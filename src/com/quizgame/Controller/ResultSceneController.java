package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.view.ResultScene;


public class ResultSceneController {
    private ResultScene resultScene;
    private QuizClient quizClient;


    public ResultSceneController(ResultScene resultScene, QuizClient quizClient){
        this.resultScene = resultScene;
        this.quizClient = quizClient;
    }

    public void start(){
        resultScene.setUp();
        resultScene.getContinueB().setOnAction(l -> {
            if(resultScene.getContinueB().getText().equalsIgnoreCase("continue")) {
                quizClient.getServerConnection().sendResultComplete(true);
                quizClient.getQuizController().setQuizCounter();
            }
            else if(resultScene.getContinueB().getText().equalsIgnoreCase("Quit")) {
                System.exit(0);
            }
        });
        resultScene.getChat().setOnAction(l -> {
            quizClient.getChatScene().getSecondStage().show();
        });
    }



}
