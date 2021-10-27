package com.example.maddexjavafinal.tabs;

import javafx.scene.control.Tab;

public class AddStatsTab extends Tab {

    private static  AddStatsTab tab;

    private AddStatsTab() {this.setText("Statistics");}

    public static AddStatsTab getInstance() {
        if (tab == null) {
            tab = new AddStatsTab();
        }
        return tab;
    }
}
