package com.sourdoughsoftware;

public class Item {
    String response = "You cannot use that item that way.";

    private Integer itemID;
    private String name;
    private String description;

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
                "description=" + getDescription();
    }
}
