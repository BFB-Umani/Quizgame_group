package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.QuizServer;
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
    }
}
