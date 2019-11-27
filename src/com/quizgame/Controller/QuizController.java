package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.QuizItem;
import com.quizgame.client.ServerConnection;
import com.quizgame.properties.ServerPropertiesReader;
import com.quizgame.view.QuizView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizController {
    private int questionCounter = 0;
    private int answeredState = 0;
    private QuizView quizView;
    private QuizClient quizClient;
    private List<QuizItem> questions;
    private QuizItem currentQuestion;
    private int quizCounter = 0;
    private int counter = 0;
    private ServerPropertiesReader prop = new ServerPropertiesReader();

    public void setQuizCounter() {
        this.quizCounter = 0;
    }

    public QuizController(QuizView quizView, QuizClient quizClient) {
        this.quizView = quizView;
        this.quizClient = quizClient;
    }

    public void start() {
        quizView.setUp();
        quizView.getAnswerButton1().setOnAction(this::handle);
        quizView.getAnswerButton2().setOnAction(this::handle);
        quizView.getAnswerButton3().setOnAction(this::handle);
        quizView.getAnswerButton4().setOnAction(this::handle);

        quizView.getContinueButton().setOnAction(this::clickedContinueButton);
    }

    public void loadQuestions(List<QuizItem> questions) {
        this.questions = questions;
        System.out.println(questions);
        currentQuestion = this.questions.get(questionCounter);
        showQuestion(currentQuestion);

    }

    private void clickedContinueButton(ActionEvent actionEvent) {
        nextQuestion();
    }

    private void handle(ActionEvent actionEvent) {
        if (answeredState == 0) {
            clickAnswerButton((Button) actionEvent.getSource());
        }
    }

    private void showQuestion(QuizItem item) {

        List<String> answerList;
        answerList = item.getAllAnswers();
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

        if (button.getText().equalsIgnoreCase(currentQuestion.getRightAnswer())) {
            clickedRightAnswerButton(button);
        } else {
            clickedWrongAnswerButton(button);
        }
        quizView.getContinueButton().setVisible(true);
    }

    private void clickedRightAnswerButton(Button button) {
        quizView.getScoreCounter().setText("Score: " + ++quizCounter);
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
        this.questionCounter++;
        if (questionCounter >= prop.getQuestionsPerRound()) {
            this.questionCounter = 0;
            Platform.runLater(() -> quizClient.getServerConnection().sendRoundComplete(quizCounter));
            quizClient.getResultScene().getPlayerOneText().setText(quizClient.getStartScene().getTextField().getText());
            quizClient.getResultScene().getResultButton().get(counter).getPlayer1Score().setText(String.valueOf(quizCounter));
            counter++;
            Platform.runLater(this::changeToWaiting);

        }
        quizView.getAnswerButton1().setId(".button");
        quizView.getAnswerButton2().setId(".button");
        quizView.getAnswerButton3().setId(".button");
        quizView.getAnswerButton4().setId(".button");
        currentQuestion = questions.get(questionCounter);
        showQuestion(currentQuestion);
        answeredState = 0;

    }

    private Button getRightAnswerButton() {

        if (currentQuestion.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton1().getText())) {
            return quizView.getAnswerButton1();
        } else if (currentQuestion.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton2().getText())) {
            return quizView.getAnswerButton2();
        } else if (currentQuestion.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton3().getText())) {
            return quizView.getAnswerButton3();
        } else if (currentQuestion.getRightAnswer().equalsIgnoreCase(quizView.getAnswerButton4().getText())) {
            return quizView.getAnswerButton4();
        } else {
            return null;
        }
    }

    public void changeToWaiting() {
        quizClient.goToWaitingScene();
    }


}
