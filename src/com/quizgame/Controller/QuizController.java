package com.quizgame.Controller;

import com.quizgame.DataBase;
import com.quizgame.Question;
import com.quizgame.view.QuizView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import java.util.Random;

public class QuizController {
    int answeredState = 0;
    private DataBase dataBase = new DataBase();
    private QuizView quizView;
    private Question question;

    public QuizController(QuizView quizView){
        this.quizView = quizView;
    }

    public void start() {
        dataBase.loadQuestions();
        question = getRandomQuestion();
        showQuestion(question);

        quizView.getAnswerButton1().setOnAction(this::handle);
        quizView.getAnswerButton2().setOnAction(this::handle);
        quizView.getAnswerButton3().setOnAction(this::handle);
        quizView.getAnswerButton4().setOnAction(this::handle);
    }

    private void handle(ActionEvent actionEvent) {
        if(answeredState == 0) {
            clickAnswerButton((Button) actionEvent.getSource());
        }else {
            nextQuestion();
        }
    }

    private Question getRandomQuestion(){
        Random random = new Random();
        Question randomQuestion = dataBase.questionList.get(random.nextInt(dataBase.questionList.size()));
        return randomQuestion;
    }

    private void showQuestion(Question question){
        quizView.getQuestionLabel().setMaxWidth(Double.MAX_VALUE);
        quizView.getQuestionLabel().setAlignment(Pos.CENTER);
        quizView.getQuestionLabel().setText(question.getQuestion());

        quizView.getAnswerButton1().setText(question.getRightAnswer());
        quizView.getAnswerButton2().setText(question.getWrongAnswerList().get(0));
        quizView.getAnswerButton3().setText(question.getWrongAnswerList().get(1));
        quizView.getAnswerButton4().setText(question.getWrongAnswerList().get(2));

    }

    private void clickAnswerButton(Button button){

        if(button.getText().equalsIgnoreCase(question.getRightAnswer())){
            clickedRightAnswerButton(button);
        }else {
            clickeWrongAnswerButton(button);
        }
    }

    private void clickedRightAnswerButton(Button button){
        button.setId("right");
        answeredState = 1;
    }

    private void clickeWrongAnswerButton(Button button){
        button.setId("wrong");
        Button rightButton = getRightAnswerButton();
        if(rightButton != null){
        rightButton.setId("right");
        }
        answeredState = 1;
    }
    void nextQuestion(){
        quizView.getAnswerButton1().setId(".button");
        quizView.getAnswerButton2().setId(".button");
        quizView.getAnswerButton3().setId(".button");
        quizView.getAnswerButton4().setId(".button");
        question = getRandomQuestion();
        showQuestion(question);
        answeredState = 0;
    }

    private Button getRightAnswerButton(){

        if(question.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton1().getText())){
            return quizView.getAnswerButton1();
        }else if (question.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton2().getText())){
            return quizView.getAnswerButton2();
        }else if(question.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton3().getText())){
            return quizView.getAnswerButton3();
        }else if (question.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton4().getText())){
            return quizView.getAnswerButton4();
        }else {
            return null;
        }
    }


}
