package com.example.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class ImageViewExample extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Setting the Scene object
        FXMLLoader fxmlLoader = new FXMLLoader(ImageViewExample.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}

/*
* TODO - Add currency holding currency for cat
*       - Add weapon values that connects to the win rate percentage of cats
*       - disappear button and labels
*              setting disappear for labels and buttons
                            playButton.setVisible(false);
                            catName.setVisible(false);
        - add list items stolen
        - Add + percentage if browny won
        - Add thread
        - add percentage button click % for browny till sell button clicked

*
* */