package com.sourdoughsoftware.interaction;
/**
 The Command instance has the current noun and verb from the user input.
 A singleton is used to prevent memory leaks and for GameState access.
 */
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.gamepieces.Pie;

import java.io.Serializable;

public class Command implements Serializable {
    private static Noun noun;
    private static Verb verb;
    private static Noun targetNoun = null;

    private static Command instance;

    private Command() {
      noun = null;
      verb = null;
    }

    private Command(Noun noun, Verb verb) {
        Command.noun = noun;
        Command.verb = verb;
    }
    private Command(Noun noun, Verb verb, Noun targetNoun) {
        this(noun, verb);
        Command.targetNoun = targetNoun;
    }

    public static Command getInstance() {
        instance = instance != null ? instance : new Command();
        return instance;
    }

    public Command setInstance(Noun noun, Verb verb, Noun targetNoun) {
        Command.noun = noun;
        Command.verb = verb;
        Command.targetNoun = targetNoun;
        return instance;
    }

    public static Noun getNoun() {
        return noun;
    }

    public static Verb getVerb() {
        return verb;
    }

    public static Noun getTargetNoun() {
        return targetNoun;
    }
}
