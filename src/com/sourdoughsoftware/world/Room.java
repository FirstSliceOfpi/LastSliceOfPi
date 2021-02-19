package com.sourdoughsoftware.world;

import com.sourdoughsoftware.Savable;
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.gamepieces.Player;

import static com.sourdoughsoftware.utility.Colors.*;

import java.util.*;

/**
 * Creates a Room to be used to create the game world. Rooms have exits and can also contain items.
 * @author Tyrone Moore, Kelson Smith, and Gina Villegas
 * @version 1.0.0
 */
public class Room implements java.io.Serializable, Savable {
    private Integer roomID;
    private String name;
    private String description;
    private String shortDescription;
    private Map<String, Integer> exitsById;
    private HashSet<Noun> roomItems;
    Map<String, Room> roomExits = new HashMap<>();
    public Map<Directions.Direction, Room> exits = new HashMap<>();
    public Map<String, String> roomList = new HashMap<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exitsById = new HashMap<>();
        roomItems = new HashSet<>();
        saveClass();
    }

    public void saveClass() {
        GameState.addSavable(this);
    }

    public HashMap<String, Object> getSaveFields() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("roomID", roomID);
        result.put("name", name);
        result.put("description", description);
        result.put("shortDescription", shortDescription);
        result.put("exitsById", exitsById);
        result.put("roomItems", roomItems);
        result.put("exits", exits);
        result.put("roomList", roomList);
        result.put("roomExits", roomExits);
        return result;
    }

    public boolean setSaveFields(HashMap<String, Object> result) {
        try {
            roomID = (Integer) result.get("roomID");
            name = (String) result.get("name");
            description = (String) result.get("description");
            shortDescription = (String) result.get("shortDescription");
            exitsById = (Map<String, Integer>) result.get("exitsById");
            roomItems = (HashSet<Noun>) result.get("roomItems");
            exits = (Map<Directions.Direction, Room>) result.get("exits");
            roomList = (Map<String, String>) result.get("roomList");
            roomExits = (Map<String, Room>) result.get("roomExits");
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    public Map<String, Room> getExits() {
        return roomExits;
    }

    public Noun dropItem(Noun noun) {
        if(!roomItems.contains(noun)) return null;
            roomItems.remove(noun);
        return noun;
    }

    public boolean has(Noun noun) {
        return roomItems.contains(noun);
    }

    public Noun dropEnemy(Noun noun) {
        if(!roomItems.contains(noun)) return null;
        roomItems.remove(noun);
        Enemy.decrementEnemiesAlive();
        return noun;
    }

    public void createExit(Directions.Direction direction, Room newExit) {
        exits.put(direction, newExit);
    }

    public void clearItems() {
        roomItems.removeIf(item->!(item instanceof Enemy));
    }

    public void addExitbyID(String dir, Integer roomID){
            exitsById.put(dir.toLowerCase(), roomID);
    }

    public Room setRoomID(Integer roomID) {
        this.roomID = roomID;
        return null;
    }

    /**
     * Builds a string with the items in the room (Enemies, Pies, Generics)
     * @return
     */
    public String getRoomItems() {
        StringBuilder result = new StringBuilder();
        if(roomItems.size() == 0) {
            return "You find nothing in the room.";
        }
        String examine = ANSI_GREEN + "Examine" + ANSI_RESET;
        result.append("You " + examine + " and See: \n");
        roomItems.forEach(item -> {
                    try {
                        Enemy myEnemy = new Enemy(" ", " ");
                        myEnemy = (Enemy) item;
                        if (roomItems.contains(item)) {
                            String enemyD = ANSI_RED + item.getDescription() + ANSI_RESET;
                            result.append(ANSI_RESET + ANSI_RED).append(item.getName()).append(ANSI_RESET).append(ANSI_GREEN).append(" HP: ").append(((Enemy) item).getHp()).append(ANSI_RESET);
                            result.append(enemyD);
                        }
                    } catch (ClassCastException e) {
                        String itemD;
                        try {
                            Pie pie = (Pie) item;
                            itemD = ANSI_BLUE + item.getDescription() + ANSI_RESET;
                            result.append(ANSI_RESET + ANSI_CYAN + "An ingredient: " + ANSI_RESET + ANSI_BLUE).append(item.getName()).append(ANSI_RESET).append("\n ");
                        } catch (ClassCastException f) {
                            itemD = ANSI_RESET + ANSI_GREEN + "\n" + item.getDescription() + ANSI_RESET;
                            result.append(ANSI_RESET + ANSI_CYAN + "An item: " + ANSI_RESET + ANSI_GREEN + item.getName() + ANSI_RESET);
                        }
                        result.append(itemD);
                        result.append("\n");
                    }
                });
        result.setLength(result.length() - 4);
        return result.toString();
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
        return name + "\n" + description + "\n" + getRoomItems();
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

    public Integer getExitByID(String dir) {
        return exitsById.get(dir);
    }

    public HashSet<Noun> getItemList() {
        return roomItems;
    }

    /**
     * Adds findable pie ingredients to the room on entering
     * @param tries
     */
    public void addItemsToRoomOnEntering(int tries) {
        Random rand = new Random();
        int maxSize = GameState.getFindableWeapons().size();
        int findableWeapon = rand.nextInt(maxSize);
        int difficulty = (maxSize*1);
        int randomNumber = rand.nextInt(difficulty);
        if(randomNumber < difficulty) {
            Player.Inventory playerInventory = GameState.getPlayer().getInventory();
            if(!playerInventory.has(GameState.getFindableWeapons().get(randomNumber))) {
                addToRoom(GameState.getFindableWeapons().get(findableWeapon));
            } else if (tries < 3){
                ++tries;
                addItemsToRoomOnEntering(tries);
            }
        }
    }

    /**
     * Adds generic items to the room on entering
     * @param tries
     */
    public void addGenericsToRoomOnEntering(int tries) {
        Random rand = new Random();
        ArrayList<Noun> allNouns = Dictionary.INSTANCE.getNounsForRespawn();
        int maxSize = allNouns.size();
        int difficulty = (maxSize*1);
        int randomNumber = rand.nextInt(difficulty);
        if(randomNumber < difficulty) {
            Player.Inventory playerInventory = GameState.getPlayer().getInventory();
            Noun item = allNouns.get(randomNumber);
            if(item instanceof Enemy || item instanceof Pie || item.isCheat()) {
                addGenericsToRoomOnEntering(tries);
                return;
            }
            if(!playerInventory.has(item)
                    && item.isGeneric()) {
                addToRoom(allNouns.get(randomNumber));
            } else if (tries < 3){
                ++tries;
                addGenericsToRoomOnEntering(tries);
            }
        }
    }
    /**
     * Adds nouns to the list and verfies they are of type enemy, pie, or generic
     * @param item
     */
    public void addToRoom(Noun item) {
        if(!Objects.isNull(item)){
            try {
                roomItems.add((Enemy) item);
            } catch (Exception e) {
                try {
                    roomItems.add((Pie) item);
                } catch (Exception f) {
                    roomItems.add(item);
                    Dictionary.INSTANCE.killNounRespawn(item);
                }
            }
        }
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
