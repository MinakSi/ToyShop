package com.minakov.servlet.admin;


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
import java.util.ArrayList;
import java.util.Collections;


public class AdminMainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/adminMain.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (req.getParameter("logout")!=null){
            req.removeAttribute("logout");
            session.removeAttribute("user");
            try {
                req.getRequestDispatcher("").forward(req,resp);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Order> orders = DBManager.getInstance().getAllOrders();
        orders.sort(Order.orderIdDec);
        session.setAttribute("orders", orders);


        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
