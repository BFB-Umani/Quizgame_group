package com.quizgame;

import java.util.List;

public class QuizItem {
    private String subject;
    private String question;
    private String rightAnswer;
    private List<String> allAnswers;

    public QuizItem(String subject, String question,List<String> allAnswers, String rightAnswer) {
        this.subject = subject;
        this.question = question;
        this.allAnswers = allAnswers;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAllAnswer() {
        return allAnswers;
    }

    public String getSubject() {
        return subject;
    }
}