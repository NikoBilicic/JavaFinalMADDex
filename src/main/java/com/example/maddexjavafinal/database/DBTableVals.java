package com.example.maddexjavafinal.database;

public class DBTableVals {

    //Pokémon TABLE
    public static final String TABLE_POKEMON = "POKEMON";
    public static final String POKEMON_COLUMN_ID = "dex_num";
    public static final String POKEMON_COLUMN_SPRITE = "sprite";
    public static final String POKEMON_COLUMN_NAME = "name";
    public static final String POKEMON_COLUMN_GEN = "generation";

    //type TABLE
    public static final String TABLE_TYPE = "TYPE";
    public static final String TYPE_COLUMN_ID = "id";
    public static final String TYPE_COLUMN_TYPE = "type";

    //many-to-many-type TABLE
    public static final String TABLE_MANY = "POKEMON_TYPE";
    public static final String MANY_COLUMN_POKEMON = "dex_num";
    public static final String MANY_COLUMN_TYPE = "type";

    //TABLE CREATE statements

    //Type Table
    public static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TABLE_TYPE + " (" +
            TYPE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
            TYPE_COLUMN_TYPE + " VARCHAR(20), " +
            "PRIMARY KEY(" + TYPE_COLUMN_ID + ")" +
            ");";

    //Pokémon Table
    public static final String CREATE_TABLE_POKEMON = "CREATE TABLE " + TABLE_POKEMON + " (" +
            POKEMON_COLUMN_ID + " int NOT NULL PRIMARY KEY, " +
            POKEMON_COLUMN_SPRITE + " VARCHAR(255) NOT NULL, " +
            POKEMON_COLUMN_NAME + " VARCHAR(25) NOT NULL, " +
            POKEMON_COLUMN_GEN + " int(2) NOT NULL );";

    public static final String CREATE_TABLE_MANY = "CREATE TABLE " + TABLE_MANY + " (" +
            MANY_COLUMN_POKEMON + " int NOT NULL, " +
            MANY_COLUMN_TYPE + " int NOT NULL, " +
            "FOREIGN KEY (" + MANY_COLUMN_POKEMON + ") REFERENCES " + TABLE_POKEMON + "("
            + POKEMON_COLUMN_ID + "), FOREIGN KEY (" + MANY_COLUMN_TYPE + ") REFERENCES " +
            TABLE_TYPE + "(" +TYPE_COLUMN_ID + "), PRIMARY KEY (" + MANY_COLUMN_POKEMON + ", " +
            MANY_COLUMN_TYPE + "));";

    //View related statements
    public static final String VIEW_NAME = "pokeview";
    public static final String POKE_VIEW = "CREATE VIEW pokeview AS SELECT p.dex_num, p.sprite, p.name, GROUP_CONCAT(DISTINCT t.type SEPARATOR ', ') AS type, p.generation " +
            "FROM TYPE AS t " +
            "INNER JOIN POKEMON_TYPE AS pt ON t.id=pt.type " +
            "INNER JOIN POKEMON AS p ON pt.dex_num=p.dex_num " +
            "GROUP BY p.dex_num, p.sprite, p.name, p.generation";
    public static final String POKE_GRAB = "SELECT * FROM pokeview";
    public static final String POKE_TYPE_GRAB = "SELECT * FROM pokeview WHERE type LIKE '%";
    public static final String POKE_GEN_GRAB = "SELECT * FROM pokeview WHERE generation = ";
    public static final String GRAB_TYPE = "type";


    //TABLE FILL statements
    public static String[] typing = {"normal", "fighting", "flying", "poison", "ground", "rock", "bug",
            "ghost", "steel", "fire", "water", "grass", "electric", "psychic",
            "ice", "dragon", "dark", "fairy"};

    public static final String TABLE_TYPE_POPULATE = "INSERT INTO " + TABLE_TYPE + " (type) VALUES ";

}
