package com.minakov.servlet;


import com.minakov.database.DBManager;
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
import java.util.Map;


public class BuyServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/thanks.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Map<Integer, Integer> cartList = (Map<Integer, Integer>) session.getAttribute("cartList");
        try {
            DBManager.getInstance().setOrder(user.getId(), cartList);
            session.removeAttribute("cartList");
        } catch (SQLException | NullPointerException throwable) {
            LOG.error("buy/thanks error", throwable);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);

        }
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
