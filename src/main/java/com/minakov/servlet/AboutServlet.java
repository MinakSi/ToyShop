package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.servlet.listener.ConfigListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class AboutServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/about.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        try {
            req.setAttribute("product",DBManager.getInstance().getProduct(req.getParameter("product_id")));
        } catch (SQLException exception) {
            LOG.error("about product error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession();
        Map<Integer, Integer> cartList = (Map<Integer, Integer>) session.getAttribute("cartList");
        int productAmount;
        try {
            productAmount = cartList.get(Integer.parseInt(req.getParameter("product_id")));
        } catch (NullPointerException exception){
            productAmount = 0;
        }
        req.setAttribute("productAmount", productAmount);



        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
