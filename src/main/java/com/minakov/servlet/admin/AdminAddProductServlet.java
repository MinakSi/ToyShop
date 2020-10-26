
package com.minakov.servlet.admin;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/adminAddProduct.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (req.getParameterMap().containsKey("createProduct")) {

            try {
                Part part = req.getPart("file");
                Product product = new Product(0,
                        req.getParameter("name"),
                        Double.parseDouble(req.getParameter("price")),
                        Integer.parseInt(req.getParameter("amount")),
                        req.getParameter("description"));
                DBManager.getInstance().setProduct(part,product);
            } catch (ServletException | FileAlreadyExistsException e) {
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
                //todo: log
            } catch (SQLException exception) {
                exception.printStackTrace();
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
            }

        }
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
}
