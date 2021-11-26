package com.example.maddexjavafinal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonFunc {

    //Function to get pokemon name
    public static String getPokeName(JsonObject object) {
        String pokeName = "";
        pokeName = String.valueOf(object.get("name"));
        return pokeName;
    }

    //Function to get pokemon Dex num
    public static int getDexNum(JsonObject object) {
        int dexNum = 0;
        dexNum = object.get("id").getAsInt();
        return dexNum;
    }

    public static String getPokeTyping(JsonObject object) {
        JsonArray types = object.getAsJsonArray("types");
        if (types.size() > 1) {
            JsonObject type1 = (JsonObject) types.get(0);
            JsonObject type1Type = type1.getAsJsonObject("type");
            types.add(String.valueOf(type1Type.get("name")));
            JsonObject type2 = (JsonObject) types.get(1);
            JsonObject type2Type = type2.getAsJsonObject("type");
            String[] pokeTypes = {String.valueOf(type1Type.get("name")), String.valueOf(type2Type.get("name"))};
            return pokeTypes.toString();
        } else {
            JsonObject type1 = (JsonObject) types.get(0);
            JsonObject type1Type = type1.getAsJsonObject("type");
            String pokeTypes = String.valueOf(type1Type.get("name"));
            return pokeTypes;
        }
    }

    public static Integer getPokeGen(int dexNum) {
        if (dexNum >= 1 && dexNum <= 151) {
            return 1;
        } else if (dexNum >= 152 && dexNum <= 251) {
            return  2;
        } else if (dexNum >= 252 && dexNum <= 386) {
            return  3;
        } else if (dexNum >= 387 && dexNum <= 493) {
            return  4;
        } else if (dexNum >= 494 && dexNum <= 649) {
            return 5;
        } else if (dexNum >= 650 && dexNum <= 721) {
            return 6;
        } else if (dexNum >= 722 && dexNum <= 809) {
            return 7;
        } else if (dexNum >= 810 && dexNum <= 898) {
            return 8;
        }
        return null;
    }
}
