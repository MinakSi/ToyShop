package com.minakov.servlet;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class OrderServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/order.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
//        HttpSession session = req.getSession();
        ArrayList<Product> products = null;
        try {
            products = DBManager.getInstance().getOrderDetails(Integer.parseInt(req.getParameter("order_id")));
        } catch (NullPointerException exception){
            try {
                req.getRequestDispatcher("account").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("products", products);
        req.setAttribute("order_id", req.getParameter("order_id"));
        req.setAttribute("order_total", req.getParameter("order_total"));

        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
