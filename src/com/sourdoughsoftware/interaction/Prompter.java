package com.sourdoughsoftware.interaction;

import java.util.Scanner;

public class Prompter {
    Scanner scanner = new Scanner(System.in);

    public String prompt(String prompt) {
        System.out.println(prompt);
        System.out.print(" > ");
        return scanner.nextLine();
    }
}
