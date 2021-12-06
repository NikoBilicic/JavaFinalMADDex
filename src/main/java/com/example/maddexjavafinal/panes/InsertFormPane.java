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

    public static String pokeGender;
    public static String pokeShiny = "N";

    public InsertFormPane() {
        PokeTable pokeTable = new PokeTable();
        VBox vBox = new VBox();
        HBox radioButts = new HBox();

        final ToggleGroup gender = new ToggleGroup();

        TextField newPoke = new TextField();
        newPoke.setText("Input Pokemon Name");
        newPoke.setMaxWidth(200);
        newPoke.setAlignment(Pos.CENTER);

        Text genderQuestion = new Text();
        genderQuestion.setText("Male/Female");
        genderQuestion.setFont(new Font(12));


        RadioButton male = new RadioButton("M");
        male.setToggleGroup(gender);
        male.setSelected(true);
        male.setUserData("M");
        male.setAlignment(Pos.CENTER);

        RadioButton female = new RadioButton("F");
        female.setToggleGroup(gender);
        female.setUserData("F");
        female.setAlignment(Pos.CENTER);

        Text shinyQuestion = new Text();
        shinyQuestion.setText("Shiny:");
        shinyQuestion.setFont(new Font(12));

        CheckBox shiny = new CheckBox();
        shiny.setUserData("Y");
        shiny.setAlignment(Pos.CENTER);

        Button submitButt = new Button();
        submitButt.setOnAction(e -> {
            pokeGender = gender.getSelectedToggle().toString();
            if (shiny.isSelected()) {
                pokeShiny = "Y";
            } else {
                pokeShiny = "N";
            }
            pokeTable.createPoke(newPoke.getText(), pokeGender, pokeShiny);
            AddHomeTableTab.tableRefresh();
            HelloApplication.otherStage.setScene(new HomeScene());
        });
        submitButt.setText("Submit");
        submitButt.setFont(new Font(18));
        submitButt.setWrapText(true);
        submitButt.setAlignment(Pos.CENTER);

        radioButts.getChildren().addAll(male, female);
        radioButts.setAlignment(Pos.CENTER);
        radioButts.setSpacing(15);

        vBox.getChildren().addAll(newPoke, genderQuestion, radioButts, shinyQuestion, shiny, submitButt);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(384);
        background.setFitWidth(512);

        this.getChildren().addAll(background, vBox);
    }
}
