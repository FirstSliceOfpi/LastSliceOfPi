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
    private Noun noun;
    private Verb verb;
    private Noun targetNoun = null;
    private static Command instance;

    private Command() {
      noun = null;
      verb = null;
    }

    public static Command getInstance() {
        instance = instance != null ? instance : new Command();
        return instance;
    }

    public Command setInstance(Noun noun, Verb verb) {
        this.noun = noun;
        this.verb = verb;
        return instance;
    }

    public Command setInstance(Noun noun, Verb verb, Noun targetNoun) {
        this.noun = noun;
        this.verb = verb;
        this.targetNoun = targetNoun;
        return instance;
    }

    public Noun getNoun() {
        return noun;
    }

    public Verb getVerb() {
        return verb;
    }

    public Noun getTargetNoun() {
        return targetNoun;
    }
}
