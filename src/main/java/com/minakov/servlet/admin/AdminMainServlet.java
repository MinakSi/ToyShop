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
            req.removeAttribute("logout");
            session.removeAttribute("user");
            try {
                req.getRequestDispatcher("").forward(req,resp);
                return;
            } catch (ServletException e) {
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
                LOG.error("admin main error", e);
            }
        }
        ArrayList<Order> orders = null;
        try {
            orders = DBManager.getInstance().getAllOrders();
        } catch (SQLException exception) {
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
            LOG.error("admin main error", exception);
        }
        orders.sort(Order.orderIdDec);
        session.setAttribute("orders", orders);


        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
