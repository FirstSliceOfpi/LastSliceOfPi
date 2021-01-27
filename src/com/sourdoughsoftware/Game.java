package com.sourdoughsoftware;

import com.sourdoughsoftware.InputParser;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        InputParser prompt = new InputParser(new Scanner(System.in));
        WelcomeScreen.getWelcomeMessage();
        String userEntry = prompt.prompt(">> ");
        System.out.println(userEntry);
    }
}
