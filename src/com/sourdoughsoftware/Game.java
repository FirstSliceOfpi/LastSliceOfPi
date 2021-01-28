package com.sourdoughsoftware;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        room1.addExit("e", room2);
        InputParser prompt = new InputParser(new Scanner(System.in));
        WelcomeScreen.getWelcomeMessage();
        Story.readFile();
        System.out.println("This is gonna be great.");
        String userName = prompt.prompt("Enter your name adventurer\n>> ");
        Player player1 = new Player(userName, room1);
        System.out.println(player1.toString());
        player1.addInventory("Knife");
        System.out.println(player1.toString());
        String[] userEntry = prompt.promptAction(">> ");
        System.out.println(Arrays.toString(userEntry));
        System.out.println(userEntry.length);
        System.out.println(userEntry[0] + "," + userEntry[1]);


        if (userEntry[0].equals("look")) {
            if (userEntry[1].equals("room")) {
                String currentRoom = player1.getLocation();
                // turn this if into a for loop over the rooms checking against descriptions
                // if (currentRoom.equals(room[i].getName()))
                if (currentRoom.equals(room1.getName())) {
                    System.out.println(room1.getDescription());
                }
            }
        }
        // put into a movePlayer function that sets the players location and loads the new locations
        // description, then restarts the game loop
        if (userEntry[0].equals("go")) {
            if (userEntry[1].equals("e")) {
                String currentRoom = player1.getLocation();
                // turn this if into a for loop over the rooms checking against descriptions
                // if (currentRoom.equals(room[i].getName()))
                if (currentRoom.equals(room1.getName())) {
                    player1.setLocation(room2.getName());
                    System.out.println("Player in: " + player1.getLocation());
                }
            }
        }

    }


//        System.out.println(userEntry);


}
