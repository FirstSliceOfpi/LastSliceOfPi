package com.sourdoughsoftware.dictionary;

public class Noun implements DictionaryEntry {

    private final String name;
    private final String description;


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
    private boolean grabbale = false;
    private boolean useable = false;
    private boolean mergeable = false;

    public Noun(String name, String description) {

        this.name = name;
        this.description = description;
        addToDictionary();
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public boolean isGrabbale() {
        return grabbale;
    }

    public void setGrabbale(boolean grabbale) {
        this.grabbale = grabbale;
    }

    public boolean isUseable() {
        return useable;
    }

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

}
