package com.quizgame.Controller;

import com.quizgame.Database;
import com.quizgame.Question;
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
    private Database database = new Database();
    private QuizView quizView;
//    private Question question;
    private QuizItem item;

    public QuizController(QuizView quizView) {
        item = database.getItem();
        this.quizView = quizView;
    }

    public void start() {
        database.loadQuestions();
//        question = getRandomQuestion();
        showQuestion(item);

        quizView.getAnswerButton1().setOnAction(this::handle);
        quizView.getAnswerButton2().setOnAction(this::handle);
        quizView.getAnswerButton3().setOnAction(this::handle);
        quizView.getAnswerButton4().setOnAction(this::handle);
    }

    private void handle(ActionEvent actionEvent) {
        if (answeredState == 0) {
            clickAnswerButton((Button) actionEvent.getSource());
        } else {
            nextQuestion();
        }
    }

//    private Question getRandomQuestion() {
//        Question randomQuestion;
//
//
//        Random random = new Random();
//        while(true) {
//            int quizNumber = random.nextInt(database.questionList.size());
//
//            if (storedQuestion[quizNumber] == quizNumber || quizNumber == lastQuestion) {
//                for (int i = 0; i < 4; i++) {
//                    if(storedQuestion[i] == i) {
//                        questionCounter++;
//                    }
//                }
//                if (questionCounter >= 4) {
//                    for (int i = 0; i < storedQuestion.length; i++) {
//                        storedQuestion[i] = 5;
//                        questionCounter = 0;
//                    }
//                }
//            } else {
//                storedQuestion[quizNumber] = quizNumber;
//                randomQuestion = database.questionList.get(quizNumber);
//                lastQuestion = quizNumber;
//                break;
//            }
//        }
//        return randomQuestion;
//    }

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

    void nextQuestion() {
        quizView.getAnswerButton1().setId(".button");
        quizView.getAnswerButton2().setId(".button");
        quizView.getAnswerButton3().setId(".button");
        quizView.getAnswerButton4().setId(".button");
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
