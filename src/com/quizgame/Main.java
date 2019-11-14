package com.quizgame;

import com.quizgame.Controller.QuizController;
import com.quizgame.view.QuizView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Quizgame");
        QuizView quizView = new QuizView();
        QuizController quizController = new QuizController(quizView);
        Scene scene = new Scene(quizView.getDesignLayout(),425,375);
        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        quizView.setUp();
        quizController.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
