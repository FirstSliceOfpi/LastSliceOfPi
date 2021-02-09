package com.sourdoughsoftware.dictionary;

public class Verb implements DictionaryEntry {
    private String name;
    private VerbGroup group;

    public Verb(String name, VerbGroup group) {
        this.name = name;
        this.group = group;
        addToDictionary();
    }

    @Override
    public String getName() {
        return name;
    }

    public VerbGroup getGroup() {
        return group;
    }
}
