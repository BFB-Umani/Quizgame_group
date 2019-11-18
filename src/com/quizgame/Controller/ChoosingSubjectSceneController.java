package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.view.ChoosingSubjectScene;

public class ChoosingSubjectSceneController {
    private ChoosingSubjectScene choosingSubjectScene;
    private QuizClient quizClient;

    public ChoosingSubjectSceneController(ChoosingSubjectScene choosingSubjectScene, QuizClient quizClient){
        this.choosingSubjectScene = choosingSubjectScene;
        this.quizClient = quizClient;
    }

    public void start(){
        choosingSubjectScene.setUp();
    }
}
