package com.quizgame;

import com.quizgame.server.Player;

public class QuizProtocol {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int CHECKINGANSWER = 2;
    private static final int ANOTHER = 3;

    private int state = GETTINGNAME;
    Player qs;

    public void processQuestion(String answer) {
        String output = "";
        if(state == GETTINGNAME) {
            qs.setPlayerName(answer);
        }
        else if(state == SUBJECT) {
            // do something
        }
        else if(state == CHECKINGANSWER) {
            // do something
        }
        else if(state == ANOTHER) {
            // do something
        }

    }
}
