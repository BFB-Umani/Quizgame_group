package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.QuizServer;
import com.quizgame.view.StartScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartSceneController {
    private QuizServer quizServer;
    private String input;
    private StartScene startScene;
    private QuizClient quizClient;
    private QuizController quizController;

    public StartSceneController(StartScene startScene, QuizClient quizClient){
        this.startScene = startScene;
        this.quizClient = quizClient;
    }

    public void start(){
        quizClient.getMsg();
        startScene.setUp();
        startScene.getNewGameButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input = startScene.getTextField().getText();
                quizClient.sendMsg(input);
                changeToChoosingSubjectScene();
            }
        });
    }

    public void changeToChoosingSubjectScene(){
        quizClient.goToChoseSubjectScene();
    }


}
