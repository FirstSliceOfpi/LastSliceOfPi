package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.interaction.Event;

import java.io.Serializable;
import java.util.ArrayList;

public class Item extends Noun implements Serializable {

    public Item(String name, String description) {
        super(name, description);
        setGrabable(true);
        setDropable(true);
        this.setAction("grab", new ArrayList<>(){{add(new Event(VerbGroup.dropFromRoom, "dropped from room"));add(new Event(VerbGroup.addToInventory, "It's now in your inventory"));}});
        this.setAction("pick up", new ArrayList<>(){{add(new Event(VerbGroup.dropFromRoom, "dropped from room"));add(new Event(VerbGroup.addToInventory, "It's now in yur inventory.1"));}});
        this.setAction("get", new ArrayList<>(){{add(new Event(VerbGroup.dropFromRoom, "dropped from room"));add(new Event(VerbGroup.addToInventory, ". It's now in your inventory"));}});
        this.setAction("drop", new ArrayList<>(){{add(new Event(VerbGroup.dropFromInventory, "dropped to inventory"));add(new Event(VerbGroup.addToRoom, "It's added to room"));}});

    }

    public Item(Item item) {
        super(item.getName(), item.getDescription());
        setGrabable(true);
        setDropable(true);
    }

    public Item() {}

    public String toString() {
        return this.getName();
    }

}
