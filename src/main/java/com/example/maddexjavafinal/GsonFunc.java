package com.example.maddexjavafinal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import static com.example.maddexjavafinal.database.DBTableVals.typing;

public class GsonFunc {

    /**
     * Pokemon with special name issues
     * Deoxys = default:deoxys-normal, deoxys-attack, deoxys-defense, deoxys-speed
     * @Wormadam = wormadam-plant, wormadam-sandy, wormadam-trash
     * Giratina = default:giratina-altered, giratina-origin
     * Shaymin = default:shaymin-land, shaymin-sky
     * @Basculin = basculin-red-striped, basculin-blue-stripe
     * Darmanitan = default:darmanitan-standard, darmanitan-zen
     * Tornadus = default:tornadus-incarnate, tornadus-therian
     * Thundurus = default:thundurus-incarnate, thundurus-therian
     * Landorus = default:landorus-incarnate, landorus-therian
     * Keldeo = default:keldeo-ordinary, keldeo-resolute
     * Meloetta = default:meloetta-aria, meloetta-pirouette
     * Aegislash = default:aegislash-shield, aegislash-blade
     * Pumpkaboo = pumpkaboo-small, default:pumpkaboo-average, pumpkaboo-large, pumpkaboo-super
     * Gourgeist = gourgeist-small, default:gourgeist-average, gourgeist-large, gourgeist-super
     * @Oricorio = oricorio-baile, oricorio-pom-pom, oricorio-pau, oricorio-sensu
     * @Lycanroc = lycanroc-midday, lycanroc-dusk, lycanroc-midnight
     * WishiWashi = default:whishiwashi-solo, wishiwashi-school
     * Minior = minior-red-meteor
     * Mimikyu = default:mimikyu-disguise, mimikyu-busted
     * @Toxtricity = toxtricity-amped, toxtricity-low-key
     * Eiscue = default:eiscue-ice, eiscue-noice
     * Zacian = default:zacian-hero, zacian-crowned
     * Zamazenta = default:zamazenta-hero, zamazenta-crowned
     * @Urshifue = urshifu-single-strike, urshifu-rapid-strike
     * @Nidoran = nidoran-m, nidoran-f
     */

    //function to get pokemon sprite
    public static String getPokeSprite(JsonObject object, String gender, String shiny) {
        String pokeSprite = "";
        //checks input gender and shiny to grab proper sprite
        JsonObject allSprites = object.getAsJsonObject("sprites");
        if (gender == "M" && shiny == "N") {
            String sprite = String.valueOf(allSprites.get("front_default"));
            return String.valueOf(sprite).replaceAll("\"", "");
        } else if (gender == "M" && shiny == "Y") {
            String sprite = String.valueOf(allSprites.get("front_shiny"));
            return String.valueOf(sprite).replaceAll("\"", "");
        } else if (gender == "F" && shiny == "N") {
            String sprite = String.valueOf(allSprites.get("front_female"));
            return String.valueOf(sprite).replaceAll("\"", "");
        } else if (gender == "F" && shiny == "Y") {
            String sprite = String.valueOf(allSprites.get("front_shiny_female"));
            return String.valueOf(sprite).replaceAll("\"", "");
        }
        return pokeSprite;
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
    public static ArrayList<Integer> getPokeTyping(JsonObject object) {
        JsonArray types = object.getAsJsonArray("types");
        ArrayList<Integer> typesArray = new ArrayList<>();
        //checks if pokemon is mono or dual type and responds accordingly
        if (types.size() > 1) {
            JsonObject type1 = (JsonObject) types.get(0);
            JsonObject type1Type = type1.getAsJsonObject("type");
            types.add(String.valueOf(type1Type.get("name")));
            JsonObject type2 = (JsonObject) types.get(1);
            JsonObject type2Type = type2.getAsJsonObject("type");
            String[] pokeTypes = {String.valueOf(type1Type.get("name")), String.valueOf(type2Type.get("name"))};
            for (int i = 0; i <= 17; i++) {
                if (pokeTypes[0].replaceAll("\"", "").equalsIgnoreCase(typing[i])) {
                    int type1int = i + 1;
                    typesArray.add(type1int);
                }
            }
            for (int i = 0; i <= 17; i++) {
                if (pokeTypes[1].replaceAll("\"", "").equalsIgnoreCase(typing[i])) {
                    int type2int = i + 1;
                    typesArray.add(type2int);
                }
            }
            System.out.println(typesArray);
            return typesArray;
        } else {
            JsonObject type1 = (JsonObject) types.get(0);
            JsonObject type1Type = type1.getAsJsonObject("type");
            String pokeTypes = String.valueOf(type1Type.get("name"));
            for (int i = 0; i <= 17; i++) {
                if (pokeTypes.replaceAll("\"", "").equalsIgnoreCase(typing[i])) {
                    System.out.println(typing[i]);
                    typesArray.add(i + 1);
                }
            }
        }
        return typesArray;
    }

    //function to get pokemon generation
    //uses pokemon dex number to find generation
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
