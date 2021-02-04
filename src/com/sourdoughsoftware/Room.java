package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates a Room to be used to create the game world. Rooms have exits and can also contain items.
 * @author Tyrone Moore, Kelson Smith, and Gina Villegas
 * @version 1.0.0
 */
public class Room {
    private Integer roomID;
    private String name;
    private String description;
    private String shortDescription;
    private Map<String, Integer> exitsById;
    private List<Item> roomItems;

    public Room() {
        exitsById = new HashMap<>();
        roomItems = new ArrayList<>();
    }

    public void addExitbyID(String dir, Integer roomID){
            exitsById.put(dir.toLowerCase(), roomID);
    }

    public Room setRoomID(Integer roomID) {
        this.roomID = roomID;
        return null;
    }

    public List<Item> getRoomItems() {
        return roomItems;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    //    public Room getRoomAt(String dir) {
//        return exits.get(dir);          // Returns null if dir doesn't exist
//    }

    public Integer getExitByID(String dir) {
        return exitsById.get(dir);
    }

//    public boolean hasExit(String dir) {
//        return exits.containsKey(dir);
//    }

    public void addToRoom(Item item) {
        roomItems.add(item);
    }

    @Override
    public String toString() {
        return "Room [id=" + getRoomID() + ", " +
                "roomName=" + getName() + ", " +
                "description=" + getDescription() + ", " +
                "exitsByID=" + exitsById.toString() + ", " +
                "roomItems=" + getRoomItems().toString();
    }
}
