package com.example.maddexjavafinal.panes;


import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.pojo.Poke;
import com.example.maddexjavafinal.scenes.ConfirmationScene;
import com.example.maddexjavafinal.scenes.HomeScene;
import com.example.maddexjavafinal.tables.PokeTable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.example.maddexjavafinal.tabs.AddHomeTableTab.tableRefresh;

public class ConfirmationPane extends BorderPane {
    public ConfirmationPane() {
        PokeTable pokeTable = new PokeTable();
        VBox vBox = new VBox();
        HBox hBox = new HBox();

        Text confirmationQuestion = new Text();
        confirmationQuestion.setText("Are you sure you want to remove " + RemovePokePane.removedPoke + " from the database?");
        confirmationQuestion.setFont(new Font(18));

        Button cancelButt = new Button("Cancel");
        cancelButt.setOnAction(e -> {
            HelloApplication.otherStage.setScene(new HomeScene());
        });
        cancelButt.setFont(new Font(18));
        cancelButt.setWrapText(true);

        Button destroyButt = new Button("Remove Pokemon");
        destroyButt.setOnAction(e -> {
            HelloApplication.otherStage.setScene(new HomeScene());
            pokeTable.deletePoke(RemovePokePane.removedPoke);
            tableRefresh();
        });
        destroyButt.setFont(new Font(18));
        destroyButt.setWrapText(true);

        hBox.setSpacing(25);
        hBox.getChildren().addAll(cancelButt, destroyButt);
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(confirmationQuestion, hBox);
        vBox.setAlignment(Pos.CENTER);

        this.setCenter(vBox);
    }
}
