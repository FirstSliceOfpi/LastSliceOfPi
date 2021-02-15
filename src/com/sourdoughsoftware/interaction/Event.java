package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.VerbGroup;

public class Event {
    public VerbGroup verbGroup;
    public String message;
    public Noun key = null;

    public Event(VerbGroup verbGroup, String message) {
        this.verbGroup = verbGroup;
        this.message = message;
    }

    public Event(VerbGroup verbGroup, String message, Noun key) {
        this(verbGroup, message);
        this.key = key;
    }
}
