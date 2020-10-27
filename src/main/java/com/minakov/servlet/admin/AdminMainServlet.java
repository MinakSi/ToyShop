package com.minakov.servlet.admin;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
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


public class AdminMainServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/adminMain.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (req.getParameter("logout")!=null){
            session.invalidate();
            req.getRequestDispatcher("").forward(req,resp);
            return;
        }
        ArrayList<Order> orders = null;
        try {
            orders = DBManager.getInstance().getAllOrders();
        } catch (SQLException exception) {
            LOG.error("admin main error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
        if (orders!=null){
            orders.sort(Order.orderIdDec);
            orders.sort(Order.orderStatus);
        }
        session.setAttribute("orders", orders);


        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
