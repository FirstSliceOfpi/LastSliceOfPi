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
        List<Room> rooms = objectFromXml.parseRoom();
        List<Item> items = objectFromXml.parseItems();

        for (Room room : rooms) {
            for (Item item : items) {
                if (room.getDescription().contains(item.getName())) {
                    room.addToRoom(item);
                }
            }
        }
//        System.out.println(rooms.toString());
        System.out.println(items.toString());
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
            if (userCommands[0].equalsIgnoreCase("look") ||
                    userCommands[0].equalsIgnoreCase("show") ||
                    userCommands[0].equalsIgnoreCase("inspect") ||
                    userCommands[0].equalsIgnoreCase("examine")) {
                if (Nouns.getAllDirections().contains(userCommands[1])) {
                    System.out.println(Look.roomLook(p1.getPlayerRoomID(), userCommands[1], rooms, p1));
                } else {
                    System.out.println(Look.itemLook(p1.getPlayerRoomID(), userCommands[1], rooms, items));
                }
                continue;
            }
            if (userCommands[0].equalsIgnoreCase("help") || userCommands[0].equalsIgnoreCase("h")) {
                System.out.println("Commands:\n" + Verbs.getAllVerbs().toString() + "\nAccess this " +
                        "help menu at any time: help or h.\nQuit at any time: quit or q.");
                continue;
            }
            if (userCommands[0].equalsIgnoreCase("quit") || userCommands[0].equalsIgnoreCase("q")) {
                setGameOver(true);
                System.out.println("Thanks for playing!");
                continue;
            }
            if (userCommands[0].equalsIgnoreCase("hint")) {
                System.out.println("You should find something yummy to eat.");
                continue;
            }
            if (Verbs.getAllVerbs().contains(userCommands[0].toLowerCase()) && Nouns.getAllNouns().contains(userCommands[1].toLowerCase())) {
                String message = processInteraction(rooms, userCommands);
                // output verb interaction message or a failure message
                System.out.println(message);
                continue;
            }

            System.out.println(roomDescription);
        } while (!gameOver);
    }

    private String processInteraction(List<Room> rooms, String[] userCommands) {
        String message = "You try to " + userCommands[0].toLowerCase() + " the " + userCommands[1].toLowerCase() + " but nothing happens.";
        // get room items against player location
        List<Item> itemsToCheck = getPlayerRoomItems(p1.getPlayerRoomID(), rooms);

        if (userCommands[0].equalsIgnoreCase("take") ||
                userCommands[0].equalsIgnoreCase("get")) {
            // Get player item
            Item playerItem = new Item();
            for (Item item : itemsToCheck) {
                if (item.getName().equalsIgnoreCase(userCommands[1])) {
                    playerItem = item;
                    break;
                }
            }
            message = TakeItem.takeItem(p1, rooms, playerItem);
        } else {
            // check item for verb interaction map
            for (Item item : itemsToCheck) {
                if (item.getVerbInteraction().containsKey(userCommands[0].toLowerCase()) &&
                        item.getName().equalsIgnoreCase(userCommands[1])) {
                    message = item.getVerbInteraction().get(userCommands[0].toLowerCase());
                    break;
                }
            }
        }
        return message;
    }

    private List<Item> getPlayerRoomItems(Integer playerRoomID, List<Room> rooms) {
        List<Item> specificItems = new ArrayList<>();
        for (Room room : rooms) {
            if (playerRoomID.equals(room.getRoomID())) {
                specificItems = room.getRoomItems();
            }
        }
        return specificItems;
    }

    private String getRoomDescription(Integer playerLocation, List<Room> rooms) {
        String result = "";
        for (Room room : rooms) {
            if (playerLocation.equals(room.getRoomID())) {
                if (room.getDescription() == null) {
                    room.setDescription("Room " + room.getRoomID() + " is missing a description!");
                    result = room.getDescription();
                } else {
                    result = room.getDescription();
                }
            }
        }
        return result;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}

