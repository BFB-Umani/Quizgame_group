package com.quizgame.Controller;

//import com.quizgame.Database;
import com.quizgame.QuizItem;
import com.quizgame.view.QuizView;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizController {
    private int lastQuestion = 0;
    private int questionCounter = 0;
    private int[] storedQuestion = {5, 5, 5, 5};
    private int answeredState = 0;
    private QuizView quizView;
    private Object fromServer;
    private List<QuizItem> itemPack;
    private QuizItem item;  //eller currentItem?
    private int score = 0;

    public QuizController(QuizView quizView, Object fromServer) {
        this.fromServer = fromServer;
        this.quizView = quizView;
    }

    public void start() {

        quizView.setUp();
        loadItemPack(fromServer); //vi anropar först den nya metoden
        item = itemPack.get(questionCounter);
        showQuestion(item); //bara startar
        //förmodligen ska questionCounter loopa s till vardet n
        // n ska komma (direkt eller odirekt vet ej...)  från properties
        // men var ska counter loopa? inte har: det är bara en start!
        // från nextquestion? Eller?
        // och vad händer när loop tar slut? Alltså när round tar slut?


        quizView.getAnswerButton1().setOnAction(this::handle);
        quizView.getAnswerButton2().setOnAction(this::handle);
        quizView.getAnswerButton3().setOnAction(this::handle);
        quizView.getAnswerButton4().setOnAction(this::handle);

        quizView.getContinueButton().setOnAction(this::clickedContinueButton);
    }


    //NEW METOD!!
    private void loadItemPack(Object fromServer) {
        List<QuizItem> itemPack = (List<QuizItem>)fromServer;
        this.itemPack = itemPack;
    }

    private void clickedContinueButton(ActionEvent actionEvent){
        nextQuestion();
    }

    private void handle(ActionEvent actionEvent) {
        if (answeredState == 0) {
            clickAnswerButton((Button) actionEvent.getSource());
        }
    }

    private void showQuestion(QuizItem item) {

        List<String> answerList = new ArrayList<>();
        answerList.addAll(item.getAllAnswers());
        Collections.shuffle(answerList);


        quizView.getQuestionLabel().setMaxWidth(Double.MAX_VALUE);
        quizView.getQuestionLabel().setAlignment(Pos.CENTER);
        quizView.getQuestionLabel().setText(item.getQuestion());
        quizView.getAnswerButton1().setText(answerList.get(0));
        quizView.getAnswerButton2().setText(answerList.get(1));
        quizView.getAnswerButton3().setText(answerList.get(2));
        quizView.getAnswerButton4().setText(answerList.get(3));

        quizView.getContinueButton().setVisible(false);


    }

    private void clickAnswerButton(Button button) {

        if (button.getText().equalsIgnoreCase(item.getRightAnswer())) {
            clickedRightAnswerButton(button);
        } else {
            clickedWrongAnswerButton(button);
        }
        quizView.getContinueButton().setVisible(true);
    }

    private void clickedRightAnswerButton(Button button) {
        button.setId("right");
        answeredState = 1;
        increaseScore();
    }

    private void clickedWrongAnswerButton(Button button) {
        button.setId("wrong");
        Button rightButton = getRightAnswerButton();
        if (rightButton != null) {
            rightButton.setId("right");
        }
        answeredState = 1;
    }

    void nextQuestion() {  //nu går vidare genom alla fyra item och sen crashar för Index 4 out of bounds(så klart!)
        quizView.getAnswerButton1().setId(".button");
        quizView.getAnswerButton2().setId(".button");
        quizView.getAnswerButton3().setId(".button");
        quizView.getAnswerButton4().setId(".button");
        this.questionCounter++;
        item = itemPack.get(questionCounter);
        showQuestion(item);
        answeredState = 0;
    }

    private Button getRightAnswerButton() {

        if (item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton1().getText())) {
            return quizView.getAnswerButton1();
        } else if (item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton2().getText())) {
            return quizView.getAnswerButton2();
        } else if (item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton3().getText())) {
            return quizView.getAnswerButton3();
        } else if (item.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton4().getText())) {
            return quizView.getAnswerButton4();
        } else {
            return null;
        }

    }

    private void increaseScore(){
        score++;
        quizView.getScoreCounter().setText("Score: " + score);
    }


}
