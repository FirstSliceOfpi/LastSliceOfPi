package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.dictionary.Noun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    static Player player = new Player("edgar");
    private String name;
    private int hp;

    public Inventory inventory = new Inventory();

    private Player(String name) {
        this.name = name;
    }

    public static Player getPlayer() {
        return player;
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

    public static class Inventory extends Noun implements Serializable{
        List<Noun> inventory = new ArrayList<>();

        private Inventory() {
            super("inventory", "This is your inventory bag");
            setExaminable(true);
        }

        public boolean has(Noun noun) {
            return inventory.contains(noun);
        }

        public String add(Noun noun) {
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
    }

}
