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
            if (response.length != 2) {
                System.out.println("Please enter one verb and one noun");
            }
            else {
                String verb= response[0];
                String noun = response[1];

                if (!isValidVerb(verb)) {
                    System.out.println("Invalid Verb");
                }

                else if (!isValidNoun(noun)) {
                    System.out.println("Invalid Noun");
                }
                // // TODO: 1/28/2021 Logic here to check for verb/noun combination

//                {
//                    System.out.println("Invalid verb and noun combination");
//                }

                else{
                    break;
                }

            }
        }
//        System.out.println(Arrays.toString(response));
        return response;
    }

    public boolean isValidVerb(String word) {
        for (Verbs verb : Verbs.values()) {
//            System.out.println(verb);
            if (word.equalsIgnoreCase(String.valueOf(verb))) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidNoun(String word) {
        for (Nouns noun : Nouns.values()) {
//            System.out.println(verb);
            if (word.equalsIgnoreCase(String.valueOf(noun))) {
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        InputParser prompt = new InputParser(new Scanner(System.in));
//        String userEntry = prompt.prompt(">> ");
////        if (prompt.isValidVerb(userEntry)) {
//        System.out.println(userEntry);
////        } else {
////            System.out.println("Invalid Entry");
////        }
//    }
}
