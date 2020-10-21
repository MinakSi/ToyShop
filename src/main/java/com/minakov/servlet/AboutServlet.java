package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class AboutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/about.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("product",DBManager.getInstance().getProduct(req.getParameter("product_id")));
        HttpSession session = req.getSession();
        Map<Integer, Integer> cartList = (Map<Integer, Integer>) session.getAttribute("cartList");
        int productAmount;
        try {
            productAmount = cartList.get(Integer.parseInt(req.getParameter("product_id")));
        } catch (NullPointerException exception){
            productAmount = 0;
        }
        req.setAttribute("productAmount", productAmount);


        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
