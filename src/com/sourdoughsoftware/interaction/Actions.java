package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.utility.CombinePies;
import com.sourdoughsoftware.utility.Node;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.world.World;

public class Actions {
    public static String execute(Command command) {

        if (command.getVerb() == null) {
            return "no verb in input";
        }
        if (command.getNoun() == null) {
            return "no noun in input";
        }

        VerbGroup group = command.getVerb().getGroup();
        if (group.equals(VerbGroup.MERGE) && command.getTargetNoun() == null) {
            return "You need two items to merge";
        }

        switch (group) {
            case GRAB:
                return grab(command.getNoun(), command.getVerb());
            case MOVE:
                return move(command.getNoun(), command.getVerb());
            case MERGE:
                return merge(command.getNoun(), command.getVerb(), command.getTargetNoun());
            case WIELD:
                return wield(command.getNoun(), command.getVerb());
            case ATTACK:
                return attack(command.getNoun(),command.getVerb(), command.getTargetNoun());
//            case ATTACK:
//                return
            case EXAMINE:
                return examine(command);
            default:
                break;
        }
        return "Bug FOUND";
    }

    private static String examine(Command command) {
        return command.getNoun().getDescription();
    }

    // merge or combine to weapons for a higher level weapon
    private static String merge(Noun noun, Verb verb, Noun targetNoun) {
        GameState gs = GameState.getInstance();
        if (!gs.getPlayer().getInventory().has(noun) || !gs.getPlayer().getInventory().has(noun)) {
            return "One or more items are not in your inventory.";
        }
        Node weapon1Node = gs.getTree().find(noun.getName());
        Node weapon2Node = gs.getTree().find(noun.getName());
        Pie pie1 = null;
        Pie pie2 = null;
        if(weapon1Node != null) {
            pie1 = (Pie) weapon1Node.getItem();
        }
        if(weapon2Node != null) {
            pie2 = (Pie) weapon2Node.getItem();
        }
        Pie combinedPie = CombinePies.combine(pie1, pie2, gs.getTree());
        if(combinedPie != pie1) {
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
        if(noun instanceof Directions.Direction) {
            return World.changeCurrentRoom((Directions.Direction) noun);
        }
        return "That's not a direction";
    }

    private static String grab(Noun noun, Verb verb) {
        if (noun.isGrabbale()) {
            return "YOU " + verb.getName() + " " + noun.getName();
        } else {
            return "You can't grab a " + noun.getName();
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
            if (targetNoun instanceof Weapon & noun instanceof Enemy) {
                Enemy enemy = (Enemy) noun;
                Weapon weapon = (Weapon) targetNoun;
                if (enemy.getHp() > 0) {
                    int newHP = enemy.getHp() - weapon.getAttackPoints();
                    enemy.setHp(newHP);
                    System.out.println("YOU " + verb.getName()+ enemy.getName() + " with" + targetNoun.getName());
                }
                if (enemy.getHp() < 0) {
                    return ((Weapon) noun).getVictory();
                }
            } else {
                return "What are you doing sir? ";
            }
        }return "hmmmm";
    }

   }
