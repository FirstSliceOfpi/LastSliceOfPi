package com.sourdoughsoftware.dictionary;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.ChainOfEventException;
import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.interaction.Event;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Noun implements DictionaryEntry, Serializable {

    private String name;
    private String description;

    private boolean examinable = false;
    private boolean grabable = false;
    private boolean mergeable = false;
    private boolean attackable = false;
    private boolean findable = false;
    private boolean dropable = false;
    private boolean wieldable = false;
    private boolean generic = false;
    private boolean cheat = false;

    public HashMap<String, ArrayList<Event>> interactions = new HashMap<>();


    public void setAction(String verb, ArrayList<Event> events) {
        interactions.put(verb, events);
    }

    public String getAction(String verb) {
        StringBuilder response = new StringBuilder();
        ArrayList<Event> events = interactions.get(verb);
        Actions act = new Actions();
        Noun key = null;
        if(!Command.getVerb().getName().equals("cheat")) {
            if(Actions.checkIfAvailable() != null) return Actions.checkIfAvailable();
        }
        if(events == null) {
            return "You can't " + Command.getVerb().getName() + " a " + getName();
        }

        for(Event event : events) {
            if(event.key != null && key == null) {
                key = Command.getNoun();
            }

            if(event.key != null && event.key != key && Command.getTargetNoun() != null) {
                return "You can't " + Command.getVerb().getName() + " a " + getName() + " with a " + key.getName();
            }
            try {
                Method method = act.getClass().getMethod(event.verbGroup.toString().strip(), String.class);
                response.append(method.invoke(act ,event.message.strip())+ " ");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                if(GameState.getDevMode()) { e.printStackTrace(); };
                if(e instanceof InvocationTargetException && e.getCause() instanceof ChainOfEventException) {
                    return e.getCause().getMessage();
                }
            }
        }
        return response.toString();
    }

    public Noun(String name, String description) {
        this.name = name.toLowerCase();
        this.description = description;
        addToDictionary();
    }

    public Noun(Noun noun) {
        this.name = noun.getName();
        this.description = noun.getDescription();
    }

    public Noun() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }

    public boolean isCheat() {
        return this.cheat;
    }
    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public boolean isGeneric() { return generic; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public boolean isFindable() {
        return findable;
    }

    public void setFindable(boolean findable) {
        this.findable = findable;
    }
//
//    public void setMergeable(boolean mergeable) {
//        this.mergeable = mergeable;
//    }
//
//
    public boolean isAttackable() {return attackable;}

    public void setAttackable(boolean attackable){this.attackable = attackable;}
//
//    public boolean isWieldable(){return wieldable;}
//
//    public void setWieldable(boolean wieldable) {
//        this.wieldable = wieldable;
//    }
//
//    public void setExaminable(boolean examinable) {
//        this.examinable = examinable;
//    }
//
//
//    public void setGrabable(boolean grabable) {
//        this.grabable = grabable;
//    }
//
//
//    public void setDropable(boolean dropable) {
//        this.dropable = dropable;
//    }
}
