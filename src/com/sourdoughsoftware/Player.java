package com.sourdoughsoftware;

//import com.sourdoughsoftware.utility.InputParser;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Integer roomID;
    private List<Item> inventory;
    private List<String> piecesOfPi;

    // Constructors
    public Player(String name, Integer roomID) {
        this.name = name;
        this.roomID = roomID;
        this.inventory = new ArrayList<>();
        this.piecesOfPi = new ArrayList<>();
    }

    public String takeItem(List<Room> rooms, Item item) {
        String message = "You  see a " + item.getName();
        for (Room r1 : rooms) {
            if (this.getPlayerRoomID().equals(r1.getRoomID())) {
                List<Item> items = r1.getRoomItems();
                if (items.contains(item)) {
                    r1.removeItem(item);
                    this.addInventory(item);
                    message = "You added a " + item.getName() + " to your inventory.";
                    System.out.println(r1.getRoomItems().toString());
                    System.out.println(this.getInventory().toString());
                    break;
                }
            }
        }
        return message;
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

    public List<Item> getInventory() {
        return inventory;
    }

    public List<Item> addInventory(Item item) {
        inventory.add(item);
        return inventory;
    }

    public int sizeOfPi() {
        return piecesOfPi.size();
    }

    public boolean piContains(String value) {
        return piecesOfPi.contains(value);
    }

//    // Methods
//    public String getPrettyInventory() {
//        for (Item eachItem: inventory) {
//            for (int i = 0; i < eachItem[0].length(); i++ ) {
//
//            }
//
//        }
//    }

    @Override
    public String toString() {
        return "Thank you " + getName() + ", I see that you are in " + getPlayerRoomID() + ".\nYour current inventory is: " + getInventory().toString();
    }
}
