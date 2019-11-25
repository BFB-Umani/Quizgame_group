package com.quizgame.Controller;

import com.quizgame.QuizServer;
import com.quizgame.view.ResultScene;

public class ResultSceneController {
    private ResultScene resultScene;
    private QuizServer quizServer;

    public ResultSceneController(ResultScene resultScene){
        this.resultScene = resultScene;
    }

    public void start(){
        resultScene.setUp();
    }
}
