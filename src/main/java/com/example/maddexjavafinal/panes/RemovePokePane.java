package com.example.maddexjavafinal.panes;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.scenes.ConfirmationScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RemovePokePane extends StackPane {

    //declare var for pokemon name to delete
    public static String removedPoke;

    public RemovePokePane() {
        //declares pane vbox
        VBox vBox = new VBox();

        //text input for user to declare pokemon for deletion
        TextField deletedPoke = new TextField();
        deletedPoke.setText("Input Pokemon Name");
        deletedPoke.setMaxWidth(200);

        //button to grab name of pokemon for confirmation pane
        Button deleteButt = new Button("Remove Pokemon");
        deleteButt.setOnAction(e -> {
            removedPoke = deletedPoke.getText();
            HelloApplication.otherStage.setScene(new ConfirmationScene());
        });
        deleteButt.setFont(new Font(18));
        deleteButt.setWrapText(true);

        //setup of vbox
        vBox.getChildren().addAll(deletedPoke,deleteButt);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);

        //setup pane background
        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(384);
        background.setFitWidth(512);

        //assigns content to pane
        this.getChildren().addAll(background, vBox);
    }
}
