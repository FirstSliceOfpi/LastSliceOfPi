package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public enum Nouns {
    // Story specific items
    HAIRBRUSH, SILK, BRAID, HAIR,
    APPLE, MIRROR, TOOLS,
    GINGERBREAD, OVEN, CAGE, CHEST, TREASURE,
    DOORS, BAG, INSECTS, CRYSTAL_SPHERE,
    BED, SPINDLE, BOX, FIRST_AID_KIT,
    CHAIR, INVITATION, TABLE, BOXES, GOWNS, GLASS_SLIPPER,
    STRAW, LOCKED_DOORS,HAY, GOLD_BARS,BOOK,
    TEA, LOZENGES, TISSUE, NIGHTSTAND, CAPE, SLEEPING_CAP,BASKET, FOOD, PACKAGES,

    // Non-takeable things
    ROOM;

    // Directions
    enum DIRECTIONS{N, S, E, W, NW, NE, SE, SW, NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST};

    public static String[] getRapunzel() {
        return new String[]{HAIR.name(), HAIRBRUSH.name(), SILK.name(), BRAID.name()};
    }
    public static String[] getSnow() {
        return new String[]{APPLE.name()};
    }
    public static String[] getGeneric() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Nouns.class).forEach(val -> result.add(val.name()));
        String[] resultArray = new String[Nouns.values().length];
        resultArray = result.toArray(resultArray);
        return resultArray;

    }


}
