package com.quizgame;

import com.quizgame.Controller.QuizController;
import com.quizgame.view.QuizView;

public class QuizProtocol {

    private static final int WAITING = 0;
    private static final int CHOOSINGANSWER = 1;
    private static final int ANOTHER = 2;

    private int state = WAITING;
    private QuizController quizController;
    private QuizView quizView;

    public QuizProtocol(QuizController quizController) {
        this.quizController = quizController;
    }

    public void result(){
        if(state == WAITING){
            quizController.start();
            state = CHOOSINGANSWER;
        }else if(state == CHOOSINGANSWER){


        }
    }
}
