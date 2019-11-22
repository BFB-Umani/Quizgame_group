package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.view.ResultScene;

public class ResultSceneController {
    private ResultScene resultScene;
    private int points;
    QuizClient quizClient;

    public ResultSceneController(ResultScene resultScene, QuizClient quizClient){
        this.resultScene = resultScene;
        this.quizClient = quizClient;
    }

    public void setUserScore() {
        resultScene.getRoundOneResult1().setText(String.valueOf(points));
    }

    public void setOpponentScore() {
        resultScene.getRoundOneResult2().setText("");
    }

    public void loadScore(int points) {
        resultScene.getRoundOneResult1().setText(String.valueOf(points));
    }
    public void start(){

        resultScene.setUp();

    }
}
