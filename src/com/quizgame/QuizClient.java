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

package com.quizgame;

import com.quizgame.Controller.QuizController;
import com.quizgame.view.QuizView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class QuizClient extends Application {

    private static int PORT = 12345;
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;

    public QuizClient() {    //NO TOUCH THIS!!!!
    }

    public QuizClient(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Quizgame");
        QuizView quizView = new QuizView();

        Scene scene = new Scene(quizView.getDesignLayout(),480,620);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/quizIcon.png"));
        quizView.setUp();
        scene.getStylesheets().add(QuizClient.class.getResource("Style.css").toExternalForm());

        QuizClient quizClient = new QuizClient("127.0.0.1");

        // item ska vara en List<QuizItem>
        // vi ska ha den i ROUND_STATE
        // HÄR  ska det bli bara en response (Objekt fromServer t.e.)
        // som vi kollar och urskjilier sen (med en cast)
        QuizItem item = (QuizItem) quizClient.in.readObject();
        QuizController quizController = new QuizController(quizView, item);
        quizController.start();
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
