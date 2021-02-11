package com.sourdoughsoftware;

import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.gamepieces.Weapon;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Prompter;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.XmlParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    GameState gs = GameState.getInstance();

    public Game() throws IOException, SAXException, ParserConfigurationException {
        XmlParser.parseItems();
        XmlParser.parseVerbs();
        XmlParser.parseEnemy();
        HashMap<String, Object> weapons = XmlParser.parseWeapons();
        gs.setTree((ItemTree) weapons.get("weaponsTree"));
        gs.setFindableWeapons((ArrayList) weapons.get("findableWeapons"));
    }

    public void start() {
        boolean gameOver = false;
        while(!gameOver) {
            System.out.println(Actions.execute(TextParser.parse(prompter.prompt("What do you want to do?"))));
        }
    }

}

