package com.quizgame;

public class QuizProtocol<T> {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int DONEANSWER = 2;
    private static final int ANOTHER = 3;
    private int state = GETTINGNAME;
    Database db = new Database();

    public T processQuestion(String answer) {
        T output = null;
        if(state == GETTINGNAME) {
            output = (T) ("Välkommen: " + answer);
            state = SUBJECT;
        }
        else if(state == SUBJECT) {
            output = (T) db.getItemPack(answer);
        }
        else if(state == DONEANSWER) {
            output = (T) (Boolean) true;
        }
        else if(state == ANOTHER) {
            // do something
        }
        return  output;
    }
}
