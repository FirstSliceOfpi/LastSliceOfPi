package com.sourdoughsoftware;

//import com.sourdoughsoftware.InputParser;

import java.util.Scanner;

public class Player {
    private String name;
    private String location;

    // Constructors
    public Player(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Accessors


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Thank you " + getName() + ", I see that you are in " + getLocation() + ".";
    }

//    public static void main(String[] args) {
//        InputParser prompt = new InputParser(new Scanner(System.in));
//        Player player1 = new Player(
//                prompt.prompt("What is your name?"),
//                "Room 0"
//        );
//
//        String output = "Thank you " + player1.getName() + ", I see that you are in " + player1.getLocation() + ".";
//        System.out.println(output);
//    }
}
