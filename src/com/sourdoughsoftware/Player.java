package com.sourdoughsoftware;

//import com.sourdoughsoftware.InputParser;

import java.util.ArrayList;

public class Player {
    private String name;
    private Integer roomID;
    private ArrayList<Item> inventory;

    // Constructors
    public Player(String name, Integer roomID) {
        this.name = name;
        this.roomID = roomID;
        this.inventory = new ArrayList<>();
    }

    // Accessors
    public String getName() {
        return name;
    }

    public Integer getPlayerRoomID() {
        return roomID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public ArrayList<Item> addInventory(Item item) {
        inventory.add(item);
        return inventory;
    }

    @Override
    public String toString() {
        return "Thank you " + getName() + ", I see that you are in " + getPlayerRoomID() + ".\nYour current inventory is: " + getInventory().toString();
    }
}
