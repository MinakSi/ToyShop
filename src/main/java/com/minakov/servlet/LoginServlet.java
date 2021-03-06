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


public class LoginServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if("admin".equals(user.getType().getName())){
                req.getRequestDispatcher("admin/main").forward(req, resp);
            }
            else if("blocked".equals(user.getType().getName())){
                session.invalidate();
                req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
            }
            else {
                req.getRequestDispatcher("catalog").forward(req,resp);
            }
        } catch (NullPointerException ex){
            req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
        }


    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        User user = null;
        try {
            user = DBManager.getInstance().getUser(req.getParameter("phone"));
        } catch (SQLException exception) {
            LOG.error("login error", exception);
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession();
        try {
            String pass = req.getParameter("password");
            req.setAttribute("loggedIn", pass.equals(user.getPassword()));
            session.setAttribute("user", user);
        } catch (NullPointerException e){
            req.setAttribute("loggedIn", false);
        }
//        if (session.getAttribute("locale")==null){
            session.setAttribute("locale", "ru");
//        }
//        req.setAttribute("locales", session.getAttribute("locale"));

        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
