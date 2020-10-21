package com.minakov.database;

import com.minakov.database.entity.Product;
import com.minakov.database.entity.Status;
import com.minakov.database.entity.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static DBManager dbManager;
    private static final String INTERRUPT = "Interrupted!";
    @Resource(name = "jdbc/toyshop_db")
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    private static final String SQL_FIND_USER_BY_PHONE = "SELECT * FROM user inner join user_type on " +
            "user.id = user_type.id WHERE phone_number = ?";
    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT * FROM product;";
    private static final String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
    private static final String SQL_FIND_ALL_ORDERS = "";

    private DBManager() {
    }



    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection() throws SQLException, NamingException {
        Context context = new InitialContext();
        ds = (DataSource) context.lookup("java:comp/env/jdbc/toyshop_db");

        return ds.getConnection();
    }

    public User getUser(String phone){
        User user = null;
        try (
                Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_PHONE)) {
            statement.setString(1, phone);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                user = new User(rs.getInt("user.id"),
                        new Status(rs.getInt("user_type.id"),
                                rs.getString("type")),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email"));
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return user;
    }
    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        try(Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS)){
            try(ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    products.add(new Product(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("amount_on_storage"),
                            rs.getString("description")));
                }


            }


        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return products;

    }

    public Product getProduct(String id){
        Product product = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCT_BY_ID)) {
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("amount_on_storage"),
                        rs.getString("description"));
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return product;
    }
}
