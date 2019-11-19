package com.quizgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    /* private static final String[] SUBJECT = {"subject0", "subject1"};*/


    QuizItem item;
    List<QuizItem> allItems = new ArrayList<>();
    List<QuizItem> geografi;
    List<QuizItem> fotboll;
    List<QuizItem> historia;
    List<QuizItem> film;
    List<QuizItem> itemPack = new ArrayList<>();


    private static final String[] QUESTION = {
            //GEOGRAFI
            "Var ligger Taj Mahal?",
            "Hur lång är ekvatorn?",
            "I vilket land ligger Kilimanjaro?",
            "Till vilken stad var Titanic på väg när hon gick på ett isberg och sjönk?",
            "Vad heter Perus huvudstad?",
            "I vilket land är Ulan Bator huvudstad?",
            "På vilken ö ligger vulkanen Teide?",
            "Vilken stad går under smeknamnet \"Mellanöstern Paris\"?",
            "Vad heter världen högsta byggnad (2018)?",
            "I vilken italiensk stad sägs pizzan var född?",

            //FOTBOLL
            "Vilket nummer hade David Beckham i Manchester United?",
            "Vem var det som efterträdde Sven Göran Eriksson (Svennis) som Englands förbundskapten?",
            "Vem vann skytteligan i VM 2002?",
            "Vilken italiensk fotbollsklubb har forne storspelaren Teddy Lucic spelat för?",
            "Från vilken engelsk stad kommer Tottenham?",
            "Från vilken stad kommer Elfsborg",
            "Vad heter Chelseas hemmaarena?",
            "Vilken klubb spelade Zinedine Zidane för innan han gick till Juventus?",
            "Vilken nation tog hem VM-guldet 2002?",
            "Vem spottade på Rudi Völler i matchen Holland mot Tyskland i VM 1990?",

            //HISTORISKA ÅRTAL
            "Gustav Vasa väljs till kung?",
            "Statsminister Olof Palme mördas?",
            "Stockholms blodbad där Kristian Tyrann avrättar hundra människor på Stortorget?",
            "Kung Gustav III mördas på en operamaskerad i Stockholm?",
            "I Ådalen skjuts, under våldsamma kravaller, 5 personer ihjäl av militär?",
            "Karl XII dör under en misslyckad belägring av Fredrikshald i Norge?",
            "Sverige folkomröstar om inträde i Eu?",
            "Kvinnor i Sverige får allmän rösträtt?",
            "Heliga Birgitta helgon­förklaras av påven?",
            "Sovjetiska ubåten U 137 går på grund i Karlskronas skärgård?",

            //FILM
            "Vad hette Leonardo DiCaprios rollkaraktär i filmen Titanic?",
            "Vilken skådespelare spelade James Bond i filmen Tomorrow Never Dies?",
            "Vad var den engelska titeln på första filmen i Tolkiens Sagan om ringen-trilogin?",
            "Geena Davis spelade Thelma i filmen Thelma & Louise från 1991. Vilken skådis spelade Louise?",
            "Meryl Streep spelar hemmafrun Francesca Johnson och förälskar sig i en fotograf i denna film?",
            "Vilket år vann filmen Slumdog Millionaire Oscar för bästa film?",
            "Vad heter skådespelerskan i La la land som vann Oscar för bästa kvinnliga huvudroll?",
            "Från vilken film är följande klassiska citat? \"Why so serious?\"?",
            "Vilken bilmodell körde Simon Templar i filmen Helgonet från 1962?",
            "Vem skrev boken Hundraåringen som klev ut genom fönstret och försvann som sedan blev film 2013?"


    };

    private static final String[] RIGHT_ANSWER = {
            //GEOGRAFI
            "Indien",
            "4 000 mil",
            "Tanzania",
            "New York",
            "Lima",
            "Mongoliet",
            "Teneriffa",
            "Beirut",
            "Burj Khalifa",
            "Neapel",

            //FOTBOLL
            "7",
            "Steve McClaren",
            "Ronaldo",
            "Bologona",
            "London",
            "Borås",
            "Stamford Bridge",
            "Bordeaux",
            "Brasilien",
            "Frank Rijkaard",

            //HISTORISKA ÅRTAL
            "1523",
            "1986",
            "1520",
            "1792",
            "1931",
            "1718",
            "1994",
            "1919",
            "1391",
            "1981",

            //FILM


    };
    private static final String[] WRONG_ANSWER = {
            // GEOGRAFI
            "Kina", "Iran", "Turkiet",
            "40 000 mil", "400 000 mil", "400 mil",
            "Uganda", "Kenya", "Ghana",
            "Liverpool", "Boston", "Philadelphia",
            "Santa Cruz", "Bogota", "Sao Paulo",
            "Uzbekistan", "Kazakhstan", "Pakistan",
            "Mallorca", "Kreta", "Santorini",
            "Bagdad", "Jerusalem", "Damaskus",
            "Shanghai Tower", "One World Trade Center", "Tokyo Skytree",
            "Rom", "Pisa", "Genoa",

            // FOTBOLL
            "9", "10", "23",
            "Kevin Keegan", "Fabio Capello", "Roy Hodgson",
            "Rivaldo", "Miroslav Klose", "Christian Vieri",
            "Sampdoria", "Torino", "Parma",
            "Liverpool", "Birmingham", "Manchester",
            "Jönköping", "Halmstad", "Karlskrona",
            "Emirates Stadium", "St James' Park", "White Hart Lane",
            "Real Madrid", "Lyon", "Marseille",
            "Tyskland", "Italien", "Spanien",
            "Ruud Gullit", "Marco Van Basten", "Ronald Koeman",

            //HISTORISKA ÅRTAL
            "1533", "1543", "1556",
            "1982", "1984", "1988",
            "1510", "1459", "1420",
            "1654", "1667", "1772",
            "1929", "1933", "1939",
            "1728", "1732", "1734",
            "1993", "1995", "1996",
            "1909", "1939", "1912",
            "1423", "1549", "1756",
            "1977", "1969", "1983",

            //FILM
            "Jay Gatsby", "Rick Dawson", "Caledon Hockley",
            "Daniel Craig", "Timothy Dalton", "Roger Moore",
            "The Lords Of The Ring", "The War Of The Ring", "The Journey To Mordor",
            "Meryl Streep", "Julia Robert", "Meg Ryan",
            "Mamma Mia!", "The Deer Hunter", "Djävulen Bär Prada",
            "2010", "2009", "2012",
            "Natalie Portman", "Julianne Moore", "Brie Larson",
            "De misstänkta", "Gudfadern", "Titanic",
            "Pontiacs TRANS AM", "Lotus Espirit S1", "Ford Mustang GT",
            "Jonas Johansson", "Johan Jonasson", "Johan Johansson"
    };






    public Database() {
        for (int indexQuestion = 0; indexQuestion < QUESTION.length; indexQuestion++) {

            //skapar en tillfälligt wrongAnswer list som blir parameter i new QuizItem
            ArrayList<String> wrongAnswer = new ArrayList<>();
            for (int indexWrongAnswer = 0; indexWrongAnswer < 3; indexWrongAnswer++)
                wrongAnswer.add(WRONG_ANSWER[(3 * indexQuestion) + indexWrongAnswer]);  //koordinerar 10 String(Q) med 30 String(A)
            QuizItem item = new QuizItem(QUESTION[indexQuestion], RIGHT_ANSWER[indexQuestion], wrongAnswer);
            allItems.add(item);
        }
        geografi = allItems.subList(0,9);                      // numbers... :( det blir 10 items utan att kunna förändra antalet!!!
        fotboll = allItems.subList(10,19);           // men det är bra förtillfället :)
        historia = allItems.subList(20, 29);
        film = allItems.subList(30, 39);
        //bara för att skicka något
    }

    //inte längre getitem: nu skickar vi ett helt paket med 4(n) frågor (listan är shuffled) om italienska köket.
    public List<QuizItem> getItemPack(){    // n-frågorspaket om en särskild subject
        Collections.shuffle(fotboll);//italienskKoket ska bli en variabel som beror på users val
        for (int i=0; i<4; i++)             // till i<4  4 ska bli en variabel som beror på properties
            itemPack.add(fotboll.get(i));
        //eller utan loop
        /*questionList = italienskKöket.subList(0,4);*/
        return itemPack;
    }


}