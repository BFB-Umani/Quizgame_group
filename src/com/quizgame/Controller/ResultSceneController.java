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
            quizClient.getServerConnection().sendResultComplete(true);
        });
    }

//    public void setResult(String namn, int points) {
//        resultScene.getPlayerOneText().setText(namn);
//        resultScene.getRoundOneResult1().setText(String.valueOf(points));
//    }
}
