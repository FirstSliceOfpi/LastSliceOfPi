package com.sourdoughsoftware;

import java.util.HashMap;
import java.util.Map;

public class Item implements java.io.Serializable{
    private Integer itemID;
    private String name;
    private String description;
    private String pival;
    private Map<String, String> verbInteraction = new HashMap<>();

    public Map<String, String> getVerbInteraction() {
        return verbInteraction;
    }

    public void setVerbInteraction(String userVerb, String message) {
        this.verbInteraction.put(userVerb, message);
    }

    public String getPival() {
        return pival;
    }

    public void setPival(String pival) {
        this.pival = pival;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item[id=" + getItemID() + ", " +
                "name=" + getName() + ", " +
                "description=" + getDescription() + ", " +
                "pival=" + getPival() + ", " +
                "verbInteractions=" + getVerbInteraction().toString();
    }
}
