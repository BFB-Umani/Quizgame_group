package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.view.ChoosingSubjectScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.List;


public class ChoosingSubjectSceneController {
    private ChoosingSubjectScene choosingSubjectScene;
    private QuizClient quizClient;
    private String chosenCategory;

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

        Button subject = (Button) actionEvent.getSource();
        quizClient.getServerConnection().sendSubjectToServer(subject.getText());

        quizClient.goToWaitingScene();
    }


    public String getCategory(){
        return chosenCategory;
    }

    public void start() {
        choosingSubjectScene.setUp();
    }
}
