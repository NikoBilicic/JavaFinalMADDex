package com.example.maddexjavafinal.database;

import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.scenes.LoginScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.maddexjavafinal.HelloApplication.otherStage;
import static com.example.maddexjavafinal.database.DBTableVals.*;

public class Database {

    private static Database instance;
    private static Connection connection = null;

    //Singletons of all tables and views
    private Database() throws IOException {

        //initialize login array
        ArrayList<String> loginInfo = new ArrayList<>();
        //get loginInfo
        loginGet(loginInfo);

        //sends user to login scene is not previously logged in
        if (loginInfo.isEmpty()) {
            otherStage.setScene(new LoginScene());
        } else {

            //pulls login info from file
            String DB_NAME = loginInfo.get(2);
            String DB_USER = loginInfo.get(0);
            String DB_PASS = loginInfo.get(1);

            //Database Connection
            if (connection == null) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection =
                            DriverManager.getConnection("jdbc:mysql://localhost/"
                                            + DB_NAME + "?serverTimezone=UTC",
                                    DB_USER,
                                    DB_PASS);
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
                    //create pokeview
                    createView(VIEW_NAME, POKE_VIEW, connection);
                    //populate type table
                    populateType(DBTableVals.TABLE_TYPE,
                            DBTableVals.TABLE_TYPE_POPULATE, connection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Database getInstance(){
        if (instance == null) {
            try {
                instance = new Database();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    //create tables function
    private void createTable(String tableName, String tableQuery,
                             Connection connection) throws SQLException, IOException {

        //initialize login array
        ArrayList<String> loginInfo = new ArrayList<>();
        //get loginInfo
        loginGet(loginInfo);

        //pulls user login info
        String DB_NAME = loginInfo.get(2);

        Statement createTable;
        //database info
        DatabaseMetaData md = connection.getMetaData();
        //search for table with tableName
        ResultSet resultSet = md.getTables(DB_NAME,
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

    //create pokeview function
    private void createView(String viewName, String viewQuery,
                             Connection connection) throws SQLException, IOException {

        //initialize login array
        ArrayList<String> loginInfo = new ArrayList<>();
        //get loginInfo
        loginGet(loginInfo);

        //pulls user login info
        String DB_NAME = loginInfo.get(2);

        Statement createView;
        //database info
        DatabaseMetaData md = connection.getMetaData();
        //search for table with tableName
        ResultSet resultSet = md.getTables(DB_NAME,
                null, viewName, null);
        //check if table exists
        if (resultSet.next()) {
            System.out.println(viewName + " table already exists!");
        } else {
            createView = connection.createStatement();
            createView.execute(viewQuery);
            System.out.println("The " + viewName + " table has been inserted.");
        }
    }

    //function to auto populate the types table
    private void populateType(String tableName, String tableQuery,
                              Connection connection) throws SQLException {
        Statement fillTable;
        Statement countCheck = connection.createStatement();

        String checkCount = "SELECT COUNT(*) from " + DBTableVals.TABLE_TYPE;
        ResultSet resultSet = countCheck.executeQuery(checkCount);
        resultSet.next();
        int count = resultSet.getInt(1);

       if (count < 18) {
           fillTable = connection.createStatement();
           for (int i = 0; i <= 17; i++) {
               fillTable.execute(tableQuery + "('" + typing[i] + "');");
           }
           System.out.println("The " + tableName + " table has been populated.");
       } else {
           System.out.println("Table " + tableName + " is already populated.");
       }
    }

    //function to insert pokemon to the database
    public static void insertPokemon(Poke pokemon) throws SQLException{
        Statement insert;
        Statement many;
        Statement many2;

        if (pokemon.getType().size() > 1) {

            String insertStatement = "INSERT INTO POKEMON (`dex_num`, `sprite`, `name`," +
                    " `generation`) VALUES (" + pokemon.getId() + ", '" +
                    pokemon.getSprite() + "', '" + pokemon.getName() + "', " +
                    pokemon.getGen() +")";
            insert = connection.createStatement();
            insert.execute(insertStatement);
            System.out.println("The pokemon " + pokemon.getName() + " has been added to the database.");

            String manyInsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                    pokemon.getId() + ", " + pokemon.getType().get(0) + ")";
            many = connection.createStatement();
            many.execute(manyInsertStatement);

            String many2InsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                    pokemon.getId() + ", " + pokemon.getType().get(1) + ")";
            many2 = connection.createStatement();
            many2.execute(many2InsertStatement);
        } else {

            String insertStatement = "INSERT INTO POKEMON (`dex_num`, `sprite`, `name`," +
                    " `generation`) VALUES (" + pokemon.getId() + ", '" + pokemon.getSprite() +
                    "', '" + pokemon.getName() + "', " + pokemon.getGen() + ")";
            insert = connection.createStatement();
            insert.execute(insertStatement);
            System.out.println("The pokemon " + pokemon.getName() + " has been added to the database.");

            String manyInsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                    pokemon.getId() + ", " + pokemon.getType().get(0) + ")";
            many = connection.createStatement();
            many.execute(manyInsertStatement);

            System.out.println("The POKEMON_TYPE table was updated.");
        }
    }

    //function to write login info to an array when called
    public static ArrayList<String> loginGet(ArrayList<String> loginInfo) throws IOException {
        //call login file into view
        File login = new File("src/main/java/com/example/maddexjavafinal/database/login.txt");
        //initialize scanner
        Scanner read;

        try {
            read = new Scanner(login);
            //writes values to array
            while (read.hasNextLine()) {
                loginInfo.add(read.nextLine());
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

}
