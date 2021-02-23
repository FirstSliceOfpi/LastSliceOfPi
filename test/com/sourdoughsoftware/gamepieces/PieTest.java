package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.CombinePies;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.XmlParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PieTest {
    ItemTree tree;
    ArrayList<Pie> pies;
    @Before
    public void setUp() {
        GameState.getDevMode();
        HashMap<String, Object> xmlWeapons = XmlParser.parsePies();
        XmlParser.parseVerbs();
        tree = (ItemTree) xmlWeapons.get("pieTree");
        pies = (ArrayList) xmlWeapons.get("findablePies");
        GameState.setTree(tree);
        GameState.getPlayer().getInventory().add(tree.find("bananas").getItem());
        GameState.getPlayer().getInventory().add(tree.find("toffee").getItem());
    }

    @Test
    public void verifyTreeStructure() {
        assertEquals(31, tree.getSize());
        assertSame(tree.find(pies.get(0)).getId(), 16);
        assertEquals("chess pie", tree.getRoot().getItem().getName());
    }
    @Test
    public void getAttackPoints() {
        Pie pie = (Pie) tree.getRoot().getItem();
        assertSame(pie.getAttackPoints(), 5);
    }

    @Test
    public void getVictory() {
        Pie pie = (Pie) tree.getRoot().getItem();
        assertEquals("You played Queen's Gambit and your foe resigned.", pie.getVictory());
    }

    @Test
    public void combineWeapons() {
        Pie butterMilk = (Pie) tree.find(pies.get(14)).getItem();
        Pie eggs = (Pie) tree.find(pies.get(15)).getItem();
        Pie butterMilkPie = CombinePies.combine(butterMilk, eggs, tree);
        assertEquals("buttermilk pie", butterMilkPie.getName());
        Pie meringue = (Pie) tree.find(pies.get(12)).getItem();
        Pie elderberryCurd = (Pie) tree.find(pies.get(13)).getItem();
        Pie chiffonPie = CombinePies.combine(meringue, elderberryCurd, tree);
        assertEquals("chiffon pie", chiffonPie.getName());
        Pie creamPie = CombinePies.combine(chiffonPie, butterMilkPie, tree);
        assertEquals("cream pie", creamPie.getName());
    }

    @Test
    public void wontCombineWeapons() {
        Pie elderberryCurd = (Pie) tree.find(pies.get(13)).getItem();
        Pie buttermilk = (Pie) tree.find(pies.get(14)).getItem();
        Pie parent = CombinePies.combine(buttermilk, elderberryCurd, tree);
        assertEquals("buttermilk", parent.getName());
    }

    @Test
    public void textParserGetNounWithTwoWords() {
        String userInput = "examine buttermilk";
//        TextParser.parse(userInput);
//        assertEquals("buttermilk", Command.getNoun().getName());
//        TextParser.parse(userInput);
//        assertEquals(userInput,Command.getNoun().getName());
//        userInput = "Buttermilk pie";
//        command = TextParser.parse(userInput);
//        assertEquals(userInput, command.getNoun().getName());
        userInput = "merge bananas and toffee";
        TextParser.parse(userInput);
        assertEquals("bananas", Command.getNoun().getName());
        assertEquals("toffee", Command.getTargetNoun().getName());
        assertEquals("merge", Command.getVerb().getName());
    }

    @Test
    public void actionMerge() {
        String userInput = "merge bananas and toffee";
        TextParser.parse(userInput);
        assertEquals("bananas", Command.getNoun().getName());
        assertEquals("toffee", Command.getTargetNoun().getName());
        assertEquals("merge", Command.getVerb().getName());
        TextParser.parse(userInput);
        assertEquals("bananas", Command.getNoun().getName());
        assertEquals("toffee", Command.getTargetNoun().getName());
        assertEquals("merge",Command.getVerb().getName());
        String actual = Actions.merge(Command.getNoun(), Command.getVerb(), Command.getTargetNoun());
        String expected = "YOU " + "merge" + "d " + "bananas"
                + " and " + "toffee"
                + " to make a " + "banoffe pie";
        assertEquals(expected, actual);
    }
}