package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.dictionary.Noun;

public class Item extends Noun {

    public Item(String name, String description) {
        super(name, description);
        setGrabbale(true);
        setDropable(true);
    }


}
