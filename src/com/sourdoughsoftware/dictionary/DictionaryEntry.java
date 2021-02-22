package com.sourdoughsoftware.dictionary;

public interface DictionaryEntry {
    Dictionary dictionary = Dictionary.INSTANCE;

    default void addToDictionary() {
        dictionary.add(this);
    }

    String getName();
}
