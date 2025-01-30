package com.revature.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnector
{
    public static Connection getConnection() throws SQLException
    {
        String url = System.getenv("RDS_URL");
        String username = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
        /*System.out.println("rds_username: " + username);
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("DB connection established successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("DB connection failed: " + e.getMessage());
            throw e; // Rethrow after logging
        }*/
    }
}
