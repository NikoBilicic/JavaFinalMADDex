package com.example.maddexjavafinal.tables;

import com.example.maddexjavafinal.database.DBTableVals;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.pojo.ViewPoke;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeTable {

    //Calls arraylist of pokemon for the type table
    Database db = Database.getInstance();
    ArrayList<Poke> pokes;

    public ArrayList<ViewPoke> displayPokes(String type) {
        ArrayList<ViewPoke> pokes = new ArrayList<>();
        String query = DBTableVals.POKE_TYPE_GRAB + type + "%';";
        try {
            Statement getPokes = db.getConnection().createStatement();
            ResultSet poke = getPokes.executeQuery(query);
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

}
