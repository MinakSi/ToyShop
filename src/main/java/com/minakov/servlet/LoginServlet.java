package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {

    private String string;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("key", string);
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
/*        switch (req.getParameter("name")) {
            case ("customer"):
//                resp.sendRedirect(req.getContextPath() + "/customer");
                string = "customer";
                break;
            case ("admin"):
//                resp.sendRedirect(req.getContextPath() + "/admin");
                string = "admin";
                break;
        }*/
        User user = DBManager.getInstance().getUser(req.getParameter("phone"));
        req.setAttribute("user", user.getFirstName());
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
