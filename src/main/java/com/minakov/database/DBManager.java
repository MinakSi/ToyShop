package com.minakov.database;

import com.minakov.database.entity.Order;
import com.minakov.database.entity.Product;
import com.minakov.database.entity.Status;
import com.minakov.database.entity.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static DBManager dbManager;
    private static final String INTERRUPT = "Interrupted!";
    @Resource(name = "jdbc/toyshop_db")
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    private static final String SQL_FIND_USER_BY_PHONE = "SELECT * FROM user inner join user_type on " +
            "user.type_id = user_type.id WHERE phone_number = ?";
    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT * FROM product;";
    private static final String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
    private static final String SQL_FIND_ALL_ORDERS = "";
    private static final String SQL_FIND_USER_ORDERS = "select * from `order` inner join order_status os on `order`.status_id = os.id join user u on u.id = `order`.user_id " +
            "where user_id = ?;";
//    private static final String SQL_FIND_USER_ORDERS = "select * from `order` where user_id = ?;";
    private static final String SQL_FIND_ORDER_DETAILS = "select op.product_id, op.amount, op.price" +
            "from order_product op inner join `order` o on op.order_id = o.id where o.id = ?;";
    private static final String SQL_CREATE_USER = "INSERT INTO user(first_name, second_name, email, phone_number, address, password) " +
            "values(?, ?, ?, ?, ?, ?);";
    private static final String SQL_CREATE_ORDER = "INSERT INTO `order` (user_id,  total) values (?, 0);";
    private static final String SQL_FIND_NEW_ORDER = "select id from `order` where user_id=? and total=0;";
    private static final String SQL_CREATE_ORDER_PRODUCT = "insert into order_product (product_id, order_id, amount, price)" +
            "values (?,?,?,0);";


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

    public User getUser(String phone) {
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

    public void setUser(String name, String secondName, String email, String phone, String address, String pass) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, name);
            statement.setString(2, secondName);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.setString(6, pass);
            statement.executeUpdate();
        } catch (NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
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

    public Product getProduct(String id) {
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

    public void setOrder(int userId, Map<Integer, Integer> products) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER);
            statement.setInt(1, userId);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_FIND_NEW_ORDER);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int orderId = rs.getInt("id");
            for (Map.Entry<Integer, Integer> p : products.entrySet()) {
                statement = connection.prepareStatement(SQL_CREATE_ORDER_PRODUCT);
                statement.setInt(1, p.getKey());
                statement.setInt(2, orderId);
                statement.setInt(3, p.getValue());
                statement.executeUpdate();
            }
            connection.commit();

        } catch (SQLException | NamingException e) {
            connection.rollback();
            logger.log(Level.WARNING, INTERRUPT, e);
            throw new SQLException();
        } finally {
            connection.close();
        }
    }

    public ArrayList<Order> getUserOrders(int userId) {
        ArrayList<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_ORDERS)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = getUser(rs.getString("phone_number"));
                    Date date = rs.getDate("date");
                    orders.add(new Order(rs.getInt("id"),
                            date.toLocalDate(),
                            new Status(rs.getInt("status_id"),
                                    rs.getString("name")),
                            user,
                            rs.getString("invoice_number"),
                            rs.getDouble("total")
                            ));
                }
            }
        } catch (SQLException | NamingException e) {
            logger.log(Level.WARNING, INTERRUPT, e);
        }
        return orders;
    }
}
