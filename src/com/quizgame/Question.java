package com.quizgame;

import java.util.List;

public class Question {
    private String type;
    private String question;
    private String rightAnswer;
    private List<String> wrongAnswerList;

    public Question(String type, String question, String rightAnswer, List<String> wrongAnswerList){
        this.type = type;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswerList = wrongAnswerList;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getWrongAnswerList() {
        return wrongAnswerList;
    }

    public void setWrongAnswerList(List<String> wrongAnswerList) {
        this.wrongAnswerList = wrongAnswerList;
    }
}
