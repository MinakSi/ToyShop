package com.minakov.servlet;
import com.minakov.database.DBManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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

        if("plus".equals(req.getParameter("operation"))){
            amount++;
        } else {
            if(amount>0){
                amount--;
            }
        }
        out.print(amount);

    }
}
