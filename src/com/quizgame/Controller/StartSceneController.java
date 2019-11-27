package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.view.StartScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartSceneController {
    private String input;
    private StartScene startScene;
    private QuizClient quizClient;

    public StartSceneController(StartScene startScene, QuizClient quizClient) {
        this.startScene = startScene;
        this.quizClient = quizClient;
    }

    public void start() {
        startScene.setUp();
        startScene.getNewGameButton().setOnAction(actionEvent -> {
            input = startScene.getTextField().getText();
            if (!input.equalsIgnoreCase("")) {
                System.out.println(input);
                quizClient.getServerConnection().sendNameToServer(input);
                changeToWaitingScene();
            }
        });
    }

    public String getInput() {
        return input;
    }

    public void changeToWaitingScene() {
        quizClient.goToWaitingScene();
    }


}
