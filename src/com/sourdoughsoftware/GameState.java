package com.sourdoughsoftware;

import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.utility.ItemTree;
import java.util.ArrayList;

public class GameState {
    static GameState instance = null;
    private ItemTree tree;
    private ArrayList findableWeapons;
    private Player player = new Player("Edgar");

    private GameState() {
        instance = new GameState();
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

    public static GameState getInstance() {
        return instance != null ? instance : new GameState();
    }
}
