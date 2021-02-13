package com.sourdoughsoftware;
/**
 This class stores the global variables for the state of the game.
 It is a singleton class accessible by the getInstance() method.
 It contains getters and setters for the global variables;
 */
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.utility.ItemTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Serializable{
    private static GameState instance = null;
    private ItemTree tree;
    private ArrayList findableWeapons;
    private Player player = new Player("Edgar");
    private Command command = null;

    private GameState() {
    }

    public GameState(GameState gs) {
        instance = gs;
    }
    //create method to save the game
    public static boolean saveGame(File fileToSave) {
        try {
            FileOutputStream fileStream = new FileOutputStream(fileToSave.getAbsolutePath());
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            HashMap<String, Object> objectToSave = new HashMap<>();
            objectToSave.put("gameState", GameState.getInstance());
            objectToSave.put("nouns", Dictionary.INSTANCE.getNouns());
            objectStream.writeObject(objectToSave);
            objectStream.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean loadGame(File fileToLoad) {
        try {
            FileInputStream fileStream = new FileInputStream(fileToLoad.getAbsolutePath());
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            HashMap loadedGame = (HashMap) objectStream.readObject();
            new GameState((GameState) loadedGame.get("gameState"));
            Dictionary.INSTANCE.setNouns((Map) loadedGame.get("nouns"));
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

    public static GameState getInstance(){
        instance = instance == null ? new GameState() : instance;
        return instance;
    }
}
