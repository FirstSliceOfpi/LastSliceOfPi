package com.sourdoughsoftware;

import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Prompter;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.XmlParser;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.world.World;
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
        new Directions();
        new World();
        HashMap<String, Object> pies = XmlParser.parsePies();
        ItemTree tree = (ItemTree) pies.get("pieTree");
        gs.setTree(tree);
        gs.setFindableWeapons((ArrayList) pies.get("findablePies"));
    }

    public void start() {
        boolean gameOver = false;
        while(!gameOver) {
            System.out.println(Actions.execute(TextParser.parse(prompter.prompt("What do you want to do?"))));
        }
    }

}

