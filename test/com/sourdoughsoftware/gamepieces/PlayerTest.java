package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.ChainOfEventException;
import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.XmlParser;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.world.World;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PlayerTest {
    String userInput;
    ItemTree tree;
    ArrayList<Pie> pies;


    @Before
    public void setUp() throws Exception {
        GameState.getDevMode();
        HashMap<String, Object> xmlWeapons = XmlParser.parsePies();
        XmlParser.parseVerbs();
        XmlParser.parseItems();
        tree = (ItemTree) xmlWeapons.get("pieTree");
        pies = (ArrayList) xmlWeapons.get("findablePies");
        GameState.setTree(tree);
        new World();
        GameState.getPlayer().getInventory().add(tree.find("bananas").getItem());

    }




    @Test
    public void playerCanAttackWithDifferentInputsPositive() throws ChainOfEventException {
//        Directions.Direction directionInput = Directions.west;
//        World.changeCurrentRoom(directionInput);

//        userInput = "attack scar with bananas"; //TODO: account for 'with' Noun and TargetNoun switch
//        userInput = "attack scar bananas";
        userInput = "attack bananas scar";
        TextParser.parse(userInput);
        assertEquals("bananas", Command.getNoun().getName());
        assertEquals("scar", Command.getTargetNoun().getName());
        assertEquals("attack", Command.getVerb().getName());

//        String actual = Actions.attack(Command.getNoun(), Command.getVerb(), Command.getTargetNoun());
//        String expected = "YOU " + "attack" + "scar " + "with"
//                + "bananas";
//        assertEquals(expected, actual);

    }






    @Test
    public void wieldWeaponFromInventoryPositive() {
        XmlParser.parseVerbs();
        XmlParser.parsePies();
//        XmlParser.parseItems();



        //Command command = new Command(weapon,);


//        player.inventory.getCurrentInventory();
    }
}