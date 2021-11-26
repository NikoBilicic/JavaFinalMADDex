package com.example.maddexjavafinal.database;

import java.sql.*;

import static com.example.maddexjavafinal.database.DBTableVals.typing;

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
                //create type table
                createTable(DBTableVals.TABLE_TYPE,
                        DBTableVals.CREATE_TABLE_TYPE, connection);
                //create Pokémon table
                createTable(DBTableVals.TABLE_POKEMON,
                        DBTableVals.CREATE_TABLE_POKEMON, connection);
                //create many table
                createTable(DBTableVals.TABLE_MANY,
                        DBTableVals.CREATE_TABLE_MANY, connection);
                //populate type table
                populateType(DBTableVals.TABLE_TYPE,
                        DBTableVals.TABLE_TYPE_POPULATE, connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Database getInstance(){
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

    private void createTable(String tableName, String tableQuery,
                             Connection connection) throws SQLException {
        Statement createTable;
        //database info
        DatabaseMetaData md = connection.getMetaData();
        //search for table with tableName
        ResultSet resultSet = md.getTables(DBConst.DB_NAME,
                null, tableName, null);
        //check if table exists
        if (resultSet.next()) {
            System.out.println(tableName + " table already exists!");
        } else {
            createTable = connection.createStatement();
            createTable.execute(tableQuery);
            System.out.println("The " + tableName + " table has been inserted.");
        }
    }

    private void populateType(String tableName, String tableQuery,
                              Connection connection) throws SQLException {
        Statement fillTable;
        Statement countCheck = connection.createStatement();

        DatabaseMetaData md = connection.getMetaData();

        String checkCount = "SELECT COUNT(*) from " + DBTableVals.TABLE_TYPE;
        ResultSet resultSet = countCheck.executeQuery(checkCount);
        resultSet.next();
        int count = resultSet.getInt(1);

       if (count < 18) {
           fillTable = connection.createStatement();
           for (int i = 0; i <= 17; i++) {
               fillTable.execute(tableQuery + "('" + typing[i] + "');");
           }
           System.out.printf("The " + tableName + " table has been populated.");
       } else {
           System.out.println("table" + tableName + " is already populated.");
       }
    }

}
