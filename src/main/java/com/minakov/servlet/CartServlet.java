package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Product;
import com.minakov.servlet.listener.ConfigListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class CartServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/cart.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Map<Integer, Integer> cartList = (Map<Integer, Integer>) session.getAttribute("cartList");
        req.setAttribute("cartSession", cartList);
        ArrayList<Product> cart = new ArrayList<>();
        try {
            for (Map.Entry<Integer, Integer> item : cartList.entrySet()) {
                cart.add(DBManager.getInstance().getProduct(String.valueOf(item.getKey())));
            }
        } catch (NullPointerException ignored){
            /*
            * if cart is empty, NullPointerException is caught.
            * List<Product> cart is set to null.
            * cart.jsp will process this null.
            * */
        } catch (SQLException exception) {
            LOG.error("cart error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }

        req.setAttribute("products", cart);
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
