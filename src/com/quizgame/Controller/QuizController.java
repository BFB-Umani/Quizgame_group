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
    private QuizView quizView;
    private int lastQuestion = 0;
    private int[] storedQuestion = {5, 5, 5, 5};
    private int questionCounter = 0;
    private int roundCounter = 1;   //---
    private int totalRound = 1;     //---  det ska komma från properties. Genom server?
    private int answeredState = 0;
    private int choiceMode = 0;     //--- 0 = "accept mode"
    private Object fromServer;
    private List<QuizItem> itemPack;
    private QuizItem item;
    private QuizClient quizClient;

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

    private void handle(ActionEvent actionEvent) {
        if (answeredState == 0) {
            clickAnswerButton((Button) actionEvent.getSource());
        }
    }

    private void clickedContinueButton(ActionEvent actionEvent){
        this.questionCounter++;
        if (questionCounter<this.itemPack.size())
            nextQuestion();
        else
            nextRound();

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

        quizView.getContinueButton().setVisible(false);


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

    void nextRound(){
        if (choiceMode == 0) {              // AcceptRound
            System.out.println("AcceptRound är slut  \nKollar om matchen är slut");
            if (isGameOver()) {
                System.out.println("game över: visa färdiga resultScenen");
                System.out.println("SPELA OM?");
            } else                          //det finns en till round!
                System.out.println("game continue:\nSpara resultatet i resultScene, men också summan i en variabel score!");
            choiceMode = 1;             //det blir choiceRound
            roundCounter++;
            System.out.println("new chosingSubjectscene och new QuizView");
            System.out.println("Server ska skicka new subject list och ett nytt Quizpaket");
            System.out.println("choiceMode = 1  (choiceRound)  &   roundCounter++");
        }

        else if (choiceMode == 1) {        //ChoiceRound
            System.out.println("ChoiceRound är slut: då så?");
            System.out.println("Spara resultatet i resultScene, men också summan i en variabel score!");
            System.out.println("Switch player");
            System.out.println("Server måste skicka samma Quizpaket");
        }
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