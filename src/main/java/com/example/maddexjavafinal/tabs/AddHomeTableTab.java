package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.pojo.ViewPoke;
import com.example.maddexjavafinal.tables.PokeTable;
import com.example.maddexjavafinal.tables.TypeTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class AddHomeTableTab extends Tab {
    private static  AddHomeTableTab tab;
    public static TableView tableView;
    public static ViewPoke currentPoke;
    private AddHomeTableTab() {
        this.setText("Home Table");
        TypeTable typeTable = new TypeTable();
        PokeTable pokeTable = new PokeTable();

        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<ViewPoke, Number> idColumn = new TableColumn<>("PokeDex Number");
        idColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<ViewPoke, String> spriteColumn = new TableColumn<>("Sprite");
        spriteColumn.setCellValueFactory(e -> new SimpleStringProperty("sprite"));

        TableColumn<ViewPoke, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<ViewPoke, String> type1Column = new TableColumn<>("Type 1");
        type1Column.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getType()));

        TableColumn<ViewPoke, String> type2Column = new TableColumn<>("Type 2");
        type2Column.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getType2()));

        TableColumn<ViewPoke, Number> genColumn = new TableColumn<>("Generation");
        genColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getGen()));

        tableView.getColumns().addAll(idColumn, spriteColumn, nameColumn, type1Column, type2Column, genColumn);
        tableView.getItems().addAll(pokeTable.displayPokes());

        root.setCenter(tableView);

        this.setContent(root);
    }

    public static AddHomeTableTab getInstance() {
        if (tab == null) {
            tab = new AddHomeTableTab();
        }
        return tab;
    }

    public static void tableRefresh() {
        PokeTable pokeTable = new PokeTable();
        AddHomeTableTab.tableView.getItems().clear();
        AddHomeTableTab.tableView.getItems().addAll(pokeTable.displayPokes());
    }
}
