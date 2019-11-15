package com.quizgame.Controller;

import com.quizgame.Database;
import com.quizgame.QuizItem;
import com.quizgame.view.QuizView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.*;

public class QuizController {
    private Database database = new Database();
    private QuizView quizView;
    private QuizItem item;
    List<QuizItem> questionList;

    public QuizController(QuizView quizView){
        //questionList = database.loadQuestions();
        item = database.getItem(); // bara för att skicka något
        this.quizView = quizView;
    }

    public void start() {
        //database.loadQuestions();
        // question = getRandomQuestion();
        showQuestion(item);

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

   /* private Question getRandomQuestion(){
       *//* Random random = new Random();
        Question randomQuestion = dataBase.questionList.get(random.nextInt(dataBase.questionList.size()));
        return randomQuestion;*//*
    }*/

    private void showQuestion(QuizItem item){

        List<String> answerList = new ArrayList<>();

        answerList.add(item.getRightAnswer());
        answerList.add(item.getWrongAnswer().get(0));
        answerList.add(item.getWrongAnswer().get(1));
        answerList.add(item.getWrongAnswer().get(2));

        Collections.shuffle(answerList);

        quizView.getQuestionLabel().setText(item.getQuestion());
        quizView.getAnswerButton1().setText(answerList.get(0));
        quizView.getAnswerButton2().setText(answerList.get(1));
        quizView.getAnswerButton3().setText(answerList.get(2));
        quizView.getAnswerButton4().setText(answerList.get(3));
    }

    private void clickAnswerButton(Button button){

        if(button.getText().equalsIgnoreCase(item.getRightAnswer())){
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

        if(item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton1().getText())){
            return quizView.getAnswerButton1();
        }else if (item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton2().getText())){
            return quizView.getAnswerButton2();
        }else if(item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton3().getText())){
            return quizView.getAnswerButton3();
        }else if (item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton4().getText())){
            return quizView.getAnswerButton4();
        }else {
            return null;
        }
    }


}
