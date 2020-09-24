package com.minakov.database;

import com.minakov.database.entity.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static DBManager dbManager;
    private static final String INTERRUPT = "Interrupted!";
    @Resource(name = "jdbc/toyshop_db")
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    private static final String SQL_FIND_USER_BY_PHONE = "SELECT * FROM user WHERE phone_number = ?;";

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
                user = new User(rs.getInt("id"), rs.getString("first_name"),
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
}