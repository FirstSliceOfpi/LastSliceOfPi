package com.sourdoughsoftware.dictionary;

import java.io.Serializable;

public class Verb implements DictionaryEntry, Serializable {
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
