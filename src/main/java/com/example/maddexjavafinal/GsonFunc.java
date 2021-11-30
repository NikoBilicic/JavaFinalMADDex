package com.example.maddexjavafinal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;

import static com.example.maddexjavafinal.database.DBTableVals.typing;

public class GsonFunc {

    //function to get pokemon sprite
    public static String getPokeSprite(JsonObject object, String gender, String shiny) {
        String pokeSprite = "";
        JsonObject allSprites = object.getAsJsonObject("sprites");
        if (gender == "M" && shiny == "N") {
            String sprite = String.valueOf(allSprites.get("front_default"));
            return String.valueOf(sprite);
        } else if (gender == "M" && shiny == "Y") {
            String sprite = String.valueOf(allSprites.get("front_shiny"));
            return String.valueOf(sprite);
        } else if (gender == "F" && shiny == "N") {
            String sprite = String.valueOf(allSprites.get("front_female"));
            return String.valueOf(sprite);
        } else if (gender == "F" && shiny == "Y") {
            String sprite = String.valueOf(allSprites.get("front_shiny_female"));
            return String.valueOf(sprite);
        }
        return null;
    }

    //Function to get pokemon name
    public static String getPokeName(JsonObject object) {
        String pokeName = "";
        pokeName = String.valueOf(object.get("name"));
        return pokeName.replaceAll("\"", "");
    }

    //Function to get pokemon Dex num
    public static int getDexNum(JsonObject object) {
        int dexNum = 0;
        dexNum = object.get("id").getAsInt();
        return dexNum;
    }

    //function to get pokemon types
    public static int getPokeTyping(JsonObject object) {
        JsonArray types = object.getAsJsonArray("types");
        if (types.size() > 1) {
            JsonObject type1 = (JsonObject) types.get(0);
            JsonObject type1Type = type1.getAsJsonObject("type");
            types.add(String.valueOf(type1Type.get("name")));
            JsonObject type2 = (JsonObject) types.get(1);
            JsonObject type2Type = type2.getAsJsonObject("type");
            String[] pokeTypes = {String.valueOf(type1Type.get("name")), String.valueOf(type2Type.get("name"))};
            for (int i = 0; i <= 17; i++) {
                if (pokeTypes[0].replaceAll("\"", "").equalsIgnoreCase(typing[i])) {
                    System.out.println(typing[i]);
                    return i + 1;
                }
            }
        } else {
            JsonObject type1 = (JsonObject) types.get(0);
            JsonObject type1Type = type1.getAsJsonObject("type");
            String pokeTypes = String.valueOf(type1Type.get("name"));
            for (int i = 0; i <= 17; i++) {
                if (pokeTypes.replaceAll("\"", "").equalsIgnoreCase(typing[i])) {
                    System.out.println(typing[i]);
                    return i + 1;
                }
            }
        }
        return 0;
    }

    //function to get pokemon generation
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
