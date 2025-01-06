package com.revature.utils;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnector
{
    public static Connection getConnection() throws SQLException
    {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        String url = System.getenv("DATABASE_URL");
        return DriverManager.getConnection(url, config.toProperties());
    }
}
