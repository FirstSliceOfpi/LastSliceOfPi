package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.world.Directions;

public class Actions {
    public static String execute(Command command) {

        if(command.getVerb() == null) {
            return "no verb in input";
        }
        if(command.getNoun() == null) {
            return "no noun in input";
        }

        VerbGroup group = command.getVerb().getGroup();
        if(group.equals(VerbGroup.MERGE) && command.getTargetNoun() == null) {
            return "You need two items to merge";
        }

        switch(group) {
            case GRAB:
                return grab(command.getNoun(), command.getVerb());
            case MOVE:
                return move(command.getNoun(), command.getVerb());
            case MERGE:
                return merge(command.getNoun(), command.getVerb(), command.getTargetNoun());
        }
        return "Bug FOUND";
    }

    private static String merge(Noun noun, Verb verb, Noun targetNoun) {
        if(noun.isMergeable() && targetNoun.isMergeable()) {
            return "YOU " + verb.getName()+ " " + noun.getName();
        } else {
            return "you can't merge a " + noun.getName() + " and a " + targetNoun.getName() + " together";
        }
    }

    private static String move(Noun noun, Verb verb) {
        if(noun instanceof Directions.Direction) {
            return "YOU " + verb.getName() + " " +  noun.getName() + " in current room";
        }
        return "That's not a direction";
    }

    private static String grab(Noun noun, Verb verb) {
        if(noun.isGrabbale()) {
            return "YOU " + verb.getName() + " " + noun.getName();
        } else {
            return "You can't grab a " + noun.getName();
        }
    }
}
