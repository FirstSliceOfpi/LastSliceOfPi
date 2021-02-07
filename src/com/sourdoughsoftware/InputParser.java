package com.sourdoughsoftware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public String[] promptAction(String userInput) {
        String[] response = {};

        while (true) {
            System.out.print(userInput);
            response = scanner.nextLine().split(" ");
            // TODO: 2/3/2021 replace all of these add methods with an add of the arraylist pulled from verbs
            List<String> oneVerbCommand = Arrays.asList("help", "h", "quit", "q");
            if (response.length == 1 && oneVerbCommand.contains(response[0].toLowerCase())) {
                if (!isValidVerb(response[0])) {
                    System.out.println("Invalid Verb");
                }
                else {
                    break;
                }
            }
            else if (response.length == 2) {
                String verb = response[0];
                String noun = response[1];

                if (!isValidVerb(verb)) {
                    System.out.println("Invalid Verb");
                } else if (!isValidNoun(noun) && !isValidDirection(noun)) {
                    System.out.println("Invalid Noun");
                }
                // // TODO: 1/28/2021 Logic here to check for verb/noun combination

//                {
//                    System.out.println("Invalid verb and noun combination");
//                }
                else {
                    break;
                }
            }
            else {
                System.out.println("Please enter a single verb command or a differetn verb/noun combination.");
            }
        }
        return response;
    }

    public boolean isValidVerb(String word) {
        for (Verbs verb : Verbs.values()) {
            if (word.equalsIgnoreCase(String.valueOf(verb))) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidNoun(String word) {
        for (Nouns noun : Nouns.values()) {
            if (word.equalsIgnoreCase(String.valueOf(noun))) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDirection(String word) {
        for (Nouns.DIRECTIONS noun : Nouns.DIRECTIONS.values()) {
            if (word.equalsIgnoreCase(String.valueOf(noun))) {
                return true;
            }
        }
        return false;
    }

}
