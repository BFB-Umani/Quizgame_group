package com.quizgame;

public class QuizProtocol<T> {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int DONEANSWER = 2;
    private static final int ANOTHER = 3;
    private int state = GETTINGNAME;
    Database db = new Database();
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
            output = (T) db.getItemPack(answer);
            state = DONEANSWER;
        }
        else if(state == DONEANSWER) {
            System.out.println("Current state: "  + state);
            System.out.println(qs.getNamn());
            qs.getOpponent().setDoneRound(true);
//            output = (T) (Boolean) true;
            qs.sendRound();
            state = SUBJECT;
        }
        else if(state == ANOTHER) {
            // do something
        }
        return  output;
    }
}
