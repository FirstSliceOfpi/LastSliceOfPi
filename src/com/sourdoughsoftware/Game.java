package com.sourdoughsoftware;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        InputParser prompt = new InputParser(new Scanner(System.in));
        WelcomeScreen.getWelcomeMessage();
        Story.readFile();
        System.out.println("This is gonna be great.");
        String userName = prompt.prompt("Enter your name adventurer\n>> ");
        Player player1 = new Player(userName,"Room 0");
        System.out.println(player1.toString());
        player1.addInventory("Knife");
        System.out.println(player1.toString());
        String userEntry = prompt.prompt(">> ");
//        System.out.println(userEntry);


//        System.out.println(userEntry);






    }
}
