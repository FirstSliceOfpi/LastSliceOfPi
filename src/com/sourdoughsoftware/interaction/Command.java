package com.sourdoughsoftware.interaction;
/**
 The Command instance has the current noun and verb from the user input.
 A singleton is used to prevent memory leaks and for GameState access.
 */
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.Savable;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;

import java.io.Serializable;
import java.util.HashMap;

public class Command implements Serializable, Savable {
    private static Noun noun;
    private static Verb verb;
    private static Noun targetNoun = null;

    private Command() {
      noun = null;
      verb = null;
      saveClass();
    }

    public void saveClass() {
        GameState.addSavable(this);
    }

    public HashMap<String, Object> getSaveFields() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("noun", noun);
        result.put("verb", verb);
        result.put("targetNoun", targetNoun);
        return result;
    }

    public boolean setSaveFields(HashMap<String, Object> result) {
        try {
            noun = (Noun) result.get("noun");
            verb = (Verb) result.get("verb");
            targetNoun = (Noun) result.get("targetNoun");
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Noun getNoun() {
        return noun;
    }

    public static void setNoun(Noun nou) { noun = nou;}

    public static Verb getVerb() {
        return verb;
    }

    public static void setVerb(Verb ver) { verb = ver; }

    public static Noun getTargetNoun() {
        return targetNoun;
    }

    public static void setTargetNoun(Noun noun) { targetNoun =  noun; }
}
