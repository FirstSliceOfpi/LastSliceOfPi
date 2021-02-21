package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.utility.Cheat;
import com.sourdoughsoftware.utility.CombinePies;
import com.sourdoughsoftware.utility.Node;
import com.sourdoughsoftware.utility.PrintFiles;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.world.Room;

import com.sourdoughsoftware.world.World;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.sourdoughsoftware.utility.Colors.*;


public class Actions {

    public static String execute() throws ChainOfEventException {

        if (Command.getVerb() == null) {
            return "I am confused. What do you want me to do?";
        }

        if (Command.getNoun() == null && !(Command.getVerb().getGroup() == VerbGroup.save
                || Command.getVerb().getGroup() == VerbGroup.load
                || Command.getVerb().getGroup() == VerbGroup.quit
                || Command.getVerb().getGroup() == VerbGroup.dev
                || Command.getVerb().getGroup() == VerbGroup.show
                || Command.getVerb().getGroup() == VerbGroup.help
        )) {
            return "That doesn't make sense.";
        }

        switch (Command.getVerb().getGroup()) {
            case move:
                return move(Command.getNoun());
            case merge:
                return merge(Command.getNoun(), Command.getVerb(), Command.getTargetNoun());
            case save:
                return save();
            case load:
                return load();
            case quit:
                return quit();
            case dev:
                return dev();
            case getDescription:
                return getDescription();
            case feed:
                return feed(Command.getNoun(), Command.getTargetNoun());
            case attack:
                return attack(Command.getNoun(),Command.getVerb(), Command.getTargetNoun());
            case show:
                return show();
            case help:
                return help();
            default:
                if (Command.getTargetNoun() == null) {
                    return Command.getNoun().getAction(Command.getVerb().getName());
                } else {
                    return Command.getTargetNoun().getAction(Command.getVerb().getName());
                }
        }
    }

//    /**
//     * Method for allowing to remove something from the inventory after printing a string
//     * @param str
//     * @return
//     */
//    public static String use(String str) {
//        dropFromInventory(str);
//        return str;
//    }

    /**
     * Method for removing a generic from inventory and rewarding a pie
     * @param str
     * @return
     */
    public static String reward(String str) {
        Noun noun = Dictionary.INSTANCE.getNoun(str);

        if(noun== null) {
            return "Carry on my wayward son";
        } else  {
            World.getCurrentRoom().dropItem(Command.getNoun());
            if(Command.getTargetNoun() == null) {
                Command.setTargetNoun(Command.getNoun());
            }
            Command.setNoun(noun);
            addToInventory("");
            return "You have been rewarded a " + noun.getName()+". ";
        }


    }

    /**
     * Method for showing nouns and verbs available in the game
     * @return
     */
    public static String help() {
        StringBuilder sb = new StringBuilder("Try these nouns: \n");
        String[] keys = Dictionary.INSTANCE.getNouns().keySet().toArray(String[]::new);
        for(int i = 0; i < keys.length; i++){
            if(i%3 != 0) {
                sb.append(keys[i]).append(calculateSpace(keys[i].length()));
            }else {
                sb.append("\n").append(keys[i]).append(calculateSpace(keys[i].length()));
            }
        }
        sb.append("\n\n----verbs----\n");
        keys = Dictionary.INSTANCE.getVerbs().keySet().toArray(String[]::new);
        for(int i = 0 ; i < keys.length; i++) {
            if(i%3 != 0) {
                sb.append(keys[i]).append(calculateSpace(keys[i].length()));
            }else {
                sb.append("\n").append(keys[i]).append(calculateSpace(keys[i].length()));
            }
        }
        sb.append("\n\n Also try: examine cook book");
        return sb.toString();
    }
    // Helper method for help() to put nouns and verbs in nice columns
    private static String calculateSpace(int wordLength) {
        int columnWidth = 30;
        int numberOfSpaces = columnWidth - wordLength;
        StringBuilder space = new StringBuilder();
        while(numberOfSpaces > 0) {
            space.append(" ");
            numberOfSpaces--;
        }
        return space.toString();
    }

    /**
     * Method for taking a noun completely out of the game play
     * @param message
     * @return
     */
    public static String destroyNoun(String message) {
        Noun noun = null;
        if(Command.getTargetNoun() == null) {
            noun = Command.getNoun();
        } else {
            noun = Command.getTargetNoun();
        }

        World.getCurrentRoom().dropItem(noun);
        GameState.getPlayer().getInventory().drop(noun);
        Dictionary.INSTANCE.deleteNoun(noun);
        Dictionary.INSTANCE.killNounRespawn(noun);

        return message;
    }

    /**
     * Method for adding a new noun to game play (usually from XML document actions)
     * @param name
     * @return
     */
    public static String createNoun(String name) {
        Noun noun = new Noun(name, "this is a noun");
        Dictionary.INSTANCE.add(noun);
        return name + " created";
    }

    /**
     * Method for removing all items from inventory
     * @param message
     * @return
     */
    public static String destroyAll(String message) {
        GameState.getPlayer().getInventory()
                .getCurrentInventory().forEach(item->
                    GameState.getPlayer().getInventory().drop(item)
                );
        return message;
    }

    /**
     * Method for displaying the description of a room or other noun
     * @return
     */
    public static String getDescription() {
        if (Command.getNoun().getName().equals("room")) {
            return  World.getCurrentRoom().getRoomItems() + ANSI_RESET;
        }
        return Command.getNoun().getDescription() + ANSI_RESET;
    }

    /**
     * Method to change the description of a noun (usually after another action is performed)
     * @param newDescription
     * @return
     */
    public static String changeDescription(String newDescription) {
        Noun noun;
        if(Command.getTargetNoun() != null) {
            noun = Command.getTargetNoun();
        } else {
            noun = Command.getNoun();
        }
        noun.setDescription(newDescription);
        return noun.getDescription();
    }

    /**
     * Method to enter development mode for showing error messages and other features
     * @return
     */
    public static String dev() {
        GameState.setDevMode();
        return GameState.getDevMode()
                ? ANSI_BLUE + "Dev mode enabled" + ANSI_RESET
                : ANSI_YELLOW + "Dev mode disabled" + ANSI_RESET;
    }

    /**
     * Method to end the game
     * @return
     */
    public static String quit() {
        String response = Prompter.prompt("Are you sure you want to exit?(Y/N)");
        String cleansedResponse = response.strip().toLowerCase();

        if (cleansedResponse.equals("y") || cleansedResponse.equals("yes")) {
            System.out.println("And they lived happily ever after. The end.");
            System.exit(0);
        }
        return "Error in quit";
    }

    /**
     * Method to initiate the save process
     * @return
     */
    public static String save() {
        Path path = Paths.get("./saved_games");
        File dir = new File("./saved_games");
        if (!Files.exists(path)) {
            dir.mkdirs();
        }
        String fileName = Prompter.prompt("What do you want to name your save file?");
        File fileToSave = new File(dir, fileName);
        return GameState.saveGame(fileToSave) ?
                "Your game -- " + ANSI_GREEN + fileToSave + ANSI_RESET + " -- was saved."
                : ANSI_RED + "Your game was not saved." + ANSI_RESET;
    }

    /**
     * Method to initiate the loading saved game process
     * @return
     */
    public static String load() {
        File dir = new File("./saved_games");
        if(Objects.isNull(dir) || Objects.isNull(dir.list())) { return "No saved games."; }
        for (String file : dir.list()) {
            System.out.println(ANSI_BLUE + file + ANSI_RESET);
        }
        String fileName = Prompter.prompt("What game would you like to load?");
        File fileToLoad = new File(dir, fileName);
        return GameState.loadGame(fileToLoad) ?
                "Your game -- " + ANSI_GREEN + fileToLoad + ANSI_RESET + " -- was loaded."
                : ANSI_RED + "Your game was not loaded." + ANSI_RESET;
    }
    /**
     * merge or combine to weapons for a higher level weapon
     * @param noun
     * @param verb
     * @param targetNoun
     * @return
     */
    public static String merge(Noun noun, Verb verb, Noun targetNoun) {
        VerbGroup group = verb.getGroup();

        if (group.equals(VerbGroup.merge) && targetNoun == null) {
            return "You need two items to merge";
        }

        if (!GameState.getDevMode()) {
            if (!GameState.getPlayer().getInventory().has(noun) || !GameState.getPlayer().getInventory().has(noun)) {
                return "One or more items are not in your inventory.";
            }
        }
        Node weapon1Node = GameState.getTree().find(noun.getName());
        Node weapon2Node = GameState.getTree().find(targetNoun.getName());
        Pie pie1 = null;
        Pie pie2 = null;
        if (weapon1Node != null && weapon1Node.getItem() != null) {
            pie1 = (Pie) weapon1Node.getItem();
        } else {
            return "You can't merge these two items.";
        }
        if (weapon2Node != null && weapon2Node.getItem() != null) {
            pie2 = (Pie) weapon2Node.getItem();
        } else {
            return "You can't merge these two items.";
        }
        Pie combinedPie = CombinePies.combine(pie1, pie2, GameState.getTree());
        if (combinedPie != pie1) {
            GameState.getPlayer().getInventory().drop(noun);
            GameState.getPlayer().getInventory().drop(targetNoun);
            GameState.getPlayer().getInventory().add(combinedPie);
            return "YOU " + verb.getName() + "d " + noun.getName()
                    + " and " + targetNoun.getName()
                    + " to make a " + combinedPie.getName();
        } else {
            return "you can't merge a " + noun.getName() + " and a " + targetNoun.getName() + " together";
        }
    }

    private static String move(Noun noun) {
        if (noun.getName().equals("bananas")) {
            return printTy();
        }
        if (noun instanceof Directions.Direction) {
            return World.changeCurrentRoom((Directions.Direction) noun) +
                    " " + ANSI_YELLOW +
                    World.getCurrentRoom().getDescription() +
                    ANSI_RESET;
        }
        return "That's not a direction \n";
    }

    // Ty Easter Egg (prints on go bananas)
    private static String printTy() {
        PrintFiles pf = new PrintFiles();
        pf.print("ty");
        return "";
    }

    public static String show() {
        StringBuilder builder = new StringBuilder();
        List<Noun> inventory = GameState.getPlayer().getInventory().getCurrentInventory();
        if(inventory.size() == 0) return "No items";
        for(Noun noun : inventory) {
            if(noun instanceof Pie) {
                builder.append(ANSI_RESET);
                builder.append(ANSI_GREEN);
            }
            builder.append(noun.getName() + "\n");
            builder.append(ANSI_RESET);
        }
        return builder.toString();
    }

    public static String print(String str) {
        return str;
    }

    public static String dropFromRoom(String message) throws ChainOfEventException {
        Room currentRoom = World.getCurrentRoom();
        Noun noun = Command.getNoun();
        if (noun instanceof Item) {
            noun = currentRoom.dropItem(noun);
        }
        if (Command.getTargetNoun() instanceof Enemy) {
            noun = currentRoom.dropEnemy(Command.getTargetNoun());
        }
        if(noun == null) {
            throw new ChainOfEventException("That is not in that room");
        }
        return Command.getNoun() + " no longer in " + currentRoom.getName();
    }

    public static String addToRoom(String message) {
        Room currentRoom = World.getCurrentRoom();
        Noun noun = Command.getNoun();
        currentRoom.addToRoom((Item) noun);
        return Command.getNoun() + " is now located in " + currentRoom.getName()+".";
    }

    public static String dropFromInventory(String message) {
        Player player = GameState.getPlayer();
        Noun noun = Command.getNoun();
        player.getInventory().drop(noun);
        return "You drop the " + noun.getName()+".";

    }

    public static String addToInventory(String str) {
        Player player = GameState.getPlayer();
        Noun noun = Command.getNoun();
        player.getInventory().add(noun);
        return Objects.requireNonNullElseGet(str, () -> "You grabbed " + noun.getName());
    }

    public static String cheat(String cheat) {
        if(cheat.equals("amazon")) {
           return Cheat.getInstance().addAllPiesToInventory();
        }else if(cheat.equals("tra")) {
            try {
               return Cheat.getInstance().showCheatArt();
            }catch(Exception e) {
                if(GameState.getDevMode()) System.out.println(e);
            }
        }else if(cheat.equals("power me")){
            return Cheat.getInstance().doubleAttackPoints();
        }else if(cheat.equals("super power")){
            return Cheat.getInstance().doubleAllAP();
        }else if (cheat.equals("map")) {
            return Cheat.getInstance().getMap();
        }else if (cheat.equals("jay do you have our analytics graded?")) {
            return Cheat.getInstance().getJay();
        }
        return "Can not perform that cheat.";
    }


//    private static String wield(Noun noun, Verb verb) {
//        if (noun.isWieldable()) {
//            return "YOU now "+ verb.getName() + " " + noun.getName() + noun.getDescription();
//        } else {
//            return noun.getName() + " is not a weapon";
//        }
//    }

    public static String feed(Noun noun, Noun targetNoun) {
        if(!World.getCurrentRoom().has(targetNoun)) {
            return targetNoun.getName() + " not in this room.";
        }
        if(!GameState.getPlayer().getInventory().has(noun)) {
            return "You don't have that!";
        }
        if(!(noun instanceof Pie)) {
            return noun.getName() +  " isn't even edible. jeez do we gotta hold your hand through this whole game? play smart";
        }
        if(!(targetNoun instanceof Enemy)) {
            return "Oh yeah? You're gonna feed a " + targetNoun + " some " + noun +"? You feel dumb right now huh";
        }
        return ((Enemy) targetNoun).feed((Pie) noun);
    }


    public static String attack(Noun noun, Verb verb, Noun targetNoun) throws ChainOfEventException{
        if(noun instanceof Enemy && targetNoun instanceof Pie) {
            return "Please use english and use 'with' ";
        }
        if(!(noun instanceof Pie)) {
            return "What are you doing sir? This is a children's game. You can't just go around attacking people with " + noun.getName()+". Try using food you savage.";
        }
        if(targetNoun == null) {
            return "You're gonna attack what?";
        }
        if(!(targetNoun instanceof Enemy)) {
            return "Oh your gonna attack a " + targetNoun.getName()+". And whats that gonna solve?";
        }
        if(!World.getCurrentRoom().has(targetNoun) && targetNoun instanceof Enemy) {
            return targetNoun.getName() + " isn't here.";
        }
        int WEAPON_MULTIPLIER = 100;
        StringBuilder response = new StringBuilder();
        if (targetNoun.isAttackable() && GameState.getPlayer().getInventory().has(noun)) {
            Enemy enemy = (Enemy) targetNoun;
            Pie weapon = (Pie) noun;
            GameState.getPlayer().getInventory().drop(noun);
            if (enemy.getHp() > 0) {
                int newHP = enemy.getHp() - (weapon.getAttackPoints()*WEAPON_MULTIPLIER);
                enemy.setHp(newHP);
                response.append("You " + verb.getName() + " " + enemy.getName() + " with " + noun.getName() + "."
                       + "\n"+ enemy.getName() + " has " + enemy.getHp() +" hp remaining");

            } //DONE: Create Enemy victory message to place here
            if (enemy.getHp() <= 0) {
                StringBuilder sb = new StringBuilder();
                dropFromRoom("bye");
                sb.append(GameState.getCookBook().addRecipe());
                sb.append("\n");
                try {
                    Pie pie = (Pie) Command.getNoun();
                    sb.append(pie.getVictory());
                    sb.append("\n");
                }catch(Exception e) {
                    // do nothing
                }
                sb.append(enemy.getDeadtext());
                return sb.toString();
            }
        }else {
            return "Item not in inventory.";
        }

        return response.toString();
    }

    public static String checkIfAvailable() {
        if(Command.getTargetNoun() != null && !World.getCurrentRoom().has(Command.getTargetNoun()) && !GameState.getPlayer().getInventory().has(Command.getTargetNoun())) {
            return Command.getTargetNoun() + " isn't here";
        }
        if(Command.getNoun() != null && !World.getCurrentRoom().has(Command.getNoun()) && !GameState.getPlayer().getInventory().has(Command.getNoun())) {
            return Command.getNoun() + " isn't here";
        }
        return null;
    }

   }
