package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.pojo.ViewPoke;
import com.example.maddexjavafinal.scenes.AddOrRemoveScene;
import com.example.maddexjavafinal.tables.PokeTable;
import com.example.maddexjavafinal.tables.TypeTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class AddHomeTableTab extends Tab {
    private static  AddHomeTableTab tab;
    public static TableView tableView;
    private AddHomeTableTab() {
        this.setText("Home Table");
        PokeTable pokeTable = new PokeTable();

        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<ViewPoke, Number> idColumn = new TableColumn<>("PokeDex Number");
        idColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<ViewPoke, String> spriteColumn = new TableColumn<>("Sprite");
        spriteColumn.setCellValueFactory(e -> new SimpleStringProperty("sprite"));

        TableColumn<ViewPoke, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<ViewPoke, String> typeColumn = new TableColumn<>("Typing");
        typeColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getType()));

        TableColumn<ViewPoke, Number> genColumn = new TableColumn<>("Generation");
        genColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getGen()));

        tableView.getColumns().addAll(idColumn, spriteColumn, nameColumn, typeColumn, genColumn);
        tableView.getItems().addAll(pokeTable.displayPokes());
        tableView.setMaxSize(512, 384);

        root.setCenter(tableView);

        HBox edit = new HBox();

        Button editButt = new Button("Edit");
        editButt.setOnAction(e -> {
            HelloApplication.otherStage.setScene(new AddOrRemoveScene());
        });
        edit.getChildren().add(editButt);
        edit.setAlignment(Pos.BOTTOM_CENTER);
        editButt.setPrefSize(75,25);
        editButt.setFont(new Font(18));
        edit.setPadding(new Insets(50));

        root.setBottom(edit);

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
