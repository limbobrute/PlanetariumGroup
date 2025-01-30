package com.revature.utils;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnector {
    private static final String DB_URL  = System.getenv("DATABASE_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASS = System.getenv("DB_PASS");

    public static Connection getConnection() throws SQLException {
        // Print the URL & user to confirm what you’re using:
        System.out.println("Attempting DB connection to: " + DB_URL);
        System.out.println("Using user: " + DB_USER);

        // Actually connect
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        // If successful:
        System.out.println("DB connection established successfully!");
        return conn;
    }
}

