package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccountServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/account.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if ("logout".equals(req.getParameter("logout"))){
            req.removeAttribute("logout");
            session.removeAttribute("user");
            try {
                req.getRequestDispatcher("catalog").forward(req,resp);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        User user = (User)session.getAttribute("user");
        ArrayList<Order> orders = null;
        try {
            orders = DBManager.getInstance().getUserOrders(user.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
            //todo: error page, log
        }
        session.setAttribute("orders", orders);

        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
