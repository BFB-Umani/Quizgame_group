package com.quizgame.Controller;

import com.quizgame.QuizResult;
import com.quizgame.Round;
import com.quizgame.client.QuizClient;
import com.quizgame.properties.ServerPropertiesReader;
import com.quizgame.view.ResultScene;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

public class ResultSceneController {
    private ResultScene resultScene;
    private QuizClient quizClient;
    private ServerPropertiesReader prop = new ServerPropertiesReader();
    private QuizResult quizResult;


    public ResultSceneController(ResultScene resultScene, QuizClient quizClient, QuizResult quizResult){
        this.resultScene = resultScene;
        this.quizClient = quizClient;
        this.quizResult = quizResult;
        quizResult.rounds = new ArrayList<>();

    }

    public void start(){
        setResult();
        resultScene.setUp();
        resultScene.getContinueB().setOnAction(l -> {
            if(resultScene.getContinueB().getText().equalsIgnoreCase("continue")) {
                quizClient.getServerConnection().sendResultComplete(true);
                quizClient.getQuizController().setQuizCounter();
            }
            else if(resultScene.getContinueB().getText().equalsIgnoreCase("Quit")) {
                System.exit(0);
            }
        });
    }
    public void setResult() {
        if(prop.getRoundsPerGame() > 5) {
            ScrollPane s1 = new ScrollPane(resultScene.getPlayerLayout());
            resultScene.getDesignLayout().getChildren().add(s1);
            s1.setFitToHeight(true);
            s1.setFitToWidth(true);
            s1.setId("scrollpane");
            System.out.println("adding scrollpane");
        }
    }


}
