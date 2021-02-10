package com.sourdoughsoftware.dictionary;

public class Verb implements DictionaryEntry {
    private final String name;
    private final VerbGroup group;

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
