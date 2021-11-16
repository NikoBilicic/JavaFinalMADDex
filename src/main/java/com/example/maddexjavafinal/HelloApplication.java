package com.example.maddexjavafinal;

import com.example.maddexjavafinal.tabs.AddGenTableTab;
import com.example.maddexjavafinal.tabs.AddHomeTableTab;
import com.example.maddexjavafinal.tabs.AddStatsTab;
import com.example.maddexjavafinal.tabs.AddTypeTableTab;
import javafx.application.Application;;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();


        //Build menubar
        MenuBar menu = new MenuBar();
        //Menu items
        Menu fileMenu = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e->{
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