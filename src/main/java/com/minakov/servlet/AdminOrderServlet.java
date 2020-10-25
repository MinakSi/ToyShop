package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


public class AdminOrderServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/adminOrder.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        Order order = DBManager.getInstance().getOrder(req.getParameter("order_id"));
        if (req.getParameter("commitSaves")!=null) {
            if (req.getParameter("invoiceInput")!=null){
                try {
                    DBManager.getInstance().setInvoice(order.getId(), req.getParameter("invoiceInput"));
//                    order.setInvoiceNumber(req.getParameter("invoiceInput"));
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    req.setAttribute("invoice",req.getParameter("invoice"));
                    req.setAttribute("checking", true);
                    //todo: error page
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
                exception.printStackTrace();
                req.setAttribute("checking", true);
            }
            order = DBManager.getInstance().getOrder(req.getParameter("order_id"));
        }
//        else{
//            req.setAttribute("order_status", req.getParameter("order_status"));
//        }
        User user = DBManager.getInstance().getUser(req.getParameter("user_phone"));
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
