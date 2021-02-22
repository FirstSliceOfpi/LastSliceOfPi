package com.sourdoughsoftware.interaction;

public class ChainOfEventException extends Exception {
    public ChainOfEventException(String s) {
        super(s);
    }
    //for interrupting a chain of events
}
