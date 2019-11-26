package com.quizgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizItem implements Serializable{
    String subject;
    String question;
    String rightAnswer;
    List<String> allAnswers = new ArrayList<>();

    public QuizItem(String subject, String question, List<String> allAnswers,String rightAnswer) {
        this.subject = subject;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.allAnswers = allAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAllAnswers() {
        return allAnswers;
    }

    public String getSubject() {
        return subject;
    }
}