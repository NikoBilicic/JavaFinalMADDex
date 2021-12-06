package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class AddGenTableTab extends Tab {

    private static  AddGenTableTab tab;

    private AddGenTableTab() {
        this.setText("Generation Table");
        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        root.getChildren().addAll(background);

        this.setContent(root);
    }

    public static AddGenTableTab getInstance() {
        if (tab == null) {
            tab = new AddGenTableTab();
        }
        return tab;
    }
}
