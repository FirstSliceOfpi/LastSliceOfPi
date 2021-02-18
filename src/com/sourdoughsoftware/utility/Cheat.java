package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.gamepieces.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

public class Cheat {
    String filePathAnimatedArt = "Elsa";
    private static Cheat cheat = null;

    private Cheat() {}

    public static Cheat getInstance() {
        cheat = cheat == null ? new Cheat() : cheat;
        return cheat;
    }
    
    public String addAllPiesToInventory() {
        Node[] allItems = GameState.getTree().getAllItemsBFS();
        for(Node node : allItems) {
            GameState.getPlayer().getInventory().add(node.getItem());
        }
        return "Your items have been delivered.";
    }

    public String doubleAttackPoints() {
        GameState.getFindableWeapons().forEach(pie -> pie.setAttackPoints(2*pie.getAttackPoints()));
        return "Powered Up!";
    }

    public String doubleAllAP() {
        Arrays.stream(GameState.getTree().getAllItemsBFS()).map(node->{
            Pie pie = (Pie) node.getItem();
            pie.setAttackPoints(2*pie.getAttackPoints());
            return null;
        });
        return "Super Powered!";
    }

    public String showCheatArt() throws InterruptedException, IOException {
            String[] art = new String[0];
            try {
                art = Files.readString(Path.of("resources", filePathAnimatedArt)).split(Pattern.quote("\\n"),-1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String color = Colors.randomColor();
            for(int i = 0; i < art.length; i++) {
                System.out.println(color + art[i]);
                Thread.sleep(30);
                //System.out.print("\033[H\033[2J");
                //System.out.flush();
                if(System.getProperty("os.name").toLowerCase().contains("mac")) {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }else {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
            }
            return "Elsa.";
    }

}
