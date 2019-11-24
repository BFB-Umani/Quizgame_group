package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.QuizServer;
import com.quizgame.view.WaitingScene;

public class WaitingSceneController {
    private WaitingScene waitingScene;
    private QuizServer quizserver;
    private QuizClient quizclient;

    public WaitingSceneController(WaitingScene waitingScene){
        this.waitingScene = waitingScene;
    }

    public void start(){
        waitingScene.setUp();
    }
}
