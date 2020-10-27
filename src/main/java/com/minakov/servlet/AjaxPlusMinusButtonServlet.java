package com.minakov.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AjaxPlusMinusButtonServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        int amount = Integer.parseInt(req.getParameter("amount"));
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Map<Integer, Integer> cartList = (Map<Integer, Integer>) session.getAttribute("cartList");
        if(cartList==null){
            cartList = new HashMap<>();
        }

        switch (req.getParameter("operation")){
            case "plus":
                amount++;
                break;
            case "minus":
                if(amount>0){
                    amount--;
                }
                break;
            case "clear":
                amount = 0;
                break;
        }

        cartList.put(Integer.parseInt(req.getParameter("product")),amount);
        if (amount==0){
            cartList.remove(Integer.parseInt(req.getParameter("product")));
        }
        session.setAttribute("cartList", cartList);
        out.print(amount);

    }
}
