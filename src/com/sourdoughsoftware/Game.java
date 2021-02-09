package com.sourdoughsoftware;

import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Prompter;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.Directions;
import com.sourdoughsoftware.utility.InputParser;
import com.sourdoughsoftware.utility.XmlParser;

public class Game {
    //    Maybe this has a board with a list of rooms
    private Player p1;
    private InputParser parser;
    private boolean gameOver = false;
    private final Prompter prompter = new Prompter();

    public Game() {
        //setup methods
//        parser = new InputParser(new Scanner(System.in));
        XmlParser.parseItems();
        XmlParser.parseVerbs();
        Directions east = Directions.EAST;
    }

//    // game loop
//    public void start() throws IOException, SAXException, ParserConfigurationException {
//        System.out.println(WelcomeScreen.getWelcomeMessage());
//        List<Room> rooms = ObjectFromXml.parseRoom();
//        List<Item> items = ObjectFromXml.parseItems();
//
//        for (Room room : rooms) {
//            for (Item item : items) {
//                if (room.getDescription().contains(item.getName())) {
//                    room.addToRoom(item);
//                }
//            }
//        }
////        System.out.println(rooms.toString());
////        System.out.println(items.toString());
////        get room description
//        String userName = parser.prompt("Enter your name adventurer\n>> ");
//        p1 = new Player(userName, 0);
//        String roomDescription = Utilities.getRoomDescription(p1.getPlayerRoomID(), rooms);
//        System.out.println(roomDescription);
//
//        do {
////            give player description of the scene
////            take valid player commands or inform player of invalid choices
////            change game state
////            inform player of the changes
////            loop
//
//            String[] userCommands = parser.promptAction(">> ");
//            if (userCommands[0].equals("go")) {
//                System.out.println(RoomChange.changeRoom(p1.getPlayerRoomID(), userCommands[1], rooms, p1));
//                roomDescription = Utilities.getRoomDescription(p1.getPlayerRoomID(), rooms);
//            }
//            if (userCommands[0].equalsIgnoreCase("look")) {
//                if (Nouns.getAllDirections().contains(userCommands[1])) {
//                    System.out.println(Look.roomLook(p1.getPlayerRoomID(), userCommands[1], rooms, p1));
//                } else if (userCommands[1].equalsIgnoreCase("room")) {
//                    System.out.println(roomDescription);
//                } else {
//                    System.out.println(Look.itemLook(p1.getPlayerRoomID(), userCommands[1], rooms, items));
//                }
//                continue;
//            }
//            if (userCommands[0].equalsIgnoreCase("show") ||
//                    userCommands[0].equalsIgnoreCase("see")) {
//                if (userCommands[1].equalsIgnoreCase("inventory")) {
//                    System.out.println("Your inventory:\n" + p1.formatInventory(p1.getInventory()).toString());
//                    continue;
//                }
//            }
//            if (userCommands[0].equalsIgnoreCase("help") || userCommands[0].equalsIgnoreCase("h")) {
//                System.out.println("Commands:\n" + Verbs.getAllVerbs().toString() + "\nAccess this " +
//                        "help menu at any time: help or h.\nView inventory: show or see inventory.\nQuit at any time: quit or q.");
//                continue;
//            }
//            if (userCommands[0].equalsIgnoreCase("quit") || userCommands[0].equalsIgnoreCase("q")) {
//                setGameOver(true);
//                System.out.println("Thanks for playing!");
//                continue;
//            }
//            if (userCommands[0].equalsIgnoreCase("hint")) {
//                System.out.println("You should find something yummy to eat.");
//                continue;
//            }
//            if (Verbs.getAllVerbs().contains(userCommands[0].toLowerCase()) && Nouns.getAllNouns().contains(userCommands[1].toLowerCase())) {
//                String message = parser.processInteraction(rooms, userCommands, p1);
//                // output verb interaction message or a failure message
//                if (message.contains("congratulations")) {
//                    System.out.println(message);
//                    String playagain = parser.prompt("Do you want to run through the maze again? y/n ");
//                    if (playagain.equalsIgnoreCase("y")) {
//                        start();
//                    } else if (playagain.equalsIgnoreCase("n")) {
//                        System.out.println("Thank you for playing, we'll see you next time!");
//                        setGameOver(true);
//                        continue;
//                    }
//                }
//                System.out.println(message);
//                continue;
//            }
//            /* Function to check message for keywords to trigger win/loss
//            if message.contains("Envy") {gameovaer = true, call win message}
//            if message.contains("poisoned") {gameover = true, call loss}
//            In hansel and gretele
//            when user bake pie commands ->
//            if message.equals("bake") -> call bakepie()
//            bakepie(){
//            check inventory for apple, pan, cinnamon
//            check if oven exists in roomitems
//            if present, output message of "baking in oven"
//            empty player inventory
//            add pie to inventory
//            }
//             */
//            System.out.println(roomDescription);
//        } while (!gameOver);
//    }
//
//
//    public void setGameOver(boolean gameOver) {
//        this.gameOver = gameOver;
//    }
    public void start() {

        while(!gameOver) {
            String userInput = prompter.prompt("What do you want to do?");
            System.out.println(Actions.execute(TextParser.parse(userInput)));
        }
    }
}

