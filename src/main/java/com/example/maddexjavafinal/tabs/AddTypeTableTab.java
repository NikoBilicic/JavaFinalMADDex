package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class AddTypeTableTab extends Tab {

    private static  AddTypeTableTab tab;

    private AddTypeTableTab() {
        this.setText("Type Table");

        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        root.getChildren().addAll(background);

        this.setContent(root);
    }

    public static AddTypeTableTab getInstance() {
        if (tab == null) {
            tab = new AddTypeTableTab();
        }
        return tab;
    }
}
