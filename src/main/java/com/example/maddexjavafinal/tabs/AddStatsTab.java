package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class AddStatsTab extends Tab {

    private static  AddStatsTab tab;

    private AddStatsTab() {
        this.setText("Statistics");

        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        root.getChildren().addAll(background);

        this.setContent(root);
    }

    public static AddStatsTab getInstance() {
        if (tab == null) {
            tab = new AddStatsTab();
        }
        return tab;
    }
}
