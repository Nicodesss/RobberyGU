package com.example.demo;

import java.io.*;
import java.util.Objects;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ImageViewExample extends Application {
    private final PipedInputStream pipeIn = new PipedInputStream();
    private final PipedInputStream pipeIn2 = new PipedInputStream();
    Thread errorThrower;
    private Thread reader;
    private Thread reader2;
    boolean quit;
    private TextArea txtArea;

    @Override
    public void start(Stage stage) throws IOException {
         //Setting the Scene object
        FXMLLoader fxmlLoader = new FXMLLoader(ImageViewExample.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        txtArea = Controller.staticTxtArea;
        executeReaderThread();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {

            closeThread();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public void executeReaderThread(){
        try
        {
            PipedOutputStream pout = new PipedOutputStream(this.pipeIn);
            System.setOut(new PrintStream(pout, true));
        }
        catch (IOException | SecurityException ignored)
        { }

        try
        {
            PipedOutputStream pout2 = new PipedOutputStream(this.pipeIn2);
            System.setErr(new PrintStream(pout2, true));
        }
        catch (IOException | SecurityException ignored)
        {
        }

        ReaderThread obj = new ReaderThread(pipeIn, pipeIn2, errorThrower, reader, reader2, quit, txtArea);

    }

    synchronized void closeThread(){
        System.out.println("Message: Stage is closed.");
        this.quit = true;
        notifyAll();
        try { this.reader.join(1000L); this.pipeIn.close(); } catch (Exception e) {
        }try { this.reader2.join(1000L); this.pipeIn2.close(); } catch (Exception e) {
        }System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}


//       TODO
//        - add not accessible lack of money
//        - Add + percentage if browny won
//        - add percentage button click % for browny till sell button clicked
//        - add images