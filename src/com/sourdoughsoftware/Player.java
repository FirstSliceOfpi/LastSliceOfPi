package com.sourdoughsoftware;

//import com.sourdoughsoftware.utility.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private String name;
    private Integer roomID;
    boolean hasPi = false;
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
                    piecesOfPi.add(item.getPival());
                    doesPlayerHavePi();
                    System.out.println(hasPi);
                    message = "You added a " + item.getName() + " to your inventory.";
                    break;
                }
            }
        }
        return message;
    }

    public void doesPlayerHavePi() {
        if (sizeOfPi() >= 3) {
            if (piContains("3") && piContains("1") && piContains("4")) {
                setHasPi(true);
            }
        }
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

    public Map<String, String> formatInventory(List<Item> inv) {
        Map<String, String> result = new HashMap<>();
        for (Item item : inv) {
            result.put(item.getName(), item.getPival());
        }
        return result;
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

    public boolean isHasPi() {
        return hasPi;
    }

    public void setHasPi(boolean hasPi) {
        this.hasPi = hasPi;
    }

//     Methods
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
