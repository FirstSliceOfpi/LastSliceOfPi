package com.sourdoughsoftware;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Game {
    //    Maybe this has a board with a list of rooms
    private Player p1;
    private InputParser parser;
    private boolean gameOver = false;

    public Game() {
        //setup methods
        parser = new InputParser(new Scanner(System.in));
    }

    // game loop
    public void start() throws IOException, SAXException, ParserConfigurationException {
        System.out.println(WelcomeScreen.getWelcomeMessage());
        List<Room> rooms = PopulateRoomsFromXML.parseRoomXML();
//        System.out.println(rooms.toString());
//        get room description
        String userName = parser.prompt("Enter your name adventurer\n>> ");
        p1 = new Player(userName, 0);
        String roomDescription = getRoomDescription(p1.getPlayerRoomID(), rooms);
        System.out.println(roomDescription);

        do {
//            give player description of the scene
//            take valid player commands or inform player of invalid choices
//            change game state
//            inform player of the changes
//            loop

            String[] userCommands = parser.promptAction(">> ");
            if (userCommands[0].equals("go")) {
                System.out.println(RoomChange.changeRoom(p1.getPlayerRoomID(), userCommands[1], rooms, p1));
                roomDescription = getRoomDescription(p1.getPlayerRoomID(), rooms);
            }
            if (userCommands[0].equalsIgnoreCase("help") || userCommands[0].equalsIgnoreCase("h")) {
                System.out.println("Goal - explore the world using 'go' and a direction, read the story, and figure " +
                                "out the goal.\nCommands:\n" + Verbs.getAllVerbs().toString() + "\nAccess this " +
                                "help menu at any time: help or h.\nQuit at any time: quit or q.");
                continue;
            }
            if (userCommands[0].equalsIgnoreCase("quit") || userCommands[0].equalsIgnoreCase("q")) {
                setGameOver(true);
                System.out.println("Thanks for playing!");
                continue;
            }
            System.out.println(roomDescription);
        } while (!gameOver);

    }

    private String getRoomDescription(Integer playerLocation, List<Room> rooms) {
        String result = "";
        for (Room room : rooms) {
            if (playerLocation.equals(room.getRoomID())) {
                if (room.getDescription() == null){
                    room.setDescription("Room " + room.getRoomID() + " is missing a description!");
                    result = room.getDescription();
                }
                else{
                    result = room.getDescription();
                }
            }
        } return result;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}

