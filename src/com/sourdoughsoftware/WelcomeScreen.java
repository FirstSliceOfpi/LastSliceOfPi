package com.sourdoughsoftware;

import org.w3c.dom.ls.LSOutput;

/**
 * Welcome screen displays a welcome message to the player when the game begins.
 *
 * @author Tyrone Moore
 * @version 1.0.0
 */
public class WelcomeScreen {
    public static final String WELCOME_MESSAGE = "Welcome to \"The Last Slice of Pi\"";

    public WelcomeScreen() {}


    /**
     * @return the welcome message
     */
    public static void getWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
