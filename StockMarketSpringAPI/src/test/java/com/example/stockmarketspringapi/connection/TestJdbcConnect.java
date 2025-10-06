package com.example.stockmarketspringapi.connection;

import java.sql.*;

public class TestJdbcConnect {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/stock_market_db_test";
        String user = "postgres";
        String pass = "dido12";
        try (Connection c = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Connected OK, autocommit=" + c.getAutoCommit());
        } catch (Exception e) {
            System.out.println("Connect failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }
}
