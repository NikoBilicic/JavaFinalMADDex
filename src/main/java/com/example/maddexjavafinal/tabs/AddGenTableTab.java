package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.pojo.ViewPoke;
import com.example.maddexjavafinal.tables.GenTable;
import com.example.maddexjavafinal.tables.PokeTable;
import com.example.maddexjavafinal.tables.TypeTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddGenTableTab extends Tab {

    private static  AddGenTableTab tab;
    public static TableView tableView;
    public ImageView imageView;

    private AddGenTableTab() {
        this.setText("Generation Table");
        //calls pokeTable and pokeTable functions into view
        PokeTable pokeTable = new PokeTable();

        StackPane root = new StackPane();
        VBox vBox = new VBox();
        tableView = new TableView();
        imageView = new ImageView();

        //create tableView columns
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

        //assigns columns and pokemon to table
        tableView.getColumns().addAll(idColumn, spriteColumn, nameColumn, typeColumn, genColumn);
        tableView.getItems().addAll(pokeTable.displayPokes());
        tableView.setMaxSize(395, 384);

        //setup hBox for tableview and pokeSprite
        HBox otherBox = new HBox();
        otherBox.setAlignment(Pos.CENTER);
        otherBox.setSpacing(25);

        //setup hBox for pane buttons
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(25);

        //setups user input for generation to filter by
        TextField generationInput = new TextField();
        generationInput.setText("Please input the generation you would like to filter by");
        generationInput.setMaxWidth(300);

        //button to show sprite of selected row in table
        Button showSpriteButt = new Button("Show Sprite");
        showSpriteButt.setOnAction(e -> {
            ViewPoke pokemon = (ViewPoke) tableView.getSelectionModel().getSelectedItem();
            if (pokemon == null) {
                System.out.println("No pokemon selected");
                imageView.setImage(new Image("file:src/imgResources/image-not-found.png"));
            } else {
                if (pokemon.getSprite().equalsIgnoreCase("null")) {
                    System.out.println("No Sprite in database");
                    imageView.setImage(new Image("file:src/imgResources/image-not-found.png"));
                } else {
                    imageView.setImage(new Image(pokemon.getSprite()));
                }
            }
        });
        showSpriteButt.wrapTextProperty();
        showSpriteButt.setFont(new Font(18));

        //button to filter the table by the generation input by the user
        Button submitButt = new Button();
        submitButt.setText("Filter");
        submitButt.setOnAction(e -> {
            int selectedGen = Integer.parseInt(generationInput.getText());
            tableRefresh(selectedGen);
        });
        submitButt.setPrefSize(75,25);
        submitButt.setFont(new Font(18));

        //assign content to respective boxes
        otherBox.getChildren().addAll(tableView, imageView);
        buttonBox.getChildren().addAll(submitButt, showSpriteButt);

        //assigns hBoxes to encompassing vbox
        vBox.getChildren().addAll(generationInput, buttonBox, otherBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);

        //Sets up pane background
        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        //assign all content to stackpane
        root.getChildren().addAll(background, vBox);

        this.setContent(root);
    }

    //function to create tab
    public static AddGenTableTab getInstance() {
        if (tab == null) {
            tab = new AddGenTableTab();
        }
        return tab;
    }

    //function to refresh tableView
    public static void tableRefresh(int gen) {
        GenTable genTable = new GenTable();
        AddGenTableTab.tableView.getItems().clear();
        AddGenTableTab.tableView.getItems().addAll(genTable.displayPokes(gen));
    }
}

