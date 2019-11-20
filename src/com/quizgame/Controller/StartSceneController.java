package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.view.StartScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartSceneController {
    private String input;
    private StartScene startScene;
    private QuizClient quizClient;

    public StartSceneController(StartScene startScene, QuizClient quizClient){
        this.startScene = startScene;
        this.quizClient = quizClient;
    }

    public void start(){
        startScene.setUp();
        startScene.getNewGameButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input = startScene.getTextField().getText();
                System.out.println(input);
                changeToChoosingSubjectScene();
            }
        });
    }

    public String getInput() {
        return input;
    }
    public void changeToChoosingSubjectScene(){
        quizClient.goToChoseSubjectScene();
    }


}
