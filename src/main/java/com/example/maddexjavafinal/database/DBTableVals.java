package com.example.maddexjavafinal.database;

public class DBTableVals {

    //pokemon TABLE
    public static final String TABLE_POKEMON = "pokemon";
    public static final int POKEMON_COLUMN_ID = 0;
    public static final String POKEMON_COLUMN_SPRITE = "sprite";
    public static final String POKEMON_COLUMN_NAME = "name";
    public static final String POKEMON_COLUMN_TYPE = "type";
    public static final int POKEMON_COLUMN_GEN = 0;

    //type TABLE
    public static final int TYPE_COLUMN_ID = 0;
    public static final String TYPE_COLUMN_TYPE = "type";

    //many-to-many-type TABLE
    public static final String MANY_COLUMN_POKEMON = "pokemon";
    public static final String MANY_COLUMN_TYPE = "type";
}
