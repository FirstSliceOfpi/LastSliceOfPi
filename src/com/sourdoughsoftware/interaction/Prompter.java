package com.sourdoughsoftware.interaction;

import java.util.Scanner;

public class Prompter {
    Scanner scanner;

    public Prompter(Scanner scanner) {
        this.scanner = scanner;
    }

    public String prompt(String prompt) {
        System.out.println(prompt);
        System.out.print(" > ");
        return scanner.nextLine();
    }
}
