package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if("admin".equals(user.getType().getName())){
                req.getRequestDispatcher("admin/main").forward(req, resp);
//                req.getRequestDispatcher("/view/adminMain.jsp").forward(req, resp);
            } else{
                req.getRequestDispatcher("catalog").forward(req,resp);
            }
        } catch (NullPointerException ex){
//            req.getRequestDispatcher("").forward(req, resp);
            req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
        }


    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        User user = DBManager.getInstance().getUser(req.getParameter("phone"));

        try {
            String pass = req.getParameter("password");
            req.setAttribute("loggedIn", pass.equals(user.getPassword()));
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        } catch (NullPointerException e){
            req.setAttribute("loggedIn", false);
        }

        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
