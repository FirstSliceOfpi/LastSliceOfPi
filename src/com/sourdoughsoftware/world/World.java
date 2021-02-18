package com.sourdoughsoftware.world;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.Savable;
import com.sourdoughsoftware.dictionary.Noun;

import java.util.List;
import java.util.Map;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.utility.XmlParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class World implements Savable {
    List<Room> gameMap = new ArrayList<>();
    List<Enemy> enemies = XmlParser.parseEnemy();
    List<Room> rooms = XmlParser.parseRooms();
    private static Room currentRoom;

    public World() throws IOException, SAXException, ParserConfigurationException {
        XmlParser.parseRooms();
        currentRoom = rooms.get(0);

        HashMap<String, Enemy> villains = new HashMap<>();
        for (Enemy enemy: enemies) {
            villains.put(enemy.getName(),enemy);
        }
        // create map between room name and room object
        HashMap<String, Room> map = new HashMap<>();
        for (Room room : rooms) {
            map.put(room.getName(),room);
            gameMap.add(room);
        }
        // get the directions from each room and map a direction to a room object
        for (Room room : rooms) {
            for (String key : room.roomList.keySet()) {
                String roomTitle = room.roomList.get(key);
                room.roomExits.put(key,map.get(roomTitle));
            }
        }
        for (Room room : gameMap) {
//            int index = (int) (Math.random() * enemies.size());
            switch (room.getName()) {
//                case "start":
//                    room.addToRoom(enemies.get(0));
//                    break;
                case "snow white":
                    room.addToRoom(enemies.get(1));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();

                    break;
                case "frog prince":
                    room.addToRoom(enemies.get(2));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "rapunzel":
                    room.addToRoom(enemies.get(3));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "rumplestiltskin":
                    room.addToRoom(enemies.get(4));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "cinderella":
                    room.addToRoom(enemies.get(5));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "hansel and gretel":
                    room.addToRoom(enemies.get(6));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "red riding hood":
                    room.addToRoom(enemies.get(7));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "shoemaker":
                    room.addToRoom(enemies.get(8));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "sleeping beauty":
                    room.addToRoom(enemies.get(0));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "golden goose":
                    room.addToRoom(enemies.get(10));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "sheriff woody":
                    room.addToRoom(enemies.get(13));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "aladdin":
                    room.addToRoom(enemies.get(12));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "narnia":
                    room.addToRoom(enemies.get(9));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
                case "elsa":
                    room.addToRoom(enemies.get(11));
                    Enemy.incrementEnemiesAlive();
                    Enemy.incrementEnemiesHungry();
                    Enemy.incrementTotalEnemies();
                    break;
            }

        }
        new Noun("room","All rooms.");
        saveClass();
    }

    public HashMap<String, Object> getSaveFields() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("gameMap", gameMap);
        result.put("enemies", enemies);
        result.put("currentRoom", currentRoom);
        return result;
    }

    public boolean setSaveFields(HashMap<String, Object> result) {
        try {
            gameMap = (List<Room>) result.get("gameMap");
            enemies = (List<Enemy>) result.get("enemies");
            currentRoom = (Room) result.get("currentRoom");
        }catch(Exception e) {
            return false;
        }
        return true;
    }


    public static String changeCurrentRoom(Directions.Direction direction) {
        Map<String, Room> exits = currentRoom.getExits();
        if(exits.containsKey(direction.getName())){
            currentRoom = exits.get(direction.getName());
            currentRoom.clearItems();
            currentRoom.addItemsToRoomOnEntering(0);
            currentRoom.addGenericsToRoomOnEntering(0);
            return "You have entered the ";

        } else {
            return ("That is not an exit.");
        }

    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    void setEnemies(List<Enemy> enemies) {this.enemies = enemies;}

    public void saveClass() {
        GameState.addSavable(this);
    }

}
