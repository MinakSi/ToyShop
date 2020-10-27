package com.minakov.servlet.admin;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.User;
import com.minakov.servlet.listener.ConfigListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminOrderServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/adminOrder.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        Order order = DBManager.getInstance().getOrder(req.getParameter("order_id"));
        if (req.getParameter("commitSaves")!=null) {
            if (req.getParameter("invoiceInput")!=null){
                try {
                    DBManager.getInstance().setInvoice(order.getId(), req.getParameter("invoiceInput"));
                } catch (SQLException throwable) {
                    req.setAttribute("invoice",req.getParameter("invoice"));
                    req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
                    LOG.error("admin order error", throwable);
                }
            }
            int newStatus;
            switch(req.getParameter("order_status_name")){
                case "new":
                    newStatus = 1;
                    break;
                case "in progress":
                    newStatus = 2;
                    break;
                case "completed":
                    newStatus = 3;
                    break;
                case "rejected":
                    newStatus = 4;
                    break;
                default:
                    newStatus = order.getStatus().getId();
            }
            try {
                DBManager.getInstance().updateOrderStatus(order.getId(), newStatus);

            } catch (SQLException exception) {
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
                LOG.error("admin order error", exception);
            }
            order = DBManager.getInstance().getOrder(req.getParameter("order_id"));
        }
        User user = null;
        try {
            user = DBManager.getInstance().getUser(req.getParameter("user_phone"));
        } catch (SQLException exception) {
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
            LOG.error("admin order error", exception);
        }
        if (req.getParameter("blockUser")!=null && user!=null){
            try {
                DBManager.getInstance().blockUser(user.getId());
            } catch (SQLException exception) {
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
                LOG.error("admin order error", exception);
            }
        }

        ArrayList<Product> products = DBManager.getInstance().getOrderDetails(order.getId());
        req.setAttribute("order", order);
        req.setAttribute("products", products);
        req.setAttribute("client", user);



        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
