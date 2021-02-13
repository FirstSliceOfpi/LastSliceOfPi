package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;

import java.io.Serializable;

public class Command implements Serializable {
    private final Noun noun;
    private final Verb verb;
    private Noun targetNoun = null;
    private static Command instance;

    private Command() {
      noun = null;
      verb = null;
    }

    private Command(Noun noun, Verb verb) {
        this.noun = noun;
        this.verb = verb;
    }
    private Command(Noun noun, Verb verb, Noun targetNoun) {
        this(noun, verb);
        this.targetNoun = targetNoun;
    }

    public static Command getInstance() {
        instance = instance != null ? instance : new Command();
        return instance;
    }

    public Command setInstance(Noun noun, Verb verb) {
        instance = new Command(noun, verb);
        GameState.getInstance().setCommand(instance);
        return instance;
    }

    public Command setInstance(Noun noun, Verb verb, Noun targetNoun) {
        instance = new Command(noun, verb, targetNoun);
        GameState.getInstance().setCommand(instance);
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
