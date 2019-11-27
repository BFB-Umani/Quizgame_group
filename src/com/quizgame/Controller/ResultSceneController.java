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

    public ResultSceneController(ResultScene resultScene, QuizClient quizClient){
        this.resultScene = resultScene;
        this.quizClient = quizClient;

    }

    public void start(){
        goToResultScene(prop.getRoundsPerGame());
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

    public void goToResultScene(int rounds){

        QuizResult quizResult = quizClient.getQuizResult();
        quizResult.rounds = new ArrayList<>();

        if(rounds > 5) {
            ScrollPane s1 = new ScrollPane(resultScene.getPlayerLayout());
            resultScene.getDesignLayout().getChildren().add(s1);
            s1.setFitToHeight(true);
            s1.setFitToWidth(true);
            s1.setId("scrollpane");
            System.out.println("adding scrollpane");
        }

        for(int i = 0; i < rounds; i++) {

//            Round round1 = new Round();
//            round1.round = 1;
//            round1.questionsPerRound = 3;
//            round1.player1Score = 3;
//            round1.player2Score = 2;
            quizResult.rounds.add(new Round());
            quizResult.rounds.get(i).round = i+1;

        }

        quizClient.getQuizResult().player1Name = "Jessie";
        System.out.println("showing resultScene");
        resultScene.showResult(quizResult);
    }
}
