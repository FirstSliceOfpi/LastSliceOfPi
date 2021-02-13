package com.sourdoughsoftware.dictionary;

import com.sourdoughsoftware.interaction.Actions;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Noun implements DictionaryEntry, Serializable {

    private String name;
    private String description;

    private boolean weildable = false;
    private boolean examinable = false;
    private boolean grabable = false;
    private boolean mergeable = false;
    private boolean attackable = false;
    private boolean findable = false;
    private boolean dropable = false;

    public ArrayList<String[]> light = null;

    public void setAction(String action, ArrayList<String[]> argument) {
        try {
            Field field = this.getClass().getField(action.strip());
            field.set(this,argument);
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String[]> getAction(String verb) {
        ArrayList<String[]> actions = null;
        try {
            actions = (ArrayList<String[]>) this.getClass().getField(verb).get(this);
            try{
                Actions act = new Actions();
                for(String[] action : actions) {
                     Method method = act.getClass().getMethod(action[0].strip(),String.class);
                     method.invoke(act,action[1].strip());
                }
            }catch(Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return actions;
    }

    public Noun(String name, String description) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String str) { this.description = description; }

    public boolean isFindable() {
        return findable;
    }

    public void setFindable(boolean findable) {
        this.findable = findable;
    }

    public boolean isMergeable() {
        return mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }

    public boolean isWeildable() {
        return weildable;
    }

    public void setWeildable(boolean weildable) {
        this.weildable = weildable;
    }

    public boolean isExaminable() {
        return examinable;
    }

    public void setExaminable(boolean examinable) {
        this.examinable = examinable;
    }

    public boolean isGrabable() {
        return grabable;
    }

    public void setGrabable(boolean grabable) {
        this.grabable = grabable;
    }

    public boolean isDropable() {
        return dropable;
    }

    public void setDropable(boolean dropable) {
        this.dropable = dropable;
    }
}
