package com.sourdoughsoftware;

import com.sourdoughsoftware.gamepieces.Pie;
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

public class Game {
    private ItemTree tree = new ItemTree();
    private ArrayList<Pie> findableWeapons = new ArrayList<Pie>();

    public Game() throws IOException, SAXException, ParserConfigurationException {
        XmlParser.parseItems();
        XmlParser.parseVerbs();
        XmlParser.parseEnemy();
        XmlParser.parseNouns();
        new Directions();
        new World();
        HashMap<String, Object> pies = XmlParser.parsePies();
        GameState.setTree((ItemTree) pies.get("pieTree"));
        GameState.setFindableWeapons((ArrayList<Pie>) pies.get("findablePies"));
    }

    public void start() {
        boolean gameOver = false;
        while(!gameOver) {
            TextParser.parse(Prompter.prompt("What do you want to do?"));
            System.out.println(Actions.execute());
        }
    }

}

