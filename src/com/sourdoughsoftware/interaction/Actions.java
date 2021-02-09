package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.utility.Directions;

public class Actions {
    public static String execute(Command command) {

        if(command.getVerb() == null) {
            return "no verb in input";
        }
        if(command.getNoun() == null) {
            return "no noun in input";

        }

        VerbGroup group = command.getVerb().getGroup();

        switch(group) {
            case GRAB:
                return grab(command.getNoun(), command.getVerb());
            case MOVE:
                return move(command.getNoun(), command.getVerb());
            case MERGE:
                return merge(command.getNoun(), command.getVerb());
        }
        return "Bug FOUND";
    }

    private static String merge(Noun noun, Verb verb) {
        return "YOU " + verb.getName()+ " " + noun.getName();
    }

    private static String move(Noun noun, Verb verb) {
        if(noun instanceof Directions.Direction) {
            return "YOU " + verb.getName() + " " +  noun.getName() + " in current room";
        }
        return "That's not a direction";
    }

    private static String grab(Noun noun, Verb verb) {
        return "YOU " + verb.getName() + " " + noun.getName();
    }
}
