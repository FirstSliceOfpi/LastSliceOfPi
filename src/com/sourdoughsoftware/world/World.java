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
    private static Room currentRoom;

    public World() throws IOException, SAXException, ParserConfigurationException {
        HashMap<String, Enemy> villains = new HashMap<>();
        for (Enemy enemy: enemies) {
            villains.put(enemy.getName(),enemy);
        }
        Enemy scar = villains.get("Scar");
        Room tomorrowLand = new Room("Tomorrow Land", "The land of the future!");
        Room captianHooksShip = new Room("Captain Hook's Ship", "There's lots of crocs in the water!");
        Room winniesTree = new Room("Winnie The Pooh's Tree", "Lots of honey in here.");
        Room goofysHouse = new Room("Goofy's house", "You see Goofy chilling on his couch.");
        currentRoom = tomorrowLand;
        gameMap.add(tomorrowLand);
        gameMap.add(captianHooksShip);
        gameMap.add(winniesTree);
        gameMap.add(goofysHouse);
        for (Room room : gameMap) {
            int index = (int) (Math.random() * enemies.size());
            Enemy enemy = enemies.remove(index);
            room.addToRoom(enemy);
        }
        tomorrowLand.createExit(Directions.east, captianHooksShip);
        captianHooksShip.createExit(Directions.west, tomorrowLand);
        tomorrowLand.createExit(Directions.west, winniesTree);
        winniesTree.createExit(Directions.east, tomorrowLand);
        tomorrowLand.createExit(Directions.south, goofysHouse);
        goofysHouse.createExit(Directions.north, tomorrowLand);
        currentRoom = tomorrowLand;
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
        Map<Directions.Direction, Room> exits = currentRoom.getExits();
        if(exits.containsKey(direction)){
            currentRoom = exits.get(direction);
            currentRoom.clearItems();
            currentRoom.addItemsToRoomOnEntering(0);
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
