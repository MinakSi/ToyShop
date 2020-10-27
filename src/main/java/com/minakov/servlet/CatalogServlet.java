package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Product;
import com.minakov.servlet.listener.ConfigListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class CatalogServlet extends HttpServlet {

    private final int AMOUNT_ON_PAGE = 2;
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/catalog.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        ArrayList<Product> products = null;
        int page = req.getParameter("page") == null?1:Integer.parseInt(req.getParameter("page"));

        if (req.getParameterMap().containsKey("sort") || req.getParameterMap().containsKey("+")
        || req.getParameterMap().containsKey("-")) {
            String sortProducts;
            switch (req.getParameter("sortBy")){
                case"price up":
                    sortProducts = DBManager.SQL_ORDER_BY_PRICE;
                    break;
                case"price down":
                    sortProducts = DBManager.SQL_ORDER_BY_PRICE_DESC;
                    break;
                case"name a-z":
                    sortProducts = DBManager.SQL_ORDER_BY_NAME;
                    break;
                case"name z-a":
                    sortProducts = DBManager.SQL_ORDER_BY_NAME_DESC;
                    break;
                default:
                    sortProducts = DBManager.SQL_ORDER_BY_ID;
            }
            if(req.getParameter("+")!=null){
                page++;
            } else if (req.getParameter("-")!=null){
                page--;
            }
            try {
                products =  DBManager.getInstance().getProducts(sortProducts,
                        (page-1)*AMOUNT_ON_PAGE, AMOUNT_ON_PAGE);
            } catch (SQLException exception) {
                LOG.error("catalog error", exception);
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
            }
            req.setAttribute("sortBy", req.getParameter("sortBy"));
        }else{
            try {
                products =  DBManager.getInstance().getProducts(DBManager.SQL_ORDER_BY_ID,0,AMOUNT_ON_PAGE);
            } catch (SQLException exception) {
                LOG.error("catalog error", exception);
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
            }
            req.setAttribute("sortBy", "id");
        }
        if (products!=null){
            req.setAttribute("full", products.size() > 0);
        }
        req.setAttribute("products", products);
        req.setAttribute("page", page);
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
