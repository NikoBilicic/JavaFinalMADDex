package com.example.maddexjavafinal.database;

import javafx.scene.chart.PieChart;

import java.sql.*;

public class Database {

    private static Database instance;
    private Connection connection = null;

    private Database(){
        //Database Connection
        if (connection == null) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection =
                        DriverManager.getConnection("jdbc:mysql://localhost/"
                                        + DBConst.DB_NAME + "?serverTimezone=UTC",
                                DBConst.DB_USER,
                                DBConst.DB_PASS);
                System.out.println("Created Connection");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Database getInstance(){
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() { return connection; }

    public void close(){
        System.out.println("Closing Connection");

        try {
            connection.close();
        } catch (Exception e) {
            connection = null;
            e.printStackTrace();
        }
    }
    


}
