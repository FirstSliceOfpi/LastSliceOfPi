package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Verbs {
    // Item specific use words

    FEEL, // hairbrush
    EAT, BREAK, //snow white
    OPEN, CLOSE, // hansel and gretel
    SQUISH, RUB, //frog prince
    SLEEP, // sleeping beauty
    SIT, READ, MOVE, WEAR, // cinderella
    SPIN, UNLOCK, // Rumpelstiltskin
    DRINK, // little red riding hood

    // Sight words
    LOOK, SHOW, INSPECT, EXAMINE,
    // Generic words
    TAKE, GET,
    USE,
    DROP,
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