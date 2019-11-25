package com.quizgame;

import javafx.application.Platform;

public class QuizProtocol<T> {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int DONEANSWER = 2;
    private static final int ANOTHER = 3;
    private int state = GETTINGNAME;
    QuizServer player;
    Database db = new Database();
    T currentQuest;
    String currentSubject = null;
    QuizServer qs;

    QuizProtocol(QuizServer qs) {
        this.qs = qs;
    }

    public T processQuestion(String answer) {
        T output = null;
        if(state == GETTINGNAME) {
            System.out.println("Current state: "  + state + " " + qs.getCurrentPlayer());
            qs.setNamn(answer);
            output = (T) db.getRoundInfo();
            state = SUBJECT;
        }
        else if(state == SUBJECT) {
            System.out.println("Current state: "  + state+ " " + qs.getCurrentPlayer());
            currentQuest = (T) db.getItemPack(answer);
            output = currentQuest;
            qs.sendRound(currentQuest);
            state = DONEANSWER;
        }
        else if(state == DONEANSWER) {
            System.out.println("Current state: "  + state+ " " + qs.getCurrentPlayer());
            System.out.println(qs.getNamn());
            qs.sendRound(true);
//            System.out.println(qs.getCurrentPlayer());
//            System.out.println(qs.getOpponent());
//            System.out.println("test");
//            qs.setCurrentPlayer(qs.getOpponent());
//            System.out.println("test");
//            System.out.println(qs.getCurrentPlayer());
//            System.out.println(qs.getOpponent());

            state = SUBJECT;
        }
        else if(state == ANOTHER) {

        }
        return  output;
    }

    public void setState(QuizServer player) {
        this.player = player;
        this.state = DONEANSWER;

    }

}
