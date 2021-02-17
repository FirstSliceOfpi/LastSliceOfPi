package com.sourdoughsoftware.world;

import com.sourdoughsoftware.Savable;
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
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
    private List<Item> roomItems;
    Map<String, Room> roomExits = new HashMap<>();
    public Map<Directions.Direction, Room> exits = new HashMap<>();
    public Map<String, String> roomList = new HashMap<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exitsById = new HashMap<>();
        roomItems = new ArrayList<>();
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
        return result;
    }

    public boolean setSaveFields(HashMap<String, Object> result) {
        try {
            result.put("roomID", roomID);
            result.put("name", name);
            result.put("description", description);
            result.put("shortDescription", shortDescription);
            result.put("exitsById", exitsById);
            result.put("roomItems", roomItems);
            result.put("exits", exits);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    public Map<String, Room> getExits() {
        return roomExits;
    }

    public Noun dropItem(Noun noun) {
        int i = roomItems.indexOf(noun);
        if(i == -1) return null;

        Noun dropped = roomItems.get(i);
        roomItems.remove(noun);
        return dropped;
    }

    public boolean has(Noun noun) {
        return roomItems.contains(noun);
    }

    public Noun dropEnemy(Noun noun) {
        int e = roomItems.indexOf(noun);
        if (e == -1) return null;
        System.out.println(noun.getName() + e);
        Noun dropped = roomItems.get(e);
        roomItems.remove(noun);
        Enemy.decrementEnemiesAlive();
        return dropped;
    }



    public void createExit(Directions.Direction direction, Room newExit) {
        exits.put(direction, newExit);
    }

    public void clearItems() {
        ArrayList items = new ArrayList();
        for (Item item : roomItems) {
            if (!(item instanceof Pie)) {
                items.add(item);
            }
        }
        roomItems = items;
    }

    public void addExitbyID(String dir, Integer roomID){
            exitsById.put(dir.toLowerCase(), roomID);
    }

    public Room setRoomID(Integer roomID) {
        this.roomID = roomID;
        return null;
    }

    public String getRoomItems() {
        StringBuilder result = new StringBuilder();
        if(roomItems.size() == 0) {
            return "You find nothing in the room.";
        }
        String examine = ANSI_GREEN + "Examine" + ANSI_RESET;
        result.append("You " + examine + " and See: ");
        roomItems.forEach(item -> {
            if (item instanceof Enemy) {
                String enemyD = ANSI_RED + item.getDescription() + ANSI_RESET;
                result.append( ANSI_RESET + ANSI_RED + item.getName() + ANSI_RESET + ANSI_GREEN + " HP: " + ((Enemy) item).getHp()
                   + ANSI_RESET);
                result.append(enemyD);
            }else if(item instanceof Pie){
                String itemD = ANSI_BLUE + item.getDescription() + ANSI_RESET;
                result.append(ANSI_RESET+ ANSI_CYAN + "An ingredient: " + ANSI_RESET+ ANSI_BLUE + item.getName() + ANSI_RESET  + "\n "
                        );
                result.append(itemD);
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
        return description + " " + getItems();
    }

    public String getItems() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < roomItems.size(); i++) {
                if(!roomItems.get(i).getClass().getSimpleName().equals("Enemy")) {
                    sb.append("\nYou see a ");
                    sb.append(roomItems.get(i).getName());
                    sb.append(".\n");
                } else {
                    Enemy enemy = (Enemy) roomItems.get(i);
                    sb.append("\n");
                    sb.append(enemy.getName());
                    sb.append(" is waiting. (hp: ");
                    sb.append(enemy.getHp());
                    sb.append(")");
                }
        }
        return sb.toString();
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

    public List<Item> getItemList() {
        return roomItems;
    }

//    public boolean hasExit(String dir) {
//        return exits.containsKey(dir);
//    }
    public void addItemsToRoomOnEntering(int tries) {
        Random rand = new Random();
        int maxSize = GameState.getFindableWeapons().size();
        int findableWeapon = rand.nextInt(maxSize);
        int difficulty = (maxSize*1);
        int randomNumber = rand.nextInt(difficulty);
        if(randomNumber < difficulty) {
            if(!GameState.getPlayer().getInventory().has(GameState.getFindableWeapons().get(randomNumber))) {
                addToRoom(GameState.getFindableWeapons().get(findableWeapon));
            }else if (tries < 3){
                ++tries;
                addItemsToRoomOnEntering(tries);
            }
        }
    }

    public void addGenericsToRoomOnEntering(int tries) {
        Random rand = new Random();
        int maxSize = Dictionary.INSTANCE.getGenericItems().size();
        int difficulty = (maxSize*1);
        int randomNumber = rand.nextInt(difficulty);
        if(randomNumber < difficulty) {
            Noun[] items = Dictionary.INSTANCE.getGenericItems().toArray(new Noun[0]);
            if(!GameState.getPlayer().getInventory().has(items[randomNumber]) && items[randomNumber] instanceof Item) {
                addToRoom((Item) items[randomNumber]);
            }else if (tries < 3){
                ++tries;
                addItemsToRoomOnEntering(tries);
            }
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
