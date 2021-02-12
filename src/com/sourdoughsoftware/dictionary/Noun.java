package com.sourdoughsoftware.dictionary;

import com.sourdoughsoftware.interaction.Actions;

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
    public ArrayList<String[]> light = new ArrayList<>(){{
        add(new String[]{"print", "printMe"});
        add(new String[]{"changeDescription", "changeMe"});
    }};

    public void setLight(String[][] lightable) {
        this.light = light; // [["print","printMe"],["changeDesc", "changeMe"]]
    }

    public void getAction(String verb) {
        try {
            Method method = this.getClass().getMethod("get"+verb,null);
            ArrayList<String[]> action = (ArrayList) method.invoke(this,null);
            Actions act = new Actions();
            for (int i = 0; i < action.size(); i++) {
                try {
                    Method meth = act.getClass().getMethod(action.get(i)[0], String.class);
                    meth.invoke(meth, action.get(i)[1]);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String[]> getlight(){
        return light;
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

//    isLightable(true,false,null);
//    isLightable(true,true,[[print, this is lit], [changeDescription, my description is change]]);
//    boolean isLightable(boolean lightable, boolean shouldLight, String[] ... args) {
//        if(this.isLightable == true && shouldLight) {
//            for(String[] method : args) {
//                try {
//                    Method meth = this.getClass().getMethod(method[0], String.class);
//                    meth.invoke(meth, method[1]);
//                } catch (Exception e) {
//                    // do nothing
//                }
//            }
//
//        }
//        return lightable;
//    }
//
//    private void print(String str) { return "";}


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
