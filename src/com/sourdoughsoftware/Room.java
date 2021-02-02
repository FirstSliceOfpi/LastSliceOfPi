package com.sourdoughsoftware;

import java.io.File;
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
    private Integer id;
    private String name;
    private String description;
    private Map<String, Room> exits;
    private Map<String, Integer> exitsById;
    private List<String> roomItems;

//    public Room(String name) {
//        this.name = name;
//        description = Story.readFileArray(0);  // TODO just for testing, should be changed
//        this.id = id;
//        exits = new HashMap<>();
//        roomItems = new ArrayList<>();
//    }

    public Room() {
//        this.name = name;
//        this.id = id;
//        this.description = description;
//        exits = new HashMap<>();
        exitsById = new HashMap<>();
        roomItems = new ArrayList<>();
    }

    public void addExit(String dir, Room room) {
        if ((dir instanceof String && dir != "") && room != null) {
            exits.put(dir.toLowerCase(), room);
        }
        else {
            throw new IllegalArgumentException("dir must be valid direction and room must not be null");
        }
    }

    public void addExitbyID(String dir, Integer roomID){
            exitsById.put(dir.toLowerCase(), roomID);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getRoomItems() {
        return roomItems;
    }

    public Integer getId() {
        return id;
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

    public Room getRoomAt(String dir) {
        return exits.get(dir);          // Returns null if dir doesn't exist
    }

    public Integer getExitByID(String dir) {
        return exitsById.get(dir);
    }

    public boolean hasExit(String dir) {
        return exits.containsKey(dir);
    }

    public void addToRoom(String item) {
        roomItems.add(item);
    }

    @Override
    public String toString() {
        return "Room [id=" + getId() + ", roomName=" + getName() + ", description=" + getDescription() + ", exitsByID=" + exitsById.toString() + ", roomItems=" + getRoomItems().toString();
    }
    //    public void removeFromRoom(String item) {
//        roomItems.
//    }
}
