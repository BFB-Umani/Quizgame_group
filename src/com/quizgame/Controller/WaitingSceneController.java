package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.view.WaitingScene;

public class WaitingSceneController {
    private WaitingScene waitingScene;
    private QuizClient quizClient;
    public WaitingSceneController(WaitingScene waitingScene, QuizClient quizClient){
        this.waitingScene = waitingScene;
        this.quizClient = quizClient;
    }

    public void start(){
        waitingScene.setUp();
    }

}
