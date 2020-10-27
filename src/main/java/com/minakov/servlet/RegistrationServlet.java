package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.servlet.listener.ConfigListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class RegistrationServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/registration.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        boolean exc = false;
        try {
            DBManager.getInstance().setUser(req.getParameter("firstName"), req.getParameter("secondName"),
                    req.getParameter("email"), req.getParameter("phone"),
                    req.getParameter("address"), req.getParameter("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            exc = true;
        }
        req.setAttribute("exception", exc);
        try {
            if (!exc) {
                HttpSession session = req.getSession();
                session.setAttribute("user", DBManager.getInstance().getUser(req.getParameter("phone")));
                req.getRequestDispatcher("catalog").forward(req, resp);
            } else {
                doGet(req, resp);
            }
        } catch (SQLException exception) {
            LOG.error("registration error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }

    }
}
