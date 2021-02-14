package com.sourdoughsoftware;
/**
 This class stores the global variables for the state of the game.
 It is a singleton class accessible by the getInstance() method.
 It contains getters and setters for the global variables;
 */
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.world.Room;
import com.sourdoughsoftware.world.World;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameState implements Serializable{
    private static GameState instance = null;
    private ItemTree tree;
    private ArrayList findableWeapons;



    private ArrayList enemies;
    private Player player = new Player("Edgar");
    private Command command = null;
    private Boolean devMode = false;
    private Room room = null;
    private List<Noun> inventory = null;

    private GameState() {
        command = Command.getInstance();
    }

    //create method to save the game
    public static boolean saveGame(File fileToSave) {
        GameState gs = GameState.getInstance();
        try {
            gs.setInventory(gs.getPlayer().getInventory().getCurrentInventory());
            FileOutputStream fileStream = new FileOutputStream(fileToSave.getAbsolutePath());
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(Dictionary.INSTANCE.getNouns());
            objectStream.writeObject(GameState.getInstance());
            System.out.println(GameState.getInstance().getInventory());
            objectStream.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDevMode() {
        devMode = !devMode;
    }

    public boolean getDevMode() {
        return devMode;
    }

    public static boolean loadGame(File fileToLoad) {
        try {
            FileInputStream fileStream = new FileInputStream(fileToLoad.getAbsolutePath());
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            Map nouns = (Map) objectStream.readObject();
            GameState gs = (GameState) objectStream.readObject();
            GameState.setInstance(gs);
            Actions.setGs(GameState.getInstance());
            World.setGs(GameState.getInstance());
            Dictionary.INSTANCE.setNouns(nouns);
            objectStream.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList getFindableWeapons() {
        return findableWeapons;
    }

    public void setFindableWeapons(ArrayList findableWeapons) {
        this.findableWeapons = findableWeapons;
    }

    public ArrayList getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList enemies) {
        this.enemies = enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(String name) {
        player = new Player(name);
    }

    public ItemTree getTree() {
        return tree;
    }

    public void setTree(ItemTree tree) {
        this.tree = tree;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

    public void setInventory(List<Noun> inventory) { this.inventory = inventory; }

    public List<Noun> getInventory() { return this.inventory; }

    public static GameState getInstance(){
        instance = instance == null ? new GameState() : instance;
        return instance;
    }

    private static void setInstance(GameState gs) {
        instance = gs;
    }
}
