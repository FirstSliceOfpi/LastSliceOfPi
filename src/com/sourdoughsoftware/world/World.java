package com.sourdoughsoftware.world;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.gamepieces.Item;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.utility.XmlParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class World {
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

        tomorrowLand.createExit(Directions.east, captianHooksShip);
        captianHooksShip.createExit(Directions.west, tomorrowLand);
        tomorrowLand.createExit(Directions.west, winniesTree);
        winniesTree.createExit(Directions.east, tomorrowLand);
        tomorrowLand.createExit(Directions.south, goofysHouse);
        goofysHouse.createExit(Directions.north, tomorrowLand);
        new Noun("room","All rooms.");
    }


    public static String changeCurrentRoom(Directions.Direction direction) {
        Map<Directions.Direction, Room> exits = currentRoom.getExits();

        if(exits.containsKey(direction)){
            currentRoom = exits.get(direction);
            return "You have entered the " + currentRoom.getName() + "\n" + currentRoom.getDescription();

        } else {
            return ("That is not an exit.");
        }

    }

    void setEnemies(List<Enemy> enemies) {this.enemies = enemies;}

    public static Room getCurrentRoom() {
      return currentRoom;
    };


}
