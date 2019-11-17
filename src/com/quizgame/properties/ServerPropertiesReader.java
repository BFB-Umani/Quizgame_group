package com.quizgame.properties;

public class ServerPropertiesReader extends PropertiesReader {

    public ServerPropertiesReader(){
        loadProperties("resources/config/settings.properties");
    }

    public int getRoundsPerGame() {
        return Integer.parseInt(p.getProperty("rounds_per_game", "2"));
    }

    public int getQuestionsPerRound(){
        return Integer.parseInt(p.getProperty("questions_per_round","2"));
    }

}
