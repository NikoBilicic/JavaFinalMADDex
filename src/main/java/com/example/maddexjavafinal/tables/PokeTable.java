package com.example.maddexjavafinal.tables;

import com.example.maddexjavafinal.daos.PokeDAO;
import com.example.maddexjavafinal.database.DBTableVals;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.pojo.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PokeTable implements PokeDAO {

    Database db = Database.getInstance();
    ArrayList<Poke> pokes;
    TypeTable typeTable = new TypeTable();

    @Override
    public void createPoke(Poke pokemon) {
        Statement insert;
        Statement many;
        Statement many2;

        try {
            if (pokemon.getType().size() > 1) {
                String insertStatement = "INSERT INTO POKEMON (`dex_num`, `sprite`, `name`," +
                        " `generation`) VALUES (" + pokemon.getId() + ", '" +
                        pokemon.getSprite() + "', '" + pokemon.getName() + "', " +
                        pokemon.getGen() + ")";
                insert = db.getConnection().createStatement();
                insert.execute(insertStatement);
                System.out.println("The pokemon " + pokemon.getName() + " has been added to the database.");

                String manyInsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                        pokemon.getId() + ", " + pokemon.getType().get(0) + ")";
                many = db.getConnection().createStatement();
                many.execute(manyInsertStatement);

                String many2InsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                        pokemon.getId() + ", " + pokemon.getType().get(1) + ")";
                many2 = db.getConnection().createStatement();
                many2.execute(many2InsertStatement);
            } else {

                String insertStatement = "INSERT INTO POKEMON (`dex_num`, `sprite`, `name`," +
                        " `generation`) VALUES (" + pokemon.getId() + ", '" + pokemon.getSprite() +
                        "', '" + pokemon.getName() + "', " + pokemon.getGen() + ")";
                insert = db.getConnection().createStatement();
                insert.execute(insertStatement);
                System.out.println("The pokemon " + pokemon.getName() + " has been added to the database.");

                String manyInsertStatement = "INSERT INTO POKEMON_TYPE (`dex_num`, `type`) VALUES (" +
                        pokemon.getId() + ", " + pokemon.getType().get(0) + ")";
                many = db.getConnection().createStatement();
                many.execute(manyInsertStatement);

                System.out.println("The POKEMON_TYPE table was updated.");
            }
        } catch (SQLException e) {
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
    public void deletePoke(Poke poke) {
        String query = "DELETE FROM " + DBTableVals.TABLE_POKEMON + " WHERE " + DBTableVals.POKEMON_COLUMN_ID + " = " + poke.getId();
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Pokemon deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
