package com.example.maddexjavafinal.panes;


import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.scenes.InsertFormScene;
import com.example.maddexjavafinal.scenes.RemovePokeScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class AddOrRemovePane extends StackPane {
    public AddOrRemovePane() {
        //main hbox
        HBox hBox = new HBox();

        //button to go to insert form
        Button addButt = new Button("Add");
        addButt.setOnAction(e -> {
            HelloApplication.otherStage.setScene(new InsertFormScene());
        });
        addButt.setPrefSize(75,25);
        addButt.setFont(new Font(18));

        //button to go to remove pokemon form
        Button removeButt = new Button("Remove");
        removeButt.setOnAction(e -> {
            HelloApplication.otherStage.setScene(new RemovePokeScene());
        });
        removeButt.setFont(new Font(18));
        removeButt.setWrapText(true);

        //setup spacing
        hBox.setSpacing(25);
        hBox.getChildren().addAll(addButt, removeButt);
        hBox.setAlignment(Pos.CENTER);

        //sets pane background
        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(384);
        background.setFitWidth(512);

        //assign all content to pane
        this.getChildren().addAll(background, hBox);
    }
}
