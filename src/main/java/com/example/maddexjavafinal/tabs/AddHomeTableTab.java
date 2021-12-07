package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.pojo.ViewPoke;
import com.example.maddexjavafinal.scenes.AddOrRemoveScene;
import com.example.maddexjavafinal.tables.PokeTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class AddHomeTableTab extends Tab {
    private static  AddHomeTableTab tab;
    public static TableView tableView;
    public ImageView imageView;
    private AddHomeTableTab() {
        this.setText("Home Table");
        PokeTable pokeTable = new PokeTable();

        StackPane root = new StackPane();
        VBox vBox = new VBox();
        tableView = new TableView();
        imageView = new ImageView();

        TableColumn<ViewPoke, Number> idColumn = new TableColumn<>("Dex Number");
        idColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getId()));

        TableColumn<ViewPoke, String> spriteColumn = new TableColumn<>("Sprites");
        spriteColumn.setCellValueFactory(e -> new SimpleStringProperty("Select to show sprite."));

        TableColumn<ViewPoke, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<ViewPoke, String> typeColumn = new TableColumn<>("Typing");
        typeColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getType()));

        TableColumn<ViewPoke, Number> genColumn = new TableColumn<>("Gen");
        genColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getGen()));

       tableView.getColumns().addAll(idColumn, spriteColumn, nameColumn, typeColumn, genColumn);
       tableView.getItems().addAll(pokeTable.displayPokes());
       tableView.setMaxSize(395, 384);

        HBox edit = new HBox();
        HBox otherBox = new HBox();
        otherBox.setAlignment(Pos.CENTER);
        otherBox.setSpacing(25);

        Button showSpriteButt = new Button("Show Sprite");
        showSpriteButt.setOnAction(e -> {
            ViewPoke pokemon = (ViewPoke) tableView.getSelectionModel().getSelectedItem();
            imageView.setImage(new Image(pokemon.getSprite()));
        });

        Button editButt = new Button("Edit");
        editButt.setOnAction(e -> {
            HelloApplication.otherStage.setScene(new AddOrRemoveScene());
        });

        edit.getChildren().addAll(editButt, showSpriteButt);
        edit.setAlignment(Pos.BOTTOM_CENTER);
        editButt.setPrefSize(75,25);
        editButt.setFont(new Font(18));
        showSpriteButt.wrapTextProperty();
        showSpriteButt.setFont(new Font(18));
        edit.setPadding(new Insets(50));
        edit.setSpacing(25);

        otherBox.getChildren().addAll(tableView, imageView);

        vBox.getChildren().addAll(otherBox, edit);
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
