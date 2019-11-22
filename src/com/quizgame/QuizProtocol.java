package com.quizgame;

public class QuizProtocol<T> {

    private static final int GETTINGNAME = 0;
    private static final int SUBJECT = 1;
    private static final int CHECKINGANSWER = 2;
    private static final int ANOTHER = 3;

    private int state = GETTINGNAME;
    QuizServer qs;

    public T processQuestion(String answer) {
        String output = "";
        if(state == GETTINGNAME) {
            output = "FirstStage";
            state = SUBJECT;
        }
        else if(state == SUBJECT) {
            output = "SecondStage";
        }
        else if(state == CHECKINGANSWER) {
            // do something
        }
        else if(state == ANOTHER) {
            // do something
        }
        return (T) output;
    }
}
