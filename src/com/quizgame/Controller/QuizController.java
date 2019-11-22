package com.quizgame.Controller;

//import com.quizgame.Database;
import com.quizgame.QuizClient;
import com.quizgame.QuizItem;
import com.quizgame.view.QuizView;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizController {
    private int questionCounter = 0;
    private int roundCounter = 0;
    private int answeredState = 0;
    private QuizView quizView;
    private QuizClient quizClient;
    private Object fromServer;
    private List<QuizItem> itemPack;
    private QuizItem item;  //eller currentItem?
    private int totalQuestion;
    private int totalRound;
    private int totalPoints;

    public QuizController(QuizView quizView, QuizClient quizClient) {
        this.quizView = quizView;
        this.quizClient = quizClient;
//        this.fromServer = fromServer;
    }

    public void start() {

        quizView.setUp();
        quizView.getAnswerButton1().setOnAction(this::handle);
        quizView.getAnswerButton2().setOnAction(this::handle);
        quizView.getAnswerButton3().setOnAction(this::handle);
        quizView.getAnswerButton4().setOnAction(this::handle);

        quizView.getContinueButton().setOnAction(this::clickedContinueButton);
    }

    public void loadGameInfo(int [] roundInfo) {
        this.totalQuestion = roundInfo[0];
        this.totalRound = roundInfo[1];
    }

    public void loadQuestion(Object fromServer) {
        this.fromServer = fromServer;
        loadItemPack(fromServer);
        item = itemPack.get(questionCounter);
        showQuestion(item);
    }

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

        quizView.getContinueButton().setVisible(false);


    }

    private void clickAnswerButton(Button button) {

        if (button.getText().equalsIgnoreCase(item.getRightAnswer())) {
            clickedRightAnswerButton(button);
            totalPoints++;
        } else {
            clickedWrongAnswerButton(button);
        }
        quizView.getContinueButton().setVisible(true);
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

    void nextQuestion() {
        quizView.getAnswerButton1().setId(".button");
        quizView.getAnswerButton2().setId(".button");
        quizView.getAnswerButton3().setId(".button");
        quizView.getAnswerButton4().setId(".button");

        //Round och Question counters
        this.questionCounter++;
        if(questionCounter >= totalQuestion) {
            quizClient.sendPoints(totalPoints);
            totalPoints = 0;
            this.roundCounter++;
            System.out.println("Done!");
            this.questionCounter = 0;
            if(roundCounter >= totalRound) {
                System.out.println("Rounds done!");
            }


        }
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


}
