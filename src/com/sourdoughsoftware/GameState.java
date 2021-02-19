package com.sourdoughsoftware;
/**
 This class stores the global variables for the state of the game.
 It is a singleton class accessible by the getInstance() method.
 It contains getters and setters for the global variables;
 */


import com.sourdoughsoftware.gamepieces.CookBook;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.utility.ItemTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState implements Serializable, Savable{
    private static Boolean devMode = false;
    private static ArrayList<Pie> findableWeapons = new ArrayList<Pie>();
    private static ArrayList<Savable> savedClasses = new ArrayList<>();
    private static ItemTree tree = new ItemTree();
    private static Player player = new Player("edgar");
    private static CookBook cookBook = null;

    private GameState() {
        saveClass();
    }
    public static void addSavable(Savable savableClass) {
        savedClasses.add(savableClass);
    }

    //create method to save the game
    public static boolean saveGame(File fileToSave) {
        try {
            FileOutputStream fileStream = new FileOutputStream(fileToSave.getAbsolutePath());
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            for(int i = 0; i < savedClasses.size(); i++) {
                objectStream.writeObject(savedClasses.get(i).getSaveFields());
            }
            objectStream.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public static void setCookBook(CookBook book) {
        cookBook = book;
    }

    public static CookBook getCookBook() {
        return cookBook;
    }

    public static void setDevMode() {
        devMode = !devMode;
    }

    public static boolean getDevMode() {
        return devMode;
    }

    public static boolean loadGame(File fileToLoad) {
        try {
            FileInputStream fileStream = new FileInputStream(fileToLoad.getAbsolutePath());
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            for(int i = 0; i < savedClasses.size(); i++) {
                HashMap<String, Object> fields = (HashMap<String, Object>)objectStream.readObject();
                savedClasses.get(i).setSaveFields(fields);
            }
            objectStream.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public static ArrayList<Pie> getFindableWeapons() {
        return findableWeapons;
    }

    public static void setFindableWeapons(ArrayList<Pie> findable) {
        findableWeapons = findable;
    }
    public static ItemTree getTree() {
        return tree;
    }

    public static void setTree(ItemTree tre) {
        tree = tre;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player playr) {
        player = playr;
    }

    public Object getGameState() {
        return this;
    }

    public HashMap<String, Object> getSaveFields(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("devMode", devMode);
        result.put("findableWeapons", findableWeapons);
        result.put("savedClasses", savedClasses);
        result.put("tree", tree);
        result.put("player", player);
        result.put("cookBook", cookBook);
        return result;
    }
    public boolean setSaveFields(HashMap<String, Object> result){
       try {
           devMode = (Boolean) result.get("devMode");
           findableWeapons = (ArrayList<Pie>) result.get("findableWeapons");
           savedClasses = (ArrayList<Savable>) result.get("savedClasses");
           tree = (ItemTree) result.get("tree");
           player = (Player) result.get("player");
           cookBook = (CookBook) result.get("cookBook");
           return true;
       }catch (Exception e) {
           return false;
       }
    }

    public void saveClass(){
        addSavable(this);
    }

}
