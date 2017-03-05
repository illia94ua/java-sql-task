package com.acceptic.task.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionHolder {

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        } else {
            try {
                InitialContext ctx = new InitialContext();
                DataSource ds = (DataSource) ctx
                        .lookup("java:comp/env/jdbc/myds");
                connection = ds.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

}
