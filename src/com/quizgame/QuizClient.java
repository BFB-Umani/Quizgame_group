package com.quizgame;

import com.quizgame.Controller.*;
import com.quizgame.view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class QuizClient extends Application {

    private static int PORT = 12345;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Scene scene;
    private ChoosingSubjectScene choosingSubjectScene;
    private WaitingScene waitingScene;
    private ResultScene resultScene;
    private QuizView quizView;
    private QuizClient quizClient;
    private Object quest;
    private QuizController quizController;
    private WaitingSceneController waitingSceneController;
    private int playerNumber;
    private boolean doneRound = false;
    private int rond = 1;
    public QuizServer currentPlayer;


    public QuizClient() {    //NO TOUCH THIS!!!!
    }

    public QuizClient(String ip) {
        try {
            socket = new Socket(ip, PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {

        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        quizClient = new QuizClient("127.0.0.1");


        stage.setTitle("Quizgame");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/quizIcon.png"));

        quizView = new QuizView();
        StartScene startScene = new StartScene();
        choosingSubjectScene = new ChoosingSubjectScene();
        waitingScene = new WaitingScene();
        resultScene = new ResultScene();

        scene = new Scene(startScene.getDesignLayout(), 480, 620);
        scene.getStylesheets().add(QuizClient.class.getResource("Style.css").toExternalForm());
        stage.setScene(scene);

        StartSceneController startSceneController = new StartSceneController(startScene, this);
        startSceneController.start();


        ChoosingSubjectSceneController choosingSubjectSceneController = new ChoosingSubjectSceneController(choosingSubjectScene, this);
        choosingSubjectSceneController.start();

        waitingSceneController = new WaitingSceneController(waitingScene, this);
        waitingSceneController.start();

        ResultSceneController resultSceneController = new ResultSceneController(resultScene, this);
        resultSceneController.start();

        quizController = new QuizController(quizView, this);
        quizController.start();

        stage.show();


    }

    public void sendMsg(String output) {
        System.out.println(output);
        try {
            quizClient.out.writeObject(output);
            getMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getMsg() {
        Object fromServer;
        try {
            while ((fromServer = quizClient.in.readObject()) != null) {
                if (fromServer instanceof List) {
                    System.out.println("I got Quizitem");
                    quest = fromServer;
                    System.out.println(quest);
                    quizController.loadQuestion(quest);
                } else if (fromServer instanceof String) {
                    String message = (String) fromServer;
                    System.out.println(message);
                    System.out.println("I got STring item");
                } else if (fromServer instanceof int[]) {
                    int[] gameInfo = (int[]) fromServer;
                    System.out.println(gameInfo[0]);
                    System.out.println(gameInfo[1]);
                    quizController.loadGameInfo(gameInfo);
                    System.out.println("I got int item");
                } else if (fromServer instanceof Integer) {
                    playerNumber = (Integer) fromServer;
                    System.out.println(playerNumber);
                }
                else if(fromServer instanceof Boolean) {
                    System.out.println("I got a boolean");
                    waitingSceneController.setStart(true);
                }
                break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getQuest() {
        return quest;
    }

//    public void setDoneRound(boolean doneRound){
//        this.doneRound = doneRound;
//    }

    public boolean getDoneRound(){
        return this.doneRound;
    }


    public void goToChoseSubjectScene() {
        if ((rond & 1) == 1) {
            if (playerNumber == 1) {
                scene.setRoot(choosingSubjectScene.getDesignLayout());
            } else {
                goToWaitingScene();
            }
        } else {
            if (playerNumber == 2) {
                scene.setRoot(choosingSubjectScene.getDesignLayout());
            } else {
                goToWaitingScene();
            }
        }

    }

    public void goToQuizScene() {
        scene.setRoot(quizView.getDesignLayout());
    }

    public void goToWaitingScene() {
        scene.setRoot(waitingScene.getDesignLayout());
    }

    public void goToResultScene(){
        scene.setRoot(resultScene.getDesignLayout());
    }

    public static void main(String[] args) {
        launch(args);
    }


}

