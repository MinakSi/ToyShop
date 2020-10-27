
package com.minakov.servlet.admin;


import com.minakov.database.DBManager;
import com.minakov.database.entity.Product;
import com.minakov.servlet.listener.ConfigListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;

@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ConfigListener.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/adminAddProduct.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
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
                LOG.error("admin add product error", e);

            } catch (SQLException exception) {
                exception.printStackTrace();
                req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
                LOG.error("admin add product error", exception);
            }

        }
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
}
