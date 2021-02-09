package com.sourdoughsoftware.dictionary;

public class Noun implements DictionaryEntry {

    String description;
    String name;

    public Noun(String name, String description) {
        this.name = name;
        this.description = description;
        addToDictionary();
    }

    @Override
    public String getName() {
        return name;
    }
}
