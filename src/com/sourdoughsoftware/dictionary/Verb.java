package com.sourdoughsoftware.dictionary;

public class Verb implements DictionaryEntry {
    private String name;
    private Integer group;

    public Verb(String name, Integer group) {
        this.name = name;
        this.group = group;
        addToDictionary();
    }

    @Override
    public String getName() {
        return name;
    }
}
