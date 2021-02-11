package com.sourdoughsoftware.world;

import com.sourdoughsoftware.dictionary.Noun;

public class Directions {
    public static final Direction north = new Direction("north", "This is the north exit");
    public static final Direction south = new Direction("south", "This is the south exit");
    public static final Direction east = new Direction("east", "This is the east exit");
    public static final Direction west = new Direction("west", "This is the west exit");


    public Directions() {}

    public static final class Direction extends Noun {
        private Direction(String name, String description) {
            super(name, description);
            addToDictionary();
        }
    }

}
