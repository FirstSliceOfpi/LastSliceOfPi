package com.sourdoughsoftware;

import java.util.Scanner;

public class InputParser {
    private Scanner scanner;

    // Constructors
    public InputParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public String prompt(String userInput) {
        System.out.print(userInput);
        return scanner.nextLine();
    }

//    public static void main(String[] args) {
//        InputParser prompt = new InputParser(new Scanner(System.in));
//        String userEntry = prompt.prompt(">> ");
//        System.out.println(userEntry);
//    }
}
