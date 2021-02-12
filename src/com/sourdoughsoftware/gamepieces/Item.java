package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.dictionary.Noun;

import java.io.Serializable;

public class Item extends Noun implements Serializable {

    public Item(String name, String description) {
        super(name, description);
        setGrabbale(true);
        setDropable(true);
    }

    public Item(Item item) {
        super(item.getName(), item.getDescription());
        setGrabbale(true);
        setDropable(true);
    }

    public Item() {}

    public String toString() {
        return this.getName();
    }

}
