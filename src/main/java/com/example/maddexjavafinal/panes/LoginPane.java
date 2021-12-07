package com.example.maddexjavafinal.panes;

import com.example.maddexjavafinal.HelloApplication;
import com.example.maddexjavafinal.database.Database;
import com.example.maddexjavafinal.scenes.ConfirmationScene;
import com.example.maddexjavafinal.scenes.HomeScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class LoginPane extends StackPane {
    public LoginPane() {
        //Brings file into view
        File login = new File("src/main/java/com/example/maddexjavafinal/database/login.txt");
        //arraylist to store login info
        ArrayList<String> loginInfo = new ArrayList<>();

        //calls background for pane
        ImageView background = new ImageView(new Image("file:src/imgResources/oak.png"));

        //input for username
        TextField user = new TextField();
        user.setText("Username");
        user.setMaxWidth(100);

        //input for password
        PasswordField pass = new PasswordField();
        pass.setText("Password");
        pass.setMaxWidth(100);

        //button and action to submit inputs
        Button submitButt = new Button("Login");
        submitButt.wrapTextProperty();
        submitButt.setOnAction(e -> {
            //adds login info to array
            loginInfo.add(user.getText());
            loginInfo.add(pass.getText());
            loginInfo.add(user.getText() + "java");
            try {
                //creates file i/o writer and writes array to file
                PrintWriter loginWrite = new PrintWriter(login);
                for (int i = 0; i <loginInfo.size(); i++) {
                    loginWrite.println(loginInfo.get(i));
                    System.out.println(loginInfo.get(i));
                }
                System.out.println("Login info saved");
                loginWrite.close();
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
            //sends user to rest of application
            HelloApplication.otherStage.setScene(new HomeScene());
        });

        //vbox containing content on top of background
        VBox inputs = new VBox();
        inputs.getChildren().addAll(user, pass, submitButt);
        inputs.setAlignment(Pos.BOTTOM_CENTER);
        inputs.setSpacing(15);
        inputs.setPadding(new Insets(10));

        //assign all content to pane
        this.getChildren().addAll(background, inputs);

    }
}
