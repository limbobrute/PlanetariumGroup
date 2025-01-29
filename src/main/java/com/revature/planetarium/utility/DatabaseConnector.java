package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("RDS_URL");
        String username = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");
        return DriverManager.getConnection(url, username, password);
    }

}
