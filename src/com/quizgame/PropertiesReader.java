package com.quizgame;


import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    Properties p = new Properties();

    public PropertiesReader() {
        try {
            p.load(new FileInputStream("resources/config/settings.properties"));
        } catch (Exception e) {
            System.out.println("settings.properties can not be found.");
        }
    }

    public int getRoundsPerGame() {
        return Integer.parseInt(p.getProperty("rounds_per_game", "2"));
    }

    public int getQuestionsPerRound(){
        return Integer.parseInt(p.getProperty("questions_per_round","2"));

    }


}
