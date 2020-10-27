package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
import com.minakov.database.entity.User;
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


public class AccountServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/account.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if ("logout".equals(req.getParameter("logout"))){
            session.invalidate();
            req.getRequestDispatcher("catalog").forward(req,resp);
            return;
        }
        User user = (User)session.getAttribute("user");
        ArrayList<Order> orders = null;
        try {
            orders = DBManager.getInstance().getUserOrders(user.getId());
        } catch (SQLException exception) {
            LOG.error("user account error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
        session.setAttribute("orders", orders);

        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
