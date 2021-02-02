package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sourdoughsoftware.Nouns;

public class Items {
    HashMap<String, String[]> interactions = new HashMap<>();
    String response = "You cannot use that item that way.";

    public String roomItems(Room location, String verb, String noun){
        switch (location.getName()){
            case "rapunzel":
                response = rapunzelItems(verb, noun);
                break;
            case "snow white":
                response = snowItems(verb,noun);
                break;
            default:
                response = "No items found.";
        }
        return response;
    }
    public String rapunzelItems(String verb, String noun) {
        // Create map of verb noun interaction
        interactions.put(Verbs.FEEL.name().toLowerCase(), Nouns.getRapunzel());
        System.out.println(interactions.toString());
        System.out.println(interactions.values());
        System.out.println(Arrays.toString(interactions.get("feel")));
        String[] rapunzel = interactions.get(verb);
        if (noun.equalsIgnoreCase(rapunzel[0])) {
            response = "The hair feels soft";
        }
        else if (noun.equalsIgnoreCase(rapunzel[1])){
            response = "The hairbrush is bristly and blundery, but functional.";
        }
        else if (noun.equalsIgnoreCase(rapunzel[2])){
            response = "You touch it, and think soft thoughts.";
        }
        else if (noun.equalsIgnoreCase(rapunzel[3])){
            response = "You feel the braid of hair, and wonder if the conditioner is natural.";
        }
        return response;
    }
    public String snowItems(String verb, String noun) {
        // Create map of verb noun interaction
        interactions.put(Verbs.EAT.name().toLowerCase(), Nouns.getSnow());
        interactions.put(Verbs.EXAMINE.name().toLowerCase(), Nouns.getGeneric());
        System.out.println(interactions.toString());
        System.out.println(interactions.values());
        System.out.println(Arrays.toString(interactions.get("eat")));
        System.out.println(Arrays.toString(interactions.get("examine")));
        String[] snow = interactions.get(verb);
        if (noun.equalsIgnoreCase(snow[0])) {
            response = "The apple is delicious, but poisoned. You fall asleep.";
        }
        else if (noun.equalsIgnoreCase(snow[1])){
            response = "The hairbrush is bristly and blundery, but functional.";
        }
        else if (noun.equalsIgnoreCase(snow[2])){
            response = "You touch it, and think soft thoughts.";
        }
        else if (noun.equalsIgnoreCase(snow[3])){
            response = "You feel the braid of hair, and wonder if the conditioner is natural.";
        }
        return response;
    }


}
