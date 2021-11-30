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
import java.sql.SQLException;

import static com.example.maddexjavafinal.GsonFunc.*;
import static com.example.maddexjavafinal.database.Database.insertPokemon;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Database.getInstance();

        String query = "INSERT INTO " + DBTableVals.TABLE_TYPE +
                "(" + DBTableVals.TYPE_COLUMN_TYPE + ", " +
                "";

        //Build Pokémon
        HttpURLConnection connection = (HttpURLConnection)
                new URL("https://pokeapi.co/api/v2/pokemon/ditto").openConnection();
        int dexNum = 0;
        int gen = 0;
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()));
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject object = element.getAsJsonObject();
            //name
            System.out.println(getPokeName(object));
            //dex number
            System.out.println(getDexNum(object));
            //types
            System.out.println(getPokeTyping(object));
            //Generation
            System.out.println(getPokeGen(getDexNum(object)));
            //Sprite
            System.out.println(getPokeSprite(object, "M", "Y"));
            //object
            Poke poke = new Poke(getDexNum(object), getPokeSprite(object, "M", "Y"), getPokeName(object), getPokeTyping(object), getPokeGen(getDexNum(object)));
            System.out.println(poke);

           // try {
           //     insertPokemon(poke);
           // } catch (SQLException e) {
           //     e.printStackTrace();
           // }
        }



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