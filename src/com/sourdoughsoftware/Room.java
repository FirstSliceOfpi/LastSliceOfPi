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
    private final String name;
    private final String description;
    private Map<String, Room> exits;
    private List<String> roomItems;

    public Room(String name) {
        this.name = name;
        description = Story.readFileArray(0);  // TODO just for testing, should be changed
        exits = new HashMap<>();
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getRoomAt(String dir) {
        return exits.get(dir);          // Returns null if dir doesn't exist
    }

    public boolean hasExit(String dir) {
        return exits.containsKey(dir);
    }

    public void addToRoom(String item) {
        roomItems.add(item);
    }

//    public void removeFromRoom(String item) {
//        roomItems.
//    }
}
