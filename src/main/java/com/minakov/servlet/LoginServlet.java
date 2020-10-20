package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = (User) req.getAttribute("user");
            if(user.getTypeId()==1){
                req.getRequestDispatcher("/view/admin.jsp").forward(req, resp);
            } else{
                req.getRequestDispatcher("catalog").forward(req,resp);
            }
        } catch (NullPointerException ex){
            req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
        }


    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        User user = DBManager.getInstance().getUser(req.getParameter("phone"));
        try {
            req.setAttribute("user", user);
            String pass = req.getParameter("password");
            req.setAttribute("loggedIn", pass.equals(user.getPassword()));
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
