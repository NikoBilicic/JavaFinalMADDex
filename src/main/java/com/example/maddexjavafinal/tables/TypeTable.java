package com.example.maddexjavafinal.tables;

import com.example.maddexjavafinal.daos.TypeDAO;
import com.example.maddexjavafinal.database.DBTableVals;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.pojo.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeTable implements TypeDAO {
    Database db = Database.getInstance();
    ArrayList<Type> types;

    public String[] typing = {"normal", "fighting", "flying", "poison", "ground", "rock", "bug",
                        "ghost", "steel", "fire", "water", "grass", "electric", "psychic",
                        "ice", "dragon", "dark", "fairy"};



    @Override
    public ArrayList<Type> getAllType() {
        String query = "SELECT * FROM " + DBTableVals.TABLE_TYPE;
        types = new ArrayList<>();
        try {
            Statement getTypes = db.getConnection().createStatement();
            ResultSet data = getTypes.executeQuery(query);
            while (data.next()) {
                types.add(new Type(
                        data.getInt(DBTableVals.TYPE_COLUMN_ID),
                        data.getString(DBTableVals.TYPE_COLUMN_TYPE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public Type getType(int id) {
        String query = "SELECT * FROM " + DBTableVals.TABLE_TYPE +
                " WHERE " + DBTableVals.TYPE_COLUMN_ID + " = " + id;
        try {
            Statement getType = db.getConnection().createStatement();
            ResultSet data = getType.executeQuery(query);
            if (data.next()) {
                Type type = new Type(
                        data.getInt(DBTableVals.TYPE_COLUMN_ID),
                        data.getString(DBTableVals.TYPE_COLUMN_TYPE));
                return type;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
