package com.example.maddexjavafinal.panes;

import com.example.maddexjavafinal.tabs.AddGenTableTab;
import com.example.maddexjavafinal.tabs.AddHomeTableTab;
import com.example.maddexjavafinal.tabs.AddStatsTab;
import com.example.maddexjavafinal.tabs.AddTypeTableTab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class HomePane extends BorderPane {
    public HomePane() {


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
        this.setTop(menu);

        //Create a TabPane
        TabPane tabPane = new TabPane();
        //Create tabs
        AddHomeTableTab addHomeTableTab = AddHomeTableTab.getInstance();
        AddTypeTableTab addTypeTableTab = AddTypeTableTab.getInstance();
        AddGenTableTab addGenTableTab = AddGenTableTab.getInstance();
        AddStatsTab addStatsTab = AddStatsTab.getInstance();
        tabPane.getTabs().addAll(addHomeTableTab, addTypeTableTab, addGenTableTab, addStatsTab);
        //remove ability to delete tabs
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.setCenter(tabPane);
    }
}
