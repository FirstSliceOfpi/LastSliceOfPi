package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Verbs {
    // Item specific use words

    FEEL,
    EAT,
    /*OPEN, CLOSE, LOOT,
    SQUISH, RUB,
    SLEEP,
    SIT, READ, MOVE, WEAR,
    SPIN, UNLOCK,
    DRINK,
    */
    // Sight words
    LOOK,
//    SHOW, INSPECT, EXAMINE,
    // Generic words
//    TAKE, USE, GET, DROP,
    GO, HELP, H, QUIT, Q, HINT;

    public static List<String> getAllVerbs() {
        List<String> result = new ArrayList<>();
        EnumSet.allOf(Verbs.class).forEach(val -> result.add(val.name().toLowerCase()));
        return result;
    }
}