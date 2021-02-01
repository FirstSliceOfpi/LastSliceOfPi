package com.sourdoughsoftware;

//import com.sourdoughsoftware.InputParser;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private String name;
    private Room location;
    private ArrayList<String> inventory;

    // Constructors
    public Player(String name, Room location) {
        this.name = name;
        this.location = location;
        this.inventory = new ArrayList<>();
    }

    // Accessors


    public String getName() {
        return name;
    }

    public Room getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public ArrayList<String> addInventory(String item) {
        Player.this.inventory.add(item);
        return inventory;
    }

    @Override
    public String toString() {
        return "Thank you " + getName() + ", I see that you are in " + getLocation() + ".\nYour current inventory is: " + getInventory().toString();
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
