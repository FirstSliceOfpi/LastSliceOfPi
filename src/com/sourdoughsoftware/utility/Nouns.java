package com.sourdoughsoftware.utility;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Nouns {
    // Story specific items
    HAIRBRUSH, SILKSKEIN, BRAID, // rapunzel
    APPLE, MIRROR, TOOLS, // snow white
    GINGERBREAD, OVEN, CAGE, CHEST, TREASURE, // hansel and gretel
    DOORS, BAG, INSECTS, CRYSTALSPHERE, // frog pricne
    BED, SPINDLE, BOX, FIRSTAIDKIT, // sleeping beauty
    CHAIR, INVITATION, TABLE, BOXES, GOWNS, GLASSSLIPPER, // cinderella
    STRAW, LOCKEDDOORS,HAY, GOLDBARS,BOOK, // rumplestiltskin
    TEA, LOZENGES, TISSUE, NIGHTSTAND, CAPE, SLEEPINGCAP,BASKET, FOOD, PACKAGES, // little red
    SHOES,CLOTHING, WORKBENCH, // the shoemaker
    AX, NEST, GOLDENFEATHER, // the golden goose

    // Non-takeable things
    INVENTORY, ROOM, PIVAL;

    // Directions
    enum DIRECTIONS{N, S, E, W, NW, NE, SE, SW, NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST}

    public static List<String> getAllNouns() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Nouns.class).forEach(val -> result.add(val.name().toLowerCase()));
        return result;
    }

    public static List<String> getAllDirections() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Nouns.DIRECTIONS.class).forEach(val -> result.add(val.name().toLowerCase()));
        return result;
    }
}
