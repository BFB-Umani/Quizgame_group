package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.view.ChoosingSubjectScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ChoosingSubjectSceneController {
    private ChoosingSubjectScene choosingSubjectScene;
    private QuizClient quizClient;

    public ChoosingSubjectSceneController(ChoosingSubjectScene choosingSubjectScene, QuizClient quizClient){
        this.choosingSubjectScene = choosingSubjectScene;
        this.quizClient = quizClient;
        choosingSubjectScene.getSubjectButton1().setOnAction(this::handle);
        choosingSubjectScene.getSubjectButton2().setOnAction(this::handle);
        choosingSubjectScene.getSubjectButton3().setOnAction(this::handle);
    }

    private void handle(ActionEvent actionEvent) {

        Button Subject = (Button) actionEvent.getSource();
        System.out.println(Subject.getText());
        changeToQuizScene();
    }

    public void changeToQuizScene(){
        quizClient.goToQuizScene();
    }

    public void start(){
        choosingSubjectScene.setUp();
    }
}
