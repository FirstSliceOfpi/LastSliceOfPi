package com.sourdoughsoftware.utility;

import java.util.Random;

public class Colors {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BG = "\u001B[40m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";
    public static final String ANSI_BLUE_BG = "\u001B[44m";
    public static final String ANSI_PURPLE_BG = "\u001B[45m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";
    public static final String ANSI_WHITE_BG = "\u001B[47m";

    public static final String ANSI_FLASHING = "\u001B[5m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_DIMMER = "\u001B[2m";

    public static String randomColor(){
        String[] colors = {ANSI_BLACK, ANSI_BLUE, ANSI_CYAN, ANSI_GREEN, ANSI_WHITE, ANSI_RED, ANSI_PURPLE};
        Random random = new Random();
        int upperBound = colors.length;
        int colorSelection = random.nextInt(upperBound);
        return colors[colorSelection];
    }

}
