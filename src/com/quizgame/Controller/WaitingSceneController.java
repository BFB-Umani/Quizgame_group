package com.quizgame.Controller;

import com.quizgame.view.WaitingScene;

public class WaitingSceneController {
    private WaitingScene waitingScene;

    public WaitingSceneController(WaitingScene waitingScene){
        this.waitingScene = waitingScene;
    }

    public void start() {

        waitingScene.setUp();
    }
}
