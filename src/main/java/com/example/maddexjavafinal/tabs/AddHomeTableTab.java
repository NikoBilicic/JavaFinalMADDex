package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;

public class AddHomeTableTab extends Tab {

    private static  AddHomeTableTab tab;

    private AddHomeTableTab() {this.setText("Home Table");}

    public static AddHomeTableTab getInstance() {
        if (tab == null) {
            tab = new AddHomeTableTab();
        }
        return tab;
    }
}
