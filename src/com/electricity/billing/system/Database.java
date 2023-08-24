package com.electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    Connection connection;
    Statement statement;
    Database()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity", "root", "Neha@29");
            System.out.println("Database Connected Successfully...");
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
