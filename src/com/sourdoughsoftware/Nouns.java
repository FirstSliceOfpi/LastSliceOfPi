package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public enum Nouns {
    // Story specific items
    HAIRBRUSH, SILK, BRAID,
    APPLE, MIRROR, TOOLS,
    GINGERBREAD, OVEN, CAGE, CHEST, TREASURE,
    DOORS, BAG, INSECTS, CRYSTALSPHERE,
    BED, SPINDLE, BOX, FIRSTAIDKIT,
    CHAIR, INVITATION, TABLE, BOXES, GOWNS, GLASSSLIPPER,
    STRAW, LOCKEDDOORS,HAY, GOLDBARS,BOOK,
    TEA, LOZENGES, TISSUE, NIGHTSTAND, CAPE, SLEEPINGCAP,BASKET, FOOD, PACKAGES,
    SHOES,CLOTHING, WORKBENCH,
    AX, NEST, GOLDENFEATHER,

    // Non-takeable things
    ROOM;

    // Directions
    enum DIRECTIONS{N, S, E, W, NW, NE, SE, SW, NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST};

    public static List<String> getAllNouns() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Nouns.class).forEach(val -> result.add(val.name()));
        return result;
    }

    public static List<String> getAllDirections() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Nouns.DIRECTIONS.class).forEach(val -> result.add(val.name().toLowerCase()));
        return result;
    }
}
