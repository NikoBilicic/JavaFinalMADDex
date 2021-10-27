package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;

public class AddGenTableTab extends Tab {

    private static  AddGenTableTab tab;

    private AddGenTableTab() {this.setText("Generation Table");}

    public static AddGenTableTab getInstance() {
        if (tab == null) {
            tab = new AddGenTableTab();
        }
        return tab;
    }
}
