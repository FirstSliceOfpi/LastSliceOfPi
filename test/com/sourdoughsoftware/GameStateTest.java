package com.sourdoughsoftware;

import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.gamepieces.CookBook;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.XmlParser;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.world.World;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GameStateTest {
    Game game;
    @Before
    public void setUp() throws Exception {
        GameState.getDevMode();
        game = new Game();

    }

    @Test
    public void addSavable() {
        Dictionary dict = Dictionary.INSTANCE;
        dict.saveClass();
        boolean saved = GameState.getSavable().get(0) == dict;
        assertTrue(saved);
    }

    @Test
    public void saveGame() throws ParserConfigurationException, SAXException, IOException {
        XmlParser.parseVerbs();
        XmlParser.parseItems();
        new Directions();
        new World();
        HashMap<String, Object> pies = XmlParser.parsePies();
        GameState.setTree((ItemTree) pies.get("pieTree"));
        GameState.setFindableWeapons((ArrayList<Pie>) pies.get("findablePies"));
        GameState.setCookBook(new CookBook());
        File file = new File("file");
        GameState.saveGame(file);
        assertTrue(Files.exists(Path.of("file")));
    }

    @Test
    public void loadGame() throws IOException, SAXException, ParserConfigurationException {
        GameState.setDevMode();
        assertTrue(GameState.getDevMode());
        saveGame();
        GameState.setDevMode();
        assertFalse(GameState.getDevMode());
        File file = new File("file");
        GameState.loadGame(file);
        assertTrue(GameState.getDevMode());
    }
}