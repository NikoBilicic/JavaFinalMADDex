package com.example.maddexjavafinal;

import com.example.maddexjavafinal.database.DBTableVals;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.scenes.HomeScene;
import com.example.maddexjavafinal.scenes.LoginScene;
import com.example.maddexjavafinal.tabs.AddGenTableTab;
import com.example.maddexjavafinal.tabs.AddHomeTableTab;
import com.example.maddexjavafinal.tabs.AddStatsTab;
import com.example.maddexjavafinal.tabs.AddTypeTableTab;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

import static com.example.maddexjavafinal.GsonFunc.*;
import static com.example.maddexjavafinal.database.Database.insertPokemon;

public class HelloApplication extends Application {
    public static Stage otherStage;
    @Override
    public void start(Stage stage) throws IOException {
        //call login file into view
        File login = new File("src/main/java/com/example/maddexjavafinal/database/login.txt");
        //initialize read scanner
        Scanner read = null;

        //sets stage for entire application
        otherStage = new Stage();
        otherStage.setTitle("MAD Dex");

        //checks if login info has already been input
        try {
            read = new Scanner(login);
            if (read.hasNextLine()) {
                otherStage.setScene(new HomeScene());
            } else {
                otherStage.setScene(new LoginScene());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        otherStage.setResizable(false);
        otherStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}