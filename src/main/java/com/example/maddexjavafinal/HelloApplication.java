package com.example.maddexjavafinal;

import com.example.maddexjavafinal.database.DBTableVals;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.tabs.AddGenTableTab;
import com.example.maddexjavafinal.tabs.AddHomeTableTab;
import com.example.maddexjavafinal.tabs.AddStatsTab;
import com.example.maddexjavafinal.tabs.AddTypeTableTab;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Database.getInstance();

        String query = "INSERT INTO " + DBTableVals.TABLE_TYPE +
                "(" + DBTableVals.TYPE_COLUMN_TYPE + ", " +
                "";

        //Build Pokémon
        HttpURLConnection connection = (HttpURLConnection)
                new URL("https://pokeapi.co/api/v2/pokemon/898").openConnection();
        int dexNum = 0;
        String pokeName = null;
        String type1Name = null;
        String type2Name = null;
        int gen = 0;
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()));
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject object = element.getAsJsonObject();
            //name
            pokeName = String.valueOf(object.get("name"));
            System.out.println(pokeName);
            //dex number
            JsonElement id = object.get("id");
            dexNum = id.getAsInt();
            System.out.println(dexNum);
            //types
            JsonArray types = object.getAsJsonArray("types");
            if (types.size() > 1) {
                JsonObject type1 = (JsonObject) types.get(0);
                JsonObject type1Type = type1.getAsJsonObject("type");
                type1Name = String.valueOf(type1Type.get("name"));
                JsonObject type2 = (JsonObject) types.get(1);
                JsonObject type2Type = type2.getAsJsonObject("type");
                type2Name = String.valueOf(type2Type.get("name"));

                System.out.println(type1Name + " " + type2Name);
            } else {
                JsonObject type1 = (JsonObject) types.get(0);
                JsonObject type1Type = type1.getAsJsonObject("type");
                type1Name = String.valueOf(type1Type.get("name"));

                System.out.println(type1Name);
            }
            //Generation
            if (dexNum >= 1 && dexNum <= 151) {
                System.out.println("Gen 1");
                gen = 1;
            } else if (dexNum >= 152 && dexNum <= 251) {
                System.out.println("Gen 2");
                gen = 2;
            } else if (dexNum >= 252 && dexNum <= 386) {
                System.out.println("Gen 3");
                gen = 3;
            } else if (dexNum >= 387 && dexNum <= 493) {
                System.out.println("Gen 4");
                gen = 4;
            } else if (dexNum >= 494 && dexNum <= 649) {
                System.out.println("Gen 5");
                gen = 5;
            } else if (dexNum >= 650 && dexNum <= 721) {
                System.out.println("Gen 6");
                gen = 6;
            } else if (dexNum >= 722 && dexNum <= 809) {
                System.out.println("Gen 7");
                gen = 7;
            } else if (dexNum >= 810 && dexNum <= 898) {
                System.out.println("Gen 8");
                gen = 8;
            }

        }

        Poke poke = new Poke(dexNum, "Sprite", pokeName, type1Name, gen);
        System.out.println(poke);

        BorderPane root = new BorderPane();


        //Build menubar
        MenuBar menu = new MenuBar();
        //Menu items
        Menu fileMenu = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        fileMenu.getItems().add(exit);
        //Add items to Bar
        menu.getMenus().addAll(fileMenu);
        root.setTop(menu);

        //Create a TabPane
        TabPane tabPane = new TabPane();
        //Create tabs
        AddHomeTableTab addHomeTableTab = AddHomeTableTab.getInstance();
        AddTypeTableTab addTypeTableTab = AddTypeTableTab.getInstance();
        AddGenTableTab addGenTableTab = AddGenTableTab.getInstance();
        AddStatsTab addStatsTab = AddStatsTab.getInstance();
        tabPane.getTabs().addAll(addHomeTableTab, addTypeTableTab, addGenTableTab, addStatsTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.setCenter(tabPane);
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("MAD Dex");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}