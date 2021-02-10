package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.dictionary.Noun;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    public Inventory inventory = new Inventory();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static class Inventory extends Noun {
        List<Noun> inventory = new ArrayList<>();

        private Inventory() {
            super("inventory", "This is your inventory bag");
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