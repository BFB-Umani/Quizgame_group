package com.quizgame;

public class QuizProtocol<T> {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int RESULT = 2;
    private static final int ANOTHER = 3;
    private int state = GETTINGNAME;
    QuizServer qs;
    Database db = new Database();

    QuizProtocol(QuizServer qs) {
        this.qs = qs;
    }

    public T processQuestion(String answer) {
        T output = null;
        if(state == GETTINGNAME) {
            qs.setNamn(answer);
            output = (T) db.getRoundInfo();
            state = SUBJECT;
        }
        else if(state == SUBJECT) {
            output = (T) db.getItemPack(answer);
            state = RESULT;
        }
        else if(state == RESULT) {

        }
        else if(state == ANOTHER) {
            // do something
        }
        return  output;
    }
}
