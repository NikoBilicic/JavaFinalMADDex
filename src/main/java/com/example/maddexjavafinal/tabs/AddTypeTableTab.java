package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;

public class AddTypeTableTab extends Tab {

    private static  AddTypeTableTab tab;

    private AddTypeTableTab() {this.setText("Type Table");}

    public static AddTypeTableTab getInstance() {
        if (tab == null) {
            tab = new AddTypeTableTab();
        }
        return tab;
    }
}
