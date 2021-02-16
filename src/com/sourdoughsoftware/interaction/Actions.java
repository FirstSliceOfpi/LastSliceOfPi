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
import com.sourdoughsoftware.utility.Colors;
import com.sourdoughsoftware.utility.Cheat;
import com.sourdoughsoftware.utility.CombinePies;
import com.sourdoughsoftware.utility.Node;
import com.sourdoughsoftware.utility.PrintFiles;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.world.Room;

import com.sourdoughsoftware.world.World;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.List;

import static com.sourdoughsoftware.utility.Colors.*;


public class Actions {

    public static String execute() {

        if (Command.getVerb() == null) {
            return "no verb in input";
        }
        if (Command.getNoun() == null && !(Command.getVerb().getGroup() == VerbGroup.save
                || Command.getVerb().getGroup() == VerbGroup.load
                || Command.getVerb().getGroup() == VerbGroup.quit
                || Command.getVerb().getGroup() == VerbGroup.dev
                || Command.getVerb().getGroup() == VerbGroup.SHOW)) {
            return "no noun in input";
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
//            case print:
//                return print();
            case WIELD:
                return wield(Command.getNoun(), Command.getVerb());
            case attack:
                return attack(Command.getNoun(),Command.getVerb(), Command.getTargetNoun());
//            case EXAMINE:
//                return print();
            case SHOW:
                return show();
            default:
                if (Command.getTargetNoun() == null) {
                    return Command.getNoun().getAction(Command.getVerb().getName());
                } else {
                    return Command.getTargetNoun().getAction(Command.getVerb().getName());
                }
        }
    }

    public static String destroyNoun(String message) {
       // Dictionary.INSTANCE.deleteNoun(Command.getNoun());
        return message;
    }

    public static String createNoun(String name) {
        Noun noun = new Noun(name, "this is a noun");
        Dictionary.INSTANCE.add(noun);
        return name + " created";
    }

    public static String getDescription() {
        if (Command.getNoun().getName().equals("room")) {
            return  World.getCurrentRoom().getRoomItems() + ANSI_RESET;
        }
        return Command.getNoun().getDescription() + ANSI_RESET;
    }

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

    public static String dev() {
        GameState.setDevMode();
        return GameState.getDevMode()
                ? ANSI_BLUE + "Dev mode enabled" + ANSI_RESET
                : ANSI_YELLOW + "Dev mode disabled" + ANSI_RESET;
    }

    public static String quit() {
        String response = Prompter.prompt("Are you sure you want to exit?(Y/N)");
        String cleansedResponse = response.strip().toLowerCase();

        if (cleansedResponse.equals("y") || cleansedResponse.equals("yes")) {
            System.out.println("And they lived happily ever after. The end.");
            System.exit(0);
        }
        return "Error in quit";
    }

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

//    private static String examine(Noun noun) {
//        StringBuilder result = new StringBuilder();
//        result.append("\n");
//        if (noun.getName().equals("room")) {
//            if(gs.getRoom().getRoomItems().size() == 0) {
//                return "You find nothing in the room.";
//            }
//            result.append("You find");
//            for (Item item : gs.getRoom().getRoomItems()) {
//                if (item instanceof Enemy) {
//                    result.append(" " + item.getName() + " ( " + ((Enemy)item).getHp() + " ),");
//                }else {
//                    result.append(" " + item.getName() + ",");
//                }
//
//            }
//            result.append(" in the room.");
//        } else {
//            result.append(noun.getDescription());
//        }
//        return result.toString();
//    }

    // merge or combine to weapons for a higher level weapon
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
        if (weapon1Node != null) {
            pie1 = (Pie) weapon1Node.getItem();
        }
        if (weapon2Node != null) {
            pie2 = (Pie) weapon2Node.getItem();
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
            return World.changeCurrentRoom((Directions.Direction) noun);
        }
        return "That's not a direction";
    }

    // Ty Easter Egg
    private static String printTy() {
        PrintFiles pf = new PrintFiles();
        pf.print("ty");
        return "";
    }

//    private static String grab(Noun noun) {
//        if(!noun.isFindable()) { return "You can not pick up " + noun.getName(); }
//        String success = noun.getName() + " not found in the room.";
//        if(gs.getRoom().removeItem((Item) noun)) {
//            success = Player.getPlayer().getInventory().add(noun);
//        }
//        return success;
//    }

    public static String show() {
        StringBuilder builder = new StringBuilder();
        List<Noun> inventory = GameState.getPlayer().getInventory().getCurrentInventory();
        if(inventory.size() == 0) return "No items";
        for(Noun noun : inventory) {
            builder.append(noun.getName() + "\n");
        }
        return builder.toString();
    }

    public static String print(String str) {
        return str;
    }

    public static String dropFromRoom(String message) throws ChainOfEventException {
        Room currentRoom = World.getCurrentRoom();
        Noun noun = Command.getNoun();
        noun = currentRoom.dropItem(noun);
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
        }
        return "Can not perform that cheat.";
    }


    private static String wield(Noun noun, Verb verb) {
        if (noun.isWieldable()) {
            return "YOU now "+ verb.getName() + " " + noun.getName() + noun.getDescription();
        } else {
            return noun.getName() + " is not a weapon";
        }
    }

    private static String attack(Noun noun, Verb verb, Noun targetNoun) {
        if(Objects.isNull(noun) || Objects.isNull(targetNoun)) {
            return "Attack who with what?";
        }
        int WEAPON_MULTIPLIER = 10;
        StringBuilder response = new StringBuilder();
        if (noun.isAttackable() & targetNoun.isWieldable()) {
            if (targetNoun instanceof Pie && noun instanceof Enemy) {
                Enemy enemy = (Enemy) targetNoun;
                Pie weapon = (Pie) noun;
                if (enemy.getHp() > 0) {
                    int newHP = enemy.getHp() - (weapon.getAttackPoints()*WEAPON_MULTIPLIER);
                    enemy.setHp(newHP);
                    response.append("You " + verb.getName() + enemy.getName() + " with " + targetNoun.getName() + "."
                            + enemy.getName() + " has " + enemy.getHp() +" hp");

                }
                if (enemy.getHp() < 0) {
                    return ((Pie) noun).getVictory();
                }
            } else {
                return "What are you doing sir? ";
            }
        }return response.toString();
    }

   }
