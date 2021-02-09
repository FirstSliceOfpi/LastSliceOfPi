package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.dictionary.Noun;

public enum Directions {
    NORTH(new Direction("north", "This is the north exit")),
    SOUTH(new Direction("south", "This is the south exit")),
    EAST(new Direction("east", "This is the east exit")),
    WEST(new Direction("west", "This is the west exit"));

    Direction direction;

    private Directions(Direction direction) {
        this.direction = direction;
    }

    public static final class Direction extends Noun {
        private Direction(String name, String description) {
            super(name, description);
            addToDictionary();
        }
    }

}
