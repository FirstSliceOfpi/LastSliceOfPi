package com.sourdoughsoftware.dictionary;

import com.sourdoughsoftware.interaction.Actions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Noun implements DictionaryEntry {

    private String name;
    private String description;


    private boolean feelable = false;
    private boolean eatable = false;
    private boolean breakable = false;
    private boolean openable = false;
    private boolean closeable = false;
    private boolean squishable = false;
    private boolean bakeable = false;
    private boolean rubable = false;
    private boolean readable = false;
    private boolean wearable = false;
    private boolean weildable = false;
    private boolean drinkable = false;
    private boolean examinable = false;
    private boolean dropable = false;
    private boolean grabable = false;
    private boolean useable = false;
    private boolean mergeable = false;
    private boolean attackable = false;
    private boolean findable = false;
    public ArrayList<String[]> light = null;

    public void setAction(String action, ArrayList<String[]> argument) {
        try {
            Field field = this.getClass().getField(action);
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
                    Method method = act.getClass().getMethod(action[0],String.class);
                    method.invoke(act,action[1]);
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

    public boolean isFindable() {
        return findable;
    }

    public void setFindable(boolean findable) {
        this.findable = findable;
    }

    public boolean isFeelable() {
        return feelable;
    }

    public void setFeelable(boolean feelable) {
        this.feelable = feelable;
    }

    public boolean isEatable() {
        return eatable;
    }

    public void setEatable(boolean eatable) {
        this.eatable = eatable;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public boolean isSquishable() {
        return squishable;
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

    public void setSquishable(boolean squishable) {
        this.squishable = squishable;
    }

    public boolean isBakeable() {
        return bakeable;
    }

    public void setBakeable(boolean bakeable) {
        this.bakeable = bakeable;
    }

    public boolean isRubable() {
        return rubable;
    }

    public void setRubable(boolean rubable) {
        this.rubable = rubable;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isWearable() {
        return wearable;
    }

    public void setWearable(boolean wearable) {
        this.wearable = wearable;
    }

    public boolean isWeildable() {
        return weildable;
    }

    public void setWeildable(boolean weildable) {
        this.weildable = weildable;
    }

    public boolean isDrinkable() {
        return drinkable;
    }

    public void setDrinkable(boolean drinkable) {
        this.drinkable = drinkable;
    }

    public boolean isExaminable() {
        return examinable;
    }

    public void setExaminable(boolean examinable) {
        this.examinable = examinable;
    }

    public boolean isDropable() {
        return dropable;
    }

    public void setDropable(boolean dropable) {
        this.dropable = dropable;
    }

    public boolean isGrabable() {
        return grabable;
    }

    public void setGrabable(boolean grabable) {
        this.grabable = grabable;
    }

    public boolean isUseable() {
        return useable;
    }

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

}
