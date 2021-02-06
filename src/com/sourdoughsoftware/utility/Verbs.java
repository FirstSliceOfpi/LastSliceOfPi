package com.sourdoughsoftware.utility;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Verbs {
    // Item specific use words

    FEEL, // 0,1,2
    EAT, // 3,6
    BREAK, // 4
    OPEN, // 11
    CLOSE, //
    SQUISH, //
    BAKE, // 7
    RUB, //
    SLEEP, //
    SIT,
    READ,
    MOVE,
    WEAR, //
    SPIN,
    UNLOCK, //
    DRINK, //

    // Sight words
    LOOK, SHOW, EXAMINE, SEE,
    // Generic words
    TAKE, GET,
    USE,
    DROP, // add ability to remove item from inventory with this?
    GO,
    HELP, H,
    QUIT, Q,
    HINT;

    public static List<String> getAllVerbs() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Verbs.class).forEach(val -> result.add(val.name().toLowerCase()));
        return result;
    }
}