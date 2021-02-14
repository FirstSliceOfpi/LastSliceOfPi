package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.gamepieces.Pie;

public class Command {
    private final Noun noun;
    private final Verb verb;
        private Noun targetNoun = null;

    public Command(Noun noun, Verb verb) {
        this.noun = noun;
        this.verb = verb;
    }
       public Command(Noun noun, Verb verb, Noun targetNoun) {
        this(noun, verb);
        this.targetNoun = targetNoun;
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
