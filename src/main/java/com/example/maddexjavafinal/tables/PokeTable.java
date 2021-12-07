package com.example.maddexjavafinal.tables;

import com.example.maddexjavafinal.daos.PokeDAO;
import com.example.maddexjavafinal.database.DBTableVals;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.pojo.Type;
import com.example.maddexjavafinal.pojo.ViewPoke;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.maddexjavafinal.GsonFunc.*;
import static com.example.maddexjavafinal.GsonFunc.getDexNum;

public class PokeTable implements PokeDAO {

    Database db = Database.getInstance();
    ArrayList<Poke> pokes;

    @Override
    public void createPoke(String pokeName, String gender, String shiny) {
        Statement insert;
        Statement many;
        Statement many2;
        if (pokeName == "deoxys") {
            pokeName = "deoxys-normal";
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)
                    new URL("https://pokeapi.co/api/v2/pokemon/" + pokeName).openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()));
                JsonElement element = JsonParser.parseReader(reader);
                JsonObject object = element.getAsJsonObject();

                Poke poke = new Poke(getDexNum(object), getPokeSprite(object, gender, shiny), getPokeName(object), getPokeTyping(object), getPokeGen(getDexNum(object)));

                try {
                    if (poke.getType().size() > 1) {
                        String insertStatement = "INSERT INTO POKEMON (`dex_num`, `sprite`, `name`," +
                                " `generation`) VALUES (" + poke.getId() + ", '" +
                                poke.getSprite() + "', '" + poke.getName() + "', " +
                                poke.getGen() + ")";
                        insert = db.getConnection().createStatement();
                        insert.execute(insertStatement);
                        System.out.println("The pokemon " + poke.getName() + " has been added to the database.");

                        String manyInsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                                poke.getId() + ", " + poke.getType().get(0) + ")";
                        many = db.getConnection().createStatement();
                        many.execute(manyInsertStatement);

                        String many2InsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                                poke.getId() + ", " + poke.getType().get(1) + ")";
                        many2 = db.getConnection().createStatement();
                        many2.execute(many2InsertStatement);
                    } else {

                        String insertStatement = "INSERT INTO POKEMON (`dex_num`, `sprite`, `name`," +
                                " `generation`) VALUES (" + poke.getId() + ", '" + poke.getSprite() +
                                "', '" + poke.getName() + "', " + poke.getGen() + ")";
                        insert = db.getConnection().createStatement();
                        insert.execute(insertStatement);
                        System.out.println("The pokemon " + poke.getName() + " has been added to the database.");

                        String manyInsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                                poke.getId() + ", " + poke.getType().get(0) + ")";
                        many = db.getConnection().createStatement();
                        many.execute(manyInsertStatement);

                        System.out.println("The POKEMON_TYPE table was updated.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @Override
    public ArrayList<Poke> getAllPoke() {
        String query = "SELECT * FROM " + DBTableVals.TABLE_POKEMON;
        pokes = new ArrayList<>();
        try {
            Statement getPokes = db.getConnection().createStatement();
            ResultSet data = getPokes.executeQuery(query);
            while (data.next()) {
                pokes.add(new Poke(data.getInt(DBTableVals.POKEMON_COLUMN_ID),
                        data.getString(DBTableVals.POKEMON_COLUMN_SPRITE),
                        data.getString(DBTableVals.POKEMON_COLUMN_NAME),
                        data.getInt(DBTableVals.POKEMON_COLUMN_GEN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokes;
    }

    @Override
    public Poke getPoke(int id) {
        String query = "SELECT * FROM " + DBTableVals.TABLE_POKEMON + " WHERE " + DBTableVals.POKEMON_COLUMN_ID + " = " + id;
        try {
            Statement getPoke = db.getConnection().createStatement();
            ResultSet data = getPoke.executeQuery(query);
            if (data.next()) {
                Poke poke = new Poke(data.getInt(DBTableVals.POKEMON_COLUMN_ID),
                        data.getString(DBTableVals.POKEMON_COLUMN_SPRITE),
                        data.getString(DBTableVals.POKEMON_COLUMN_NAME),
                        data.getInt(DBTableVals.POKEMON_COLUMN_GEN));
                return poke;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePoke(String pokeName) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)
                    new URL("https://pokeapi.co/api/v2/pokemon/" + pokeName).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()));
                JsonElement element = JsonParser.parseReader(reader);
                JsonObject object = element.getAsJsonObject();

                String query = "DELETE FROM " + DBTableVals.TABLE_MANY + " WHERE " + DBTableVals.MANY_COLUMN_POKEMON + " = " + getDexNum(object);

                try {
                    db.getConnection().createStatement().execute(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String query3 = "DELETE FROM " + DBTableVals.TABLE_POKEMON + " WHERE " + DBTableVals.POKEMON_COLUMN_NAME + " = '" + getPokeName(object) + "'";
                try {
                    db.getConnection().createStatement().execute(query3);
                    System.out.println(pokeName + " has been deleted.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public ArrayList<ViewPoke> displayPokes() {
        ArrayList<ViewPoke> pokes = new ArrayList<>();
        String query1 = DBTableVals.POKE_GRAB;
        try {
            Statement getPokes = db.getConnection().createStatement();
            ResultSet poke = getPokes.executeQuery(query1);
            while (poke.next()) {
                    pokes.add(new ViewPoke(poke.getInt(DBTableVals.POKEMON_COLUMN_ID),
                            poke.getString(DBTableVals.POKEMON_COLUMN_SPRITE),
                            poke.getString(DBTableVals.POKEMON_COLUMN_NAME),
                            poke.getString(DBTableVals.GRAB_TYPE),
                            poke.getInt(DBTableVals.POKEMON_COLUMN_GEN)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokes;
    }

    public int getTypeCount(String type) {
        int count = 0;
        try {
            PreparedStatement getCount = db.getConnection().prepareStatement("SELECT COUNT(*) FROM pokeview WHERE type LIKE '%" + type + "%';");
            ResultSet rs = getCount.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getGenCount(int gen) {
        int count = 0;
        try {
            PreparedStatement getCount = db.getConnection().prepareStatement("SELECT COUNT(*) FROM pokeview WHERE generation = " + gen);
            ResultSet rs = getCount.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
