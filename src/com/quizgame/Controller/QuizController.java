package com.quizgame.Controller;

//import com.quizgame.Database;
import com.quizgame.QuizItem;
import com.quizgame.properties.ServerPropertiesReader;
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
    private int roundCounter = 1; // det måste uppdateras när acceptRound tar slut
    private int totalRound = 4; // det ska komma från server. att skicka med list<quizItem>?
    private int[] storedQuestion = {5, 5, 5, 5};
    private int answeredState = 0;
    private int choiceMode = 1;     //0 = "accept mode"
    private QuizView quizView;
    private Object fromServer;
    private List<QuizItem> itemPack;
    private QuizItem item;

    public QuizController(QuizView quizView, Object fromServer) {
        this.fromServer = fromServer;
        this.quizView = quizView;
    }

    public void start() {

        quizView.setUp();
        loadItemPack(fromServer);
        item = itemPack.get(questionCounter);
        showQuestion(item);
        quizView.getAnswerButton1().setOnAction(this::handle);
        quizView.getAnswerButton2().setOnAction(this::handle);
        quizView.getAnswerButton3().setOnAction(this::handle);
        quizView.getAnswerButton4().setOnAction(this::handle);
    }


    private void loadItemPack(Object fromServer) {
        List<QuizItem> itemPack = (List<QuizItem>)fromServer;
        this.itemPack = itemPack;
    }


    private void handle(ActionEvent actionEvent) {
        if (answeredState == 0) {
            clickAnswerButton((Button) actionEvent.getSource());
        } else {
            System.out.println(questionCounter); //vi kan ta bor det
            nextQuestion();
        }

    }


    private void clickAnswerButton(Button button) {

        if (button.getText().equalsIgnoreCase(item.getRightAnswer())) {
            clickedRightAnswerButton(button);
        } else {
            clickedWrongAnswerButton(button);
        }
    }


    private void clickedRightAnswerButton(Button button) {
        button.setId("right");
        answeredState = 1;
    }


    private void clickedWrongAnswerButton(Button button) {
        button.setId("wrong");
        Button rightButton = getRightAnswerButton();
        if (rightButton != null) {
            rightButton.setId("right");
        }
        answeredState = 1;
    }

    private void showQuestion(QuizItem item) {

        List<String> answerList = new ArrayList<>();

        answerList.add(item.getRightAnswer());
        answerList.add(item.getWrongAnswer().get(0));
        answerList.add(item.getWrongAnswer().get(1));
        answerList.add(item.getWrongAnswer().get(2));

        Collections.shuffle(answerList);

        quizView.getQuestionLabel().setMaxWidth(Double.MAX_VALUE);
        quizView.getQuestionLabel().setAlignment(Pos.CENTER);
        quizView.getQuestionLabel().setText(item.getQuestion());
        quizView.getAnswerButton1().setText(answerList.get(0));
        quizView.getAnswerButton2().setText(answerList.get(1));
        quizView.getAnswerButton3().setText(answerList.get(2));
        quizView.getAnswerButton4().setText(answerList.get(3));

    }


    void nextQuestion() {
        this.questionCounter++;

        if (questionCounter<this.itemPack.size()) {
            quizView.getAnswerButton1().setId(".button");
            quizView.getAnswerButton2().setId(".button");
            quizView.getAnswerButton3().setId(".button");
            quizView.getAnswerButton4().setId(".button");

            item = itemPack.get(questionCounter);
            showQuestion(item);
            answeredState = 0;
        }
        else                                // om det inte finns en next Question
            nextRound();
    }


    void nextRound(){
        if (choiceMode == 0) {          // AcceptRound
            System.out.println("Vad händer när man slutar en acceptRound?\nförst kolla om matchen är slut");
            if (isGameOver()) {
                System.out.println("game över, Statistik");
            } else
                System.out.println("new chosingSubjectscene och new choiceRound");
                roundCounter++;

        }

        else if (choiceMode == 1)        //ChoiceRound
            System.out.println("Vad händer när man slutar en choiceRound?");
    }


    private boolean isGameOver() {
        return (roundCounter==totalRound);
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


}
