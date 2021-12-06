package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.pojo.ViewPoke;
import com.example.maddexjavafinal.scenes.AddOrRemoveScene;
import com.example.maddexjavafinal.tables.PokeTable;
import com.example.maddexjavafinal.tables.TypeTable;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;


public class AddHomeTableTab extends Tab {
    private static  AddHomeTableTab tab;
    public static TableView tableView;
    private AddHomeTableTab() {
        this.setText("Home Table");
        PokeTable pokeTable = new PokeTable();
        final ImageView imageview = new ImageView();

        StackPane root = new StackPane();
        VBox vBox = new VBox();
        tableView = new TableView();

        TableColumn<ViewPoke, Number> idColumn = new TableColumn<>("PokeDex Number");
        idColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<ViewPoke, String> spriteColumn = new TableColumn<>("Sprite");
        spriteColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getSprite()))))
        TableCell<ViewPoke, String> cell = new TableCell<ViewPoke, String>();
        //cell.setGraphic(imageview.setImage(new Image(path)));

        TableColumn<ViewPoke, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<ViewPoke, String> typeColumn = new TableColumn<>("Typing");
        typeColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getType()));

        TableColumn<ViewPoke, Number> genColumn = new TableColumn<>("Generation");
        genColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getGen()));

        tableView.getColumns().addAll(idColumn, spriteColumn, nameColumn, typeColumn, genColumn);
        tableView.getItems().addAll(pokeTable.displayPokes());
        tableView.setMaxSize(380, 384);


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

        vBox.getChildren().addAll(tableView, edit);
        vBox.setAlignment(Pos.CENTER);

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        root.getChildren().addAll(background, vBox);

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
