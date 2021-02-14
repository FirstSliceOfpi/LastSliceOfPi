package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.utility.Cheat;
import com.sourdoughsoftware.utility.CombinePies;
import com.sourdoughsoftware.utility.Node;
import com.sourdoughsoftware.utility.PrintFiles;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.world.World;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.sourdoughsoftware.utility.Colors.*;

public class Actions {
    private static GameState gs = GameState.getInstance();
    private static Command command = gs.getCommand();

    public static String execute() {

        if (command.getVerb() == null) {
            return "no verb in input";
        }
        if (command.getNoun() == null
                && !(command.getVerb().getGroup() == VerbGroup.SAVE
                || command.getVerb().getGroup() == VerbGroup.LOAD
                || command.getVerb().getGroup() == VerbGroup.QUIT
                || command.getVerb().getGroup() == VerbGroup.DEV
        )
        ) {
            return "no noun in input";
        }

        VerbGroup group = command.getVerb().getGroup();
        if (group.equals(VerbGroup.MERGE) && command.getTargetNoun() == null) {
            return "You need two items to merge";
        }

        switch (group) {
            case GRAB:
                return grab(command.getNoun());
            case MOVE:
                return move(command.getNoun(), command.getVerb());
            case MERGE:
                return merge(command.getNoun(), command.getVerb(), command.getTargetNoun());
            case SAVE:
                return save();
            case LOAD:
                return load();
            case QUIT:
                return quit();
            case DEV:
                return dev();
            case WIELD:
                return wield(command.getNoun(), command.getVerb());
            case ATTACK:
                return attack(command.getNoun(),command.getVerb(), command.getTargetNoun());
            case EXAMINE:
                return examine(command.getNoun());
            case SHOW:
                return show();
            default:
                command.getNoun().getAction(command.getVerb().getName());
                return "";
        }
    }

    public static String dev() {
        gs.setDevMode();
        return gs.getDevMode()
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
        return gs.saveGame(fileToSave) ?
                "Your game -- " + ANSI_GREEN + fileToSave + ANSI_RESET + " -- was saved."
                : ANSI_RED + "Your game was not saved." + ANSI_RESET;
    }

    public static String load() {
        File dir = new File("./saved_games");
        for (String file : dir.list()) {
            System.out.println(ANSI_BLUE + file + ANSI_RESET);
        }
        String fileName = Prompter.prompt("What game would you like to load?");
        File fileToLoad = new File(dir, fileName);
        return gs.loadGame(fileToLoad) ?
                "Your game -- " + ANSI_GREEN + fileToLoad + ANSI_RESET + " -- was loaded."
                : ANSI_RED + "Your game was not loaded." + ANSI_RESET;
    }

    private static String examine(Noun noun) {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        if (noun.getName().equals("room")) {
            if(gs.getRoom().getRoomItems().size() == 0) {
                return "You find nothing in the room.";
            }
            result.append("You find");
            for (Item item : gs.getRoom().getRoomItems()) {
                result.append(" " + item.getName() + ",");
            }
            result.append(" in the room.");
        } else {
            result.append(noun.getDescription());
        }
        return result.toString();
    }

    // merge or combine to weapons for a higher level weapon
    public static String merge(Noun noun, Verb verb, Noun targetNoun) {
        if (!gs.getDevMode()) {
            if (!gs.getPlayer().getInventory().has(noun) || !gs.getPlayer().getInventory().has(noun)) {
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
            gs.getPlayer().getInventory().drop(noun);
            gs.getPlayer().getInventory().drop(targetNoun);
            gs.getPlayer().getInventory().add(combinedPie);
            return "YOU " + verb.getName() + "d " + noun.getName()
                    + " and " + targetNoun.getName()
                    + " to make a " + combinedPie.getName();
        } else {
            return "you can't merge a " + noun.getName() + " and a " + targetNoun.getName() + " together";
        }
    }

    private static String move(Noun noun, Verb verb) {
        // Easter Egg
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

    private static String grab(Noun noun) {
        if(!noun.isFindable()) { return "You can not pick up " + noun.getName(); }
        String success = noun.getName() + " not found in the room.";
        if(gs.getRoom().removeItem((Item) noun)) {
            success = gs.getPlayer().getInventory().add(noun);
        }
        return success;
    }

    public static String show() {
        StringBuilder builder = new StringBuilder();
        List<Noun> inventory = gs.getInventory();
        for(Noun noun : inventory) {
            builder.append(noun.getName() + "\n");
        }
        return builder.toString();
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void changeDescription(String str) {
        Noun noun = command.getNoun();
        noun.setDescription(str);
    }

    public static void cheat(String cheat) {
        if(cheat.equals("amazon")) {
            Cheat.getInstance().addAllPiesToInventory();
        }else if(cheat.equals("tra")) {
            try {
                Cheat.getInstance().showCheatArt();
            }catch(Exception e) {
                if(gs.getDevMode()) System.out.println(e);
            }
        }
    }


    private static String wield(Noun noun, Verb verb) {
        if (noun.isWieldable()) {
            return "YOU now "+ verb.getName() + " " + noun.getName() + noun.getDescription();
        } else {
            return noun.getName() + " is not a weapon";
        }
    }

    private static String attack(Noun noun, Verb verb, Noun targetNoun) {
        if (noun.isAttackable() & targetNoun.isWieldable()) {
            if (targetNoun instanceof Pie & noun instanceof Enemy) {
                Enemy enemy = (Enemy) noun;
                Pie weapon = (Pie) targetNoun;
                if (enemy.getHp() > 0) {
                    int newHP = enemy.getHp() - weapon.getAttackPoints();
                    enemy.setHp(newHP);
                    System.out.println("YOU " + verb.getName()+ enemy.getName() + " with" + targetNoun.getName());
                }
                if (enemy.getHp() < 0) {
                    return ((Pie) noun).getVictory();
                }
            } else {
                return "What are you doing sir? ";
            }
        }return "hmmmm";
    }

    public static void setGs(GameState GS) {
        gs = GS;
    }

   }
