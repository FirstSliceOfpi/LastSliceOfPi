package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import com.sourdoughsoftware.Player;
import com.sourdoughsoftware.Room;

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
        String[] response;

        while (true) {
            System.out.print(userInput);
            response = scanner.nextLine().split(" ");
            // TODO: 2/3/2021 replace all of these add methods with an add of the arraylist pulled from verbs
            List<String> oneVerbCommand = Arrays.asList("help", "h", "quit", "q", "hint");
            if (response.length == 1 && oneVerbCommand.contains(response[0].toLowerCase())) {
                if (isInvalidVerb(response[0])) {
                    System.out.println("Invalid Verb");
                } else {
                    break;
                }
            } else if (response.length == 2) {
                String verb = response[0];
                String noun = response[1];

                if (isInvalidVerb(verb)) {
                    System.out.println("Invalid Verb");
                } else if (isInvalidNoun(noun) && isInvalidDirection(noun)) {
                    System.out.println("Invalid Noun");
                } else {
                    break;
                }
            } else {
                System.out.println("Please enter a single verb command or a different verb/noun combination.");
            }
        }
        return response;
    }

    public boolean isInvalidVerb(String word) {
        for (Verbs verb : Verbs.values()) {
            if (word.equalsIgnoreCase(String.valueOf(verb))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInvalidNoun(String word) {
        for (Nouns noun : Nouns.values()) {
            if (word.equalsIgnoreCase(String.valueOf(noun))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInvalidDirection(String word) {
        for (Nouns.DIRECTIONS noun : Nouns.DIRECTIONS.values()) {
            if (word.equalsIgnoreCase(String.valueOf(noun))) {
                return false;
            }
        }
        return true;
    }

    public String processInteraction(List<Room> rooms, String[] userCommands, Player p1) {
        String message = "You try to " + userCommands[0].toLowerCase() + " the " + userCommands[1].toLowerCase() + " but nothing happens.";
        // get room items against player location
        List<Item> playerRoomItemsToCheck = Utilities.getPlayerRoomItems(p1.getPlayerRoomID(), rooms);
        List<Item> playerInventoryItemsToCheck = p1.getInventory();

        if (userCommands[0].equalsIgnoreCase("take") ||
                userCommands[0].equalsIgnoreCase("get")) {
            // Get player item
            Item playerItem = new Item();
            for (Item item : playerRoomItemsToCheck) {
                if (item.getName().equalsIgnoreCase(userCommands[1])) {
                    playerItem = item;
                    break;
                }
            }
            message = p1.takeItem(rooms, playerItem);

        } else if (userCommands[0].equalsIgnoreCase("examine")) {
            boolean found = false;
            Item foundItem = new Item();
            for (Item item : playerInventoryItemsToCheck) {
                if (item.getName().equalsIgnoreCase(userCommands[1])) {
                    foundItem = item;
                    found = true;
                    break;
                }
            }
            if (found) {
                message = foundItem.getDescription();
            } else {
                message = "You aren't holding the " + userCommands[1];
            }

        } else if (userCommands[0].equalsIgnoreCase("bake") && userCommands[1].equalsIgnoreCase("pi")) {
            if (p1.getPlayerRoomID().equals(6)) {
                if (p1.isHasPi()) {
                    message = "You bake a PI, and eat it - congratulations!";

                } else {
                    message = "You don't have enough pieces of PI to bake a PI yet!";
                }
            } else {
                message = "This isn't the right place to try to bake a PI...";
            }
        } else {
            // check item for verb interaction map
            for (Item item : playerInventoryItemsToCheck) {
                if (item.getVerbInteraction().containsKey(userCommands[0].toLowerCase()) &&
                        item.getName().equalsIgnoreCase(userCommands[1])) {
                    message = item.getVerbInteraction().get(userCommands[0].toLowerCase());
                    break;
                }
            }
            for (Item item : playerRoomItemsToCheck) {
                if (item.getVerbInteraction().containsKey(userCommands[0].toLowerCase()) &&
                        item.getName().equalsIgnoreCase(userCommands[1])) {
                    message = item.getVerbInteraction().get(userCommands[0].toLowerCase());
                    break;
                }
            }
        }
        return message;
    }
}
