package com.sourdoughsoftware.world;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.gamepieces.Item;

import java.util.*;

/**
 * Creates a Room to be used to create the game world. Rooms have exits and can also contain items.
 * @author Tyrone Moore, Kelson Smith, and Gina Villegas
 * @version 1.0.0
 */
public class Room implements java.io.Serializable{
    private Integer roomID;
    private String name;
    private String description;
    private String shortDescription;
    private Map<String, Integer> exitsById;
    private List<Item> roomItems;
    private final Map<Directions.Direction, Room> exits = new HashMap<>();
    private List<Noun> items = new ArrayList<>();




    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exitsById = new HashMap<>();
        roomItems = new ArrayList<>();
    }
    public Map<Directions.Direction, Room> getExits() {
        return exits;
    }

    public Noun dropItem(Noun noun) {
        Noun dropped = items.get(items.indexOf(noun));
        items.remove(noun);
        return dropped;
    }

    public void addItem(Noun noun) {
        items.add(noun);
        items.forEach(item -> System.out.println(item.getName()));
        System.out.println(getName());
    }

    public void createExit(Directions.Direction direction, Room newExit) {
        exits.put(direction, newExit);
    }

    public void clearItems() {
        roomItems = new ArrayList();
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
    public void addItemsToRoomOnEntering() {
        Random rand = new Random();
        int maxSize = GameState.getInstance().getFindableWeapons().size()+1;
        int findableWeapon = rand.nextInt(maxSize);
        int difficulty = (int) (maxSize*1.5);
        int randomNumber = rand.nextInt(difficulty);
        if(randomNumber < maxSize-1) {
            addToRoom((Item) GameState.getInstance().getFindableWeapons().get(findableWeapon));
        }
    }
    public boolean addToRoom(Item item) {
        return roomItems.add(item);
    }

    public boolean removeItem(Item item) {
       return roomItems.remove(item);
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
