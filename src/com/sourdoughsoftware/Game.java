package com.sourdoughsoftware;

import com.sourdoughsoftware.InputParser;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        InputParser prompt = new InputParser(new Scanner(System.in));
        System.out.println("This is gonna be great.");
        String userEntry = prompt.prompt(">> ");
        System.out.println(userEntry);

    }
}
