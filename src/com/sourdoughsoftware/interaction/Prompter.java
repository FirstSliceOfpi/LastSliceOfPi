package com.sourdoughsoftware.interaction;

import java.util.Scanner;

public class Prompter {
    static Scanner scanner = new Scanner(System.in);

    public static String prompt(String prompt) {
        System.out.println(prompt);
        System.out.print(" > ");
        return scanner.nextLine();
    }
}
