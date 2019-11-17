package com.quizgame;

import com.quizgame.Controller.QuizController;
import com.quizgame.properties.ClientPropertiesReader;
import com.quizgame.view.QuizView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class QuizClient extends Application {
        private ClientPropertiesReader clientPropertiesReader = new ClientPropertiesReader();
    public QuizClient(){
        try(
                Socket quizSocket = new Socket(clientPropertiesReader.getHostName(),clientPropertiesReader.getPortNr());
                PrintWriter out = new PrintWriter(quizSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(quizSocket.getInputStream()));

                ) {



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Quizgame");
        QuizView quizView = new QuizView();
        QuizController quizController = new QuizController(quizView);
        Scene scene = new Scene(quizView.getDesignLayout(),480,620);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/quizIcon.png"));
        scene.getStylesheets().add(QuizClient.class.getResource("Style.css").toExternalForm());
        quizView.setUp();
        quizController.start();
        stage.show();
        QuizClient quizClient = new QuizClient();

    }

    public static void main(String[] args) {
        launch(args);
    }
    //Mario funkar(kanske)
}
