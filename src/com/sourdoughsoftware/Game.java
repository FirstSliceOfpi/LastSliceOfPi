package com.sourdoughsoftware;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static Room setupRooms() {
        Room room3 = new Room("rapunzel");
        Room room1 = new Room("snow white");
        Room room6 = new Room("hansel and gretel");
        Room room2 = new Room("frog prince");
        Room room9 = new Room("sleeping beauty");
        Room room5 = new Room("cinderella");
        Room room4 = new Room("rumplestiltskin");
        Room room7 = new Room("little red riding hood");
        Room room8 = new Room("the shoemaker");
        Room room0 = new Room("start");
        Room roomDot = new Room(".");

        room0.addExit("northwest", room1);
        room0.addExit("north", room2);
        room0.addExit("northeast", room3);
        room1.addExit("west", room3);
        room1.addExit("north", room4);
        room1.addExit("east", room2);
        room1.addExit("southeast", room0);
        room2.addExit("north", room5);
        room2.addExit("south", room0);
        room2.addExit("east", room3);
        room2.addExit("west", room1);
        room3.addExit("north",room6);
        room3.addExit("south", roomDot);
        room3.addExit("east", room1);
        room3.addExit("west", room2);
        room3.addExit("southwest", room0);
        room4.addExit("north", room7);
        room4.addExit("south", room1);
        room4.addExit("east", room5);
        room5.addExit("north", room8);
        room5.addExit("south", room2);
        room5.addExit("east", room6);
        room5.addExit("west", room4);
        room6.addExit("north", room9);
        room6.addExit("south", room3);
        room6.addExit("west", room5);
        room7.addExit("north", room9);
        room7.addExit("east", room8);
        room7.addExit("south", room4);
        room8.addExit("waet", room7);
        room8.addExit("south", room5);
        room8.addExit("east", room9);
        room7.addExit("north", room7);
        room7.addExit("west", room8);
        room7.addExit("south", room6);
        roomDot.addExit("north", room3);

        return room0;


    }

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

//        System.out.println(userEntry);
      
}
