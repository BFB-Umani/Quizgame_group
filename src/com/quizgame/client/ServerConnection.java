package com.quizgame.client;

import com.quizgame.*;
import com.quizgame.properties.ClientPropertiesReader;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerConnection extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientPropertiesReader clientPropertiesReader = new ClientPropertiesReader();
    private QuizClient quizClient;
    private List<QuizItem> questions;

    public ServerConnection(QuizClient quizClient){
        this.quizClient = quizClient;
    }


    public void connect() throws IOException{
        socket = new Socket(clientPropertiesReader.getHostName(),clientPropertiesReader.getPortNr() );
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }


    public void sendNameToServer(String name){
        SetNameObject setNameObject = new SetNameObject();
        setNameObject.text = name;
        try{
        out.writeObject(setNameObject);
            System.out.println("Sending name to server");
        out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendSubjectToServer(String subject){
        ChosenSubjectObject chosenSubjectObject = new ChosenSubjectObject();
        chosenSubjectObject.subject = subject;
        try{
            out.writeObject(chosenSubjectObject);
            System.out.println("sending subject to server");
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendRoundComplete(boolean done) {
        try {
            out.writeObject(done);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (true) {

            try {
                Object object = in.readObject();
                if(object instanceof ChooseSubjectObject){
                    List<String> threeSubjects = ((ChooseSubjectObject) object).subjects;
                    System.out.println("Client el subjects");
                    Platform.runLater(() -> quizClient.goToChoseSubjectScene(threeSubjects)); // main thread runs this when it has time.
                }
                else if(object instanceof QuestionsBySubjectObject){
                    questions =((QuestionsBySubjectObject) object).questions;
                    System.out.println("Client el questions");
                    Platform.runLater(() -> quizClient.goToQuizScene(questions)); // just in case that main thread is busy. Thank you google!
                }
                if(object instanceof Boolean) {
                    System.out.println("Client el boolean");
                    Platform.runLater(() -> quizClient.goToQuizScene(questions));
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
