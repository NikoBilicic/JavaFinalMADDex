package com.example.maddexjavafinal.panes;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.scenes.ConfirmationScene;
import com.example.maddexjavafinal.scenes.HomeScene;
import com.example.maddexjavafinal.tables.PokeTable;
import com.example.maddexjavafinal.tabs.AddHomeTableTab;
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
import javafx.scene.text.Text;

public class InsertFormPane extends StackPane {

    //declares necessary variables publicly
    public static String pokeGender;
    public static String pokeShiny = "N";

    public InsertFormPane() {
        //bring pokeTable and related functions into view
        PokeTable pokeTable = new PokeTable();
        //create content boxes
        VBox vBox = new VBox();
        HBox radioButts = new HBox();

        //create gender ToggleGroup
        final ToggleGroup gender = new ToggleGroup();

        //Text input for pokemon
        TextField newPoke = new TextField();
        newPoke.setText("Input Pokemon Name");
        newPoke.setMaxWidth(200);
        newPoke.setAlignment(Pos.CENTER);

        //Text telling user options
        Text genderQuestion = new Text();
        genderQuestion.setText("Male/Female");
        genderQuestion.setFont(new Font(12));

        //radio to assign m to gender
        RadioButton male = new RadioButton("M");
        male.setToggleGroup(gender);
        male.setSelected(true);
        male.setUserData("M");
        male.setAlignment(Pos.CENTER);

        //radio to assign f to gender
        RadioButton female = new RadioButton("F");
        female.setToggleGroup(gender);
        female.setUserData("F");
        female.setAlignment(Pos.CENTER);

        //Text asking user if pokemon is shiny
        Text shinyQuestion = new Text();
        shinyQuestion.setText("Shiny:");
        shinyQuestion.setFont(new Font(12));

        //checkBox for user to declare if pokemon is shiny or not
        CheckBox shiny = new CheckBox();
        shiny.setUserData("Y");
        shiny.setAlignment(Pos.CENTER);

        //button to grab all values and perform insert call appropriately
        Button submitButt = new Button();
        submitButt.setOnAction(e -> {
            if (gender.getSelectedToggle() == male) {
                pokeGender = "M";
            } else {
                pokeGender = "F";
            }
            if (shiny.isSelected()) {
                pokeShiny = "Y";
            } else {
                pokeShiny = "N";
            }
            pokeTable.createPoke(newPoke.getText(), pokeGender, pokeShiny);
            AddHomeTableTab.tableRefresh();
            HelloApplication.otherStage.setScene(new HomeScene());
        });
        //setup for submitButton
        submitButt.setText("Submit");
        submitButt.setFont(new Font(18));
        submitButt.setWrapText(true);
        submitButt.setAlignment(Pos.CENTER);

        //setup for radio buttons
        radioButts.getChildren().addAll(male, female);
        radioButts.setAlignment(Pos.CENTER);
        radioButts.setSpacing(15);

        //setup for vbox
        vBox.getChildren().addAll(newPoke, genderQuestion, radioButts, shinyQuestion, shiny, submitButt);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        //setup for pane background
        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(384);
        background.setFitWidth(512);

        //assign all content to pan
        this.getChildren().addAll(background, vBox);
    }
}
