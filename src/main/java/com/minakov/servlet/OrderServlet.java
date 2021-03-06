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


public class OrderServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/order.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        ArrayList<Product> products = null;
        try {
            products = DBManager.getInstance().getOrderDetails(Integer.parseInt(req.getParameter("order_id")));

        } catch (SQLException exception) {
            LOG.error("order info error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
        req.setAttribute("products", products);
        req.setAttribute("order_id", req.getParameter("order_id"));
        req.setAttribute("order_total", req.getParameter("order_total"));

        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
