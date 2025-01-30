package com.revature.utils;

import org.sqlite.SQLiteConfig;

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
        System.out.println("rds_username: " + username);
        return DriverManager.getConnection(url, username, password);
    }
}
