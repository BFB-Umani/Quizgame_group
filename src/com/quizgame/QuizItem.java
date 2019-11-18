package com.quizgame;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizItem implements Serializable{
    String subject;
    String question;
    String rightAnswer;
    ArrayList<String> wrongAnswer = new ArrayList<>(4);

    public QuizItem(/*String subject,*/ String question, String rightAnswer, ArrayList<String> fourAnswer) {
        this.subject = subject;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer = fourAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public ArrayList<String> getWrongAnswer() {
        return wrongAnswer;
    }
}