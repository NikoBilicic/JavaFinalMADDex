package com.example.maddexjavafinal.database;

public class DBTableVals {

    //Pokémon TABLE
    public static final String TABLE_POKEMON = "pokemon";
    public static final int POKEMON_COLUMN_ID = 0;
    public static final String POKEMON_COLUMN_SPRITE = "sprite";
    public static final String POKEMON_COLUMN_NAME = "name";
    public static final String POKEMON_COLUMN_TYPE = "type";
    public static final int POKEMON_COLUMN_GEN = 0;

    //type TABLE
    public static final String TABLE_TYPE = "type";
    public static final int TYPE_COLUMN_ID = 0;
    public static final String TYPE_COLUMN_TYPE = "type";

    //many-to-many-type TABLE
    public static final String MANY_COLUMN_POKEMON = "pokemon";
    public static final String MANY_COLUMN_TYPE = "type";

    //TABLE CREATE statements

    //Type Table
    public static final String CREATE_TABLE_TYPE = "";

    //Pokémon Table
    public static final String CREATE_TABLE_POKEMON = "CREATE TABLE " + TABLE_POKEMON + " (" +
            POKEMON_COLUMN_ID + "int NOT NULL PRIMARY KEY, " +
            POKEMON_COLUMN_SPRITE + " img NOT NULL, " +
            POKEMON_COLUMN_NAME + " VARCHAR(25) NOT NULL, " +
            POKEMON_COLUMN_TYPE + "int NOT NULL, " +
            POKEMON_COLUMN_GEN + " int(2) NOT NULL, " +
            "FOREIGN KEY (" + POKEMON_COLUMN_TYPE + ")" +
                         "REFERENCES " + TABLE_TYPE + "(" + TYPE_COLUMN_ID + "));";

}
