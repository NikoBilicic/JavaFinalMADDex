package com.example.maddexjavafinal.panes;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.scenes.ConfirmationScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RemovePokePane extends BorderPane {

    public static String removedPoke;

    public RemovePokePane() {
        VBox vBox = new VBox();

        TextField deletedPoke = new TextField();
        deletedPoke.setText("Input Pokemon Name");
        deletedPoke.setMaxWidth(200);

        Button deleteButt = new Button("Remove Pokemon");
        deleteButt.setOnAction(e -> {
            removedPoke = deletedPoke.getText();
            HelloApplication.otherStage.setScene(new ConfirmationScene());
        });
        deleteButt.setFont(new Font(18));
        deleteButt.setWrapText(true);

        vBox.getChildren().addAll(deletedPoke,deleteButt);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);


        this.setCenter(vBox);
    }
}
