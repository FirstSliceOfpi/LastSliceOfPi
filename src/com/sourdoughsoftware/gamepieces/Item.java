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
        this.setAction("grab", new ArrayList<>(){{add(new Event(VerbGroup.addToInventory, "added to inventory"));add(new Event(VerbGroup.dropFromRoom, "dropped from room"));}});
        this.setAction("drop", new ArrayList<>(){{add(new Event(VerbGroup.dropFromInventory, "dropped to inventory"));add(new Event(VerbGroup.addToRoom, "add from room"));}});

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
