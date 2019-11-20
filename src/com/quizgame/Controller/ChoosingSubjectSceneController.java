package com.quizgame.Controller;

import com.quizgame.Database;
import com.quizgame.QuizClient;
import com.quizgame.view.ChoosingSubjectScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.Random;

public class ChoosingSubjectSceneController {
    private ChoosingSubjectScene choosingSubjectScene;
    private QuizClient quizClient;
    private String chosenCategory;
    private String[] categories = {"GEOGRAFI", "FOTBOLL", "HISTORIA", "FILM"};
    private boolean nonRepeat;
    private int[] randomCategory = new int[4];
    private int categoryCounter;
    Database db = new Database();
    Random r = new Random();

    public ChoosingSubjectSceneController(ChoosingSubjectScene choosingSubjectScene, QuizClient quizClient) {
        this.choosingSubjectScene = choosingSubjectScene;
        this.quizClient = quizClient;

        nonRepeat = true;
        while (nonRepeat) {

            randomCategory[categoryCounter] = (r.nextInt(4));
            if(categoryCounter == 0) {
                categoryCounter++;
            }
            else{
                if(categoryCounter == 1) {
                    if (!categories[randomCategory[categoryCounter]].equals(categories[randomCategory[0]])) {

                        categoryCounter++;
                    }
                }
                else if(!categories[randomCategory[categoryCounter]].equals(categories[randomCategory[0]]) && !categories[randomCategory[categoryCounter]].equals(categories[randomCategory[1]])) {
                    categoryCounter++;
                }
                if (categoryCounter == 3) {
                    nonRepeat = false;
                }
            }
        }
        choosingSubjectScene.getSubjectButton1().setText(categories[randomCategory[0]]);
        choosingSubjectScene.getSubjectButton2().setText(categories[randomCategory[1]]);
        choosingSubjectScene.getSubjectButton3().setText(categories[randomCategory[2]]);
        choosingSubjectScene.getSubjectButton1().setOnAction(this::handle);
        choosingSubjectScene.getSubjectButton2().setOnAction(this::handle);
        choosingSubjectScene.getSubjectButton3().setOnAction(this::handle);


    }

    private void handle(ActionEvent actionEvent) {

        Button Subject = (Button) actionEvent.getSource();
        db.chosenSubject = Subject.getText();
        System.out.println(db.chosenSubject);
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
