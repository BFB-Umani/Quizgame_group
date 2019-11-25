package com.quizgame;

import javafx.application.Platform;

public class QuizProtocol<T> {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int DONEANSWER = 2;
    private static final int ANOTHER = 3;
    private int state = GETTINGNAME;
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
            System.out.println("Current state: "  + state);
            qs.setNamn(answer);
            output = (T) db.getRoundInfo();
            state = SUBJECT;
        }
        else if(state == SUBJECT) {
            System.out.println("Current state: "  + state);
            currentQuest = (T) db.getItemPack(answer);
            output = currentQuest;
            qs.sendRound(currentQuest);
            state = DONEANSWER;
        }
        else if(state == DONEANSWER) {
            System.out.println("Current state: "  + state);
            System.out.println(qs.getNamn());
            qs.sendRound(true);
            state = ANOTHER;
        }
        else if(state == ANOTHER) {

        }
        return  output;
    }
}
