package com.quizgame.client;

import com.quizgame.ChooseSubjectObject;
import com.quizgame.Controller.QuizController;
import com.quizgame.TransferObject;
import com.quizgame.properties.ClientPropertiesReader;

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

    public ServerConnection(QuizClient quizClient){
        this.quizClient = quizClient;
    }


    public void connect() throws IOException{
        socket = new Socket(clientPropertiesReader.getHostName(),clientPropertiesReader.getPortNr() );
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }


    public void sendNameToServer(String name){
        TransferObject transferObject = new TransferObject();
        transferObject.text = name;
        try{
        out.writeObject(transferObject);
        out.flush();
        }catch (IOException e){
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
                    quizClient.goToChoseSubjectScene(threeSubjects);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
