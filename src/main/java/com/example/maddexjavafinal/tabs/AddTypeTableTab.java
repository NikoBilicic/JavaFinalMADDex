package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.pojo.ViewPoke;
import com.example.maddexjavafinal.tables.PokeTable;
import com.example.maddexjavafinal.tables.TypeTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddTypeTableTab extends Tab {

    private static  AddTypeTableTab tab;
    public static TableView tableView;

    private AddTypeTableTab() {
        this.setText("Type Table");
        PokeTable pokeTable = new PokeTable();

        StackPane root = new StackPane();
        VBox vBox = new VBox();
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
        tableView.setMaxSize(380, 384);

        TextField typeInput = new TextField();
        typeInput.setText("Please input the type you would like to filter by");
        typeInput.setMaxWidth(265);

        Button submitButt = new Button();
        submitButt.setText("Filter");
        submitButt.setOnAction(e -> {
            String selectedType = typeInput.getText();
            System.out.println(selectedType);
            tableRefresh(selectedType);
        });
        submitButt.setPrefSize(75,25);
        submitButt.setFont(new Font(18));

        vBox.getChildren().addAll(typeInput, submitButt, tableView);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        root.getChildren().addAll(background, vBox);

        this.setContent(root);
    }

    public static AddTypeTableTab getInstance() {
        if (tab == null) {
            tab = new AddTypeTableTab();
        }
        return tab;
    }

    public static void tableRefresh(String type) {
        TypeTable typeTable = new TypeTable();
        AddTypeTableTab.tableView.getItems().clear();
        AddTypeTableTab.tableView.getItems().addAll(typeTable.displayPokes(type));
    }
}
