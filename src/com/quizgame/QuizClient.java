package com.quizgame;

import com.quizgame.Controller.QuizController;
import com.quizgame.view.QuizView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class QuizClient extends Application {
    public QuizClient(){
        String hostname = "127.0.0.1";
        int portNr = 12345;
        try(
                Socket quizSocket = new Socket(hostname,portNr);
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
        stage.setScene(scene);
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
