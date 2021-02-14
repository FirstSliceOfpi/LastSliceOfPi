package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.utility.Colors;
import com.sourdoughsoftware.utility.CombinePies;
import com.sourdoughsoftware.utility.Node;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.world.Room;
import com.sourdoughsoftware.world.World;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Actions {
    public static String execute(Command command) {
        if (Command.getVerb() == null) {
            return "no verb in input";
        }
        if (Command.getNoun() == null && !(Command.getVerb().getGroup() == VerbGroup.save
                || Command.getVerb().getGroup() == VerbGroup.load
                || Command.getVerb().getGroup() == VerbGroup.quit
                || Command.getVerb().getGroup() == VerbGroup.dev)) {
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
//            case ATTACK:
//                return
            case print:
                return print(Command.getNoun());
            default:
                Command.getNoun().getAction(Command.getVerb().getName());
        }
        return ".";
    }

    public static String destroyNoun(String message) {
        Dictionary.INSTANCE.deleteNoun(Command.getNoun());
        System.out.println(message);
        return message;
    }

    public static String createNoun(String name) {
        Noun noun = new Noun(name, "this is a noun");
        Dictionary.INSTANCE.add(noun);
        return name + " created";
    }

    public static String changeDescription(String newDescription) {
        Command.getNoun().setDescription(newDescription);
        return Command.getNoun().getDescription();
    }

    public static String dev() {
        GameState.getInstance().setDevMode();
        return GameState.getInstance().getDevMode()
                ? Colors.ANSI_BLUE + "Dev mode enabled" + Colors.ANSI_RESET
                : Colors.ANSI_YELLOW + "Dev mode disabled" + Colors.ANSI_RESET;
    }

    public static String quit() {
        String response = Prompter.prompt("Are you sure you want to exit?(Y/N)");
        String cleansedResponse = response.strip().toLowerCase();
        if (cleansedResponse.equals("y") || cleansedResponse.equals("yes")) {
            System.out.println("And they lived happily ever after. The end.");
            System.exit(0);
        }
        return "";
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
                "Your game -- " + Colors.ANSI_GREEN + fileToSave + Colors.ANSI_RESET + " -- was saved."
                : Colors.ANSI_RED + "Your game was not saved." + Colors.ANSI_RESET;
    }

    public static String load() {
        File dir = new File("./saved_games");
        for (String file : dir.list()) {
            System.out.println(Colors.ANSI_BLUE + file + Colors.ANSI_RESET);
        }
        String fileName = Prompter.prompt("What game would you like to load?");
        File fileToLoad = new File(dir, fileName);
        return GameState.loadGame(fileToLoad) ?
                "Your game -- " + Colors.ANSI_GREEN + fileToLoad + Colors.ANSI_RESET + " -- was loaded."
                : Colors.ANSI_RED + "Your game was not loaded." + Colors.ANSI_RESET;
    }

    private static String print(Noun noun) {
        StringBuilder result = new StringBuilder(noun.getDescription());
        result.append("\n");
        if (noun.getName() == "room") {
            result.append("You find ");
            for (Item item : World.getCurrentRoom().getRoomItems()) {
                result.append(" " + item.getName() + ",");
            }
            result.append(" in the room.");
        }
        return result.toString();
    }

    // merge or combine to weapons for a higher level weapon
    public static String merge(Noun noun, Verb verb, Noun targetNoun) {
        VerbGroup group = verb.getGroup();

        if (group.equals(VerbGroup.merge) && targetNoun == null) {
            return "You need two items to merge";
        }

        GameState gs = GameState.getInstance();
        if (!gs.getDevMode()) {
            if (!Player.getPlayer().getInventory().has(noun) || !Player.getPlayer().getInventory().has(noun)) {
                return "One or more items are not in your inventory.";
            }
        }
        Node weapon1Node = gs.getTree().find(noun.getName());
        Node weapon2Node = gs.getTree().find(targetNoun.getName());
        Pie pie1 = null;
        Pie pie2 = null;
        if (weapon1Node != null) {
            pie1 = (Pie) weapon1Node.getItem();
        }
        if (weapon2Node != null) {
            pie2 = (Pie) weapon2Node.getItem();
        }
        Pie combinedPie = CombinePies.combine(pie1, pie2, gs.getTree());
        if (combinedPie != pie1) {
            Player.getPlayer().getInventory().drop(noun);
            Player.getPlayer().getInventory().drop(targetNoun);
            Player.getPlayer().getInventory().add(combinedPie);
            return "YOU " + verb.getName() + "d " + noun.getName()
                    + " and " + targetNoun.getName()
                    + " to make a " + combinedPie.getName();
        } else {
            return "you can't merge a " + noun.getName() + " and a " + targetNoun.getName() + " together";
        }
    }

    private static String move(Noun noun) {
        if (noun instanceof Directions.Direction) {
            return World.changeCurrentRoom((Directions.Direction) noun);
        }
        return "That's not a direction";
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void dropFromRoom(String message) {
        Room currentRoom = World.getCurrentRoom();
        Noun noun = Command.getNoun();
        currentRoom.dropItem(noun);
    }

    public static void addToRoom(String message) {
        Room currentRoom = World.getCurrentRoom();
        Noun noun = Command.getNoun();
        currentRoom.addItem(noun);
    }

    public static void dropFromInventory(String message) {
        Player player = Player.getPlayer();
        Noun noun = Command.getNoun();
        player.getInventory().drop(noun);
    }

    public static String addToInventory(String str) {
        Player player = Player.getPlayer();
        Noun noun = Command.getNoun();
        player.getInventory().add(noun);
        player.getInventory().getCurrentInventory().forEach(noun1 -> System.out.println(noun1.getName()));
        return Objects.requireNonNullElseGet(str, () -> "You grabbed " + noun.getName());
    }



//    private static String attack(Noun noun,  Enemy enemy) {
//        if (noun.isAttackable() & enemy.getHp() > 0) {
//            return "YOU " + noun.getName() + enemy.getName();
//            int newHP = enemy.getHp() - weapon.getAP();
//            enemy.setHp(newHP);
//        }else (noun.isAttackable() & enemy.getHp() < 0) {
//            return "Cannot attack " + enemy.getName() + ", they are dead ";
//        }
//    }
}
