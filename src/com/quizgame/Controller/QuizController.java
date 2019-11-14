package com.quizgame.Controller;

import com.quizgame.DataBase;
import com.quizgame.Question;
import com.quizgame.view.QuizView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Random;

public class QuizController {
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

        quizView.getAnswerButton1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickAnswerButton((Button)actionEvent.getSource());//????????????
            }
        });

        quizView.getAnswerButton2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickAnswerButton((Button)actionEvent.getSource());
            }
        });

        quizView.getAnswerButton3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickAnswerButton((Button)actionEvent.getSource());
            }
        });

        quizView.getAnswerButton4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickAnswerButton((Button)actionEvent.getSource());
            }
        });
    }

    private Question getRandomQuestion(){
        Random random = new Random();
        Question randomQuestion = dataBase.questionList.get(random.nextInt(dataBase.questionList.size()));
        return randomQuestion;
    }

    private void showQuestion(Question question){
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
    }

    private void clickeWrongAnswerButton(Button button){
        button.setId("wrong");
        Button rightButton = getRightAnswerButton();
        if(rightButton != null){
        rightButton.setId("right");
        }
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
