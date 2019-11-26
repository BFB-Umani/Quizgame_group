package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.view.ChoosingSubjectScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Random;

public class ChoosingSubjectSceneController {
    private ChoosingSubjectScene choosingSubjectScene;
    private QuizClient quizClient;
    private String chosenCategory;
    //private String[] categories = {"GEOGRAFI", "FOTBOLL", "HISTORIA", "FILM"};
    private boolean nonRepeat;
    private int[] randomCategory = new int[4];
    private int categoryCounter;

    Random r = new Random();

    public void showSubjects(List<String> subjectsList){
        choosingSubjectScene.getSubjectButton1().setText(subjectsList.get(0));
        choosingSubjectScene.getSubjectButton2().setText(subjectsList.get(1));
        choosingSubjectScene.getSubjectButton3().setText(subjectsList.get(2));

    }

    public ChoosingSubjectSceneController(ChoosingSubjectScene choosingSubjectScene, QuizClient quizClient) {
        this.choosingSubjectScene = choosingSubjectScene;
        this.quizClient = quizClient;



        choosingSubjectScene.getSubjectButton1().setOnAction(this::handle);
        choosingSubjectScene.getSubjectButton2().setOnAction(this::handle);
        choosingSubjectScene.getSubjectButton3().setOnAction(this::handle);


    }

    private void handle(ActionEvent actionEvent) {

        Button Subject = (Button) actionEvent.getSource();
//        db.chosenSubject = Subject.getText();
//        System.out.println(db.chosenSubject);
        changeToQuizScene();
    }

    public void changeToQuizScene() {
        quizClient.goToQuizScene();
    }

    public String getCategory(){
        return chosenCategory;
    }

    public void start() {
        choosingSubjectScene.setUp();
    }
}
