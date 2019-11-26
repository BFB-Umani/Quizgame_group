package com.quizgame.Controller;

import com.quizgame.view.ResultScene;

public class ResultSceneController {
    private ResultScene resultScene;


    public ResultSceneController(ResultScene resultScene){
        this.resultScene = resultScene;
    }

    public void start(){
        resultScene.setUp();
    }
}
