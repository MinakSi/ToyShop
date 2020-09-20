package com.minakov.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private String string;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<String> list = new ArrayList<>();
        //list.add("SOSI");
        //list.add("SOSER");
        //String string = "SOSI";
        req.setAttribute("key", string);
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("name")) {
            case ("customer"):
//                resp.sendRedirect(req.getContextPath() + "/customer");
                string = "customer";
                break;
            case ("admin"):
//                resp.sendRedirect(req.getContextPath() + "/admin");
                string = "admin";
                break;
        }
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
