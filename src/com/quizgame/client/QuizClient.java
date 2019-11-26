/*
package com.quizgame;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class QuizClient {

    private static int PORT = 12345;
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;
    //----------------------------------- allt det ska bli FX

    private JFrame frame = new JFrame("QuizGame");
    private JLabel label = new JLabel("");
    //----------------------------------- allt det ska bli FX


    public QuizClient(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new ObjectInputStream(socket.getInputStream());
    }


    public void play() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            //mystiskt men det känns viktigt
            //String serverAddress = (args.length == 0) ? "localhost" : args[1];


            QuizClient client = new QuizClient("localhost");
            QuizItem item = (QuizItem) client.in.readObject();
            //----------------------------------- allt det ska bli FX
            //provar om client får welcome from server
            //client.frame.setTitle(client.in.readLine());
            client.label.setText(item.getQuestion());
            client.frame.add(client.label);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(600, 800);
            client.frame.setVisible(true);
            client.frame.setResizable(true);

            //----------------------------------- allt det ska bli FX
            //



            client.play();

            int playagain = JOptionPane.showConfirmDialog(null, "Again?");
            if (!(playagain == 0)) {
                client.frame.dispose();
                System.exit(0);
            }
            client.frame.dispose();


            //if (!client.wantsToPlayAgain()) {SKAPA METODEN!
            //   break;
        }
    }
}
*/

package com.quizgame.client;

import com.quizgame.Controller.*;
import com.quizgame.QuizItem;
import com.quizgame.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class QuizClient extends Application {

    private Scene scene;
    private ChoosingSubjectScene choosingSubjectScene;
    private WaitingScene waitingScene;
    private ResultScene resultScene;
    private QuizView quizView;
    private ServerConnection serverConnection;
    private QuizController quizController;
    private ChoosingSubjectSceneController choosingSubjectSceneController;


    public QuizClient() {    //NO TOUCH THIS!!!!
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Quizgame");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/quizIcon.png"));

        quizView = new QuizView();
        StartScene startScene = new StartScene();
        choosingSubjectScene = new ChoosingSubjectScene();
        waitingScene = new WaitingScene();
        resultScene = new ResultScene();

        scene = new Scene(startScene.getDesignLayout(),480,620);
        // Testar andra scener.
        //scene = new Scene(waitingScene.getDesignLayout(),480,620);
        //scene = new Scene(resultScene.getDesignLayout(),480,620);
        scene.getStylesheets().add(QuizClient.class.getResource("Style.css").toExternalForm());
        stage.setScene(scene);

        StartSceneController startSceneController = new StartSceneController(startScene,this);
        startSceneController.start();

        choosingSubjectSceneController = new ChoosingSubjectSceneController(choosingSubjectScene,this);
        choosingSubjectSceneController.start();

        WaitingSceneController waitingSceneController = new WaitingSceneController(waitingScene);
        waitingSceneController.start();

        quizController = new QuizController(quizView,this);
        quizController.start();

        ResultSceneController resultSceneController = new ResultSceneController(resultScene);
        resultSceneController.start();

        serverConnection = new ServerConnection(this);
        serverConnection.connect();
        serverConnection.start();

        stage.show();
    }

    public void goToChoseSubjectScene(List<String> subjectsList){
        scene.setRoot(choosingSubjectScene.getDesignLayout());
        choosingSubjectSceneController.showSubjects(subjectsList);

//        choosingSubjectScene.getSubjectButton1().setText(subjectsList.get(0));
//        choosingSubjectScene.getSubjectButton2().setText(subjectsList.get(1));
//        choosingSubjectScene.getSubjectButton3().setText(subjectsList.get(2));
    }

    public void goToQuizScene(List<QuizItem> questions) {
        scene.setRoot(quizView.getDesignLayout());
        quizController.loadQuestions(questions);

    }

    public void goToWaitingScene(){
        scene.setRoot(waitingScene.getDesignLayout());
    }

    public void goToResultScene(){
        scene.setRoot(resultScene.getDesignLayout());
    }

    public ServerConnection getServerConnection(){
        return serverConnection;
    }
    public static void main(String[] args) {
        launch(args);
    }



}
