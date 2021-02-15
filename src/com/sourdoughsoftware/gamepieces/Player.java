package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.Savable;
import com.sourdoughsoftware.dictionary.Noun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Serializable, Savable {
    private String name;
    private int hp;
    static Player player = new Player("edgar");

    public Inventory inventory = new Inventory();

    public Player(String name) {
        this.name = name;
        saveClass();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<String, Object> getSaveFields() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("hp", hp);
        return result;
    }

    public boolean setSaveFields(HashMap<String, Object> result) {
        try {
            name = (String) result.get("name");
            hp = (Integer) result.get("hp");
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    public void saveClass() {
        GameState.addSavable(this);
    }

    public static Player getPlayer() {
        return player;
    }

    public static class Inventory extends Noun implements Serializable, Savable {
        List<Noun> inventory = new ArrayList<>();
        private Pie currentWeapon;

        private Inventory() {
            super("inventory", "This is your inventory bag");
            setExaminable(true);
            saveClass();
        }

        public boolean has(Noun noun) {
            return inventory.contains(noun);
        }

        public String add(Noun noun) {
            if(inventory.contains(noun)) { return "Item is already in inventory."; }
            inventory.add(noun);
            return noun.getName() + " is now in your inventory";
        }

        public String drop(Noun noun) {
            boolean dropped = inventory.remove(noun);
            if(dropped) {
                return "You dropped the " + noun.getName();
            } else {
                return "You don't have that in your inventory";
            }
        }

        public List<Noun> getCurrentInventory() {
            return inventory;
        }

        public Pie getCurrentWeapon() {
            return currentWeapon;
        }

        public HashMap<String, Object> getSaveFields() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("inventory", inventory);
            result.put("currentWeapon", currentWeapon);
            return result;
        }

        public boolean setSaveFields(HashMap<String, Object> result) {
            try {
                inventory = (List<Noun>) result.get("inventory");
                currentWeapon = (Pie) result.get("currentWeapon");
            }catch(Exception e) {
                return false;
            }
            return true;
        }

        public void saveClass() {
            GameState.addSavable(this);
        }
    }

}
