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
        HashMap<String, Object> xmlWeapons = XmlParser.parsePies();
        XmlParser.parseVerbs();
        tree = (ItemTree) xmlWeapons.get("pieTree");
        pies = (ArrayList) xmlWeapons.get("findablePies");
        GameState.getInstance().setTree(tree);
    }

    @Test
    public void verifyTreeStructure() {
        assertEquals(31, tree.getSize());
        assertSame(tree.find(pies.get(0)).getId(), 16);
        assertEquals("Chess pie", tree.getRoot().getItem().getName());
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
        assertEquals("Buttermilk pie", butterMilkPie.getName());
        Pie meringue = (Pie) tree.find(pies.get(12)).getItem();
        Pie elderberryCurd = (Pie) tree.find(pies.get(13)).getItem();
        Pie chiffonPie = CombinePies.combine(meringue, elderberryCurd, tree);
        assertEquals("Chiffon pie", chiffonPie.getName());
        Pie creamPie = CombinePies.combine(chiffonPie, butterMilkPie, tree);
        assertEquals("Cream pie", creamPie.getName());
    }

    @Test
    public void wontCombineWeapons() {
        Pie elderberryCurd = (Pie) tree.find(pies.get(13)).getItem();
        Pie buttermilk = (Pie) tree.find(pies.get(14)).getItem();
        Pie parent = CombinePies.combine(buttermilk, elderberryCurd, tree);
        assertEquals("Buttermilk", parent.getName());
    }

    @Test
    public void textParserGetNounWithTwoWords() {
        String userInput = "Buttermilk";
        Command command = TextParser.parse(userInput);
        assertEquals(userInput, Command.getNoun().getName());
//        userInput = "Buttermilk pie";
//        command = TextParser.parse(userInput);
//        assertEquals(userInput, command.getNoun().getName());
        userInput = "merge Buttermilk and Eggs";
        command = TextParser.parse(userInput);
        assertEquals("Buttermilk", Command.getNoun().getName());
        assertEquals("Eggs", Command.getTargetNoun().getName());
        assertEquals("merge", Command.getVerb().getName());
    }

    @Test
    public void actionMerge() {
        String userInput = "merge Buttermilk and Eggs";
        Command command = TextParser.parse(userInput);
        assertEquals("Buttermilk", Command.getNoun().getName());
        assertEquals("Eggs", Command.getTargetNoun().getName());
        assertEquals("merge", Command.getVerb().getName());
        String actual = Actions.merge(command.getNoun(), command.getVerb(), command.getTargetNoun());
        String expected = "YOU " + "merge" + "d " + "Buttermilk"
                + " and " + "Eggs"
                + " to make a " + "Buttermilk pie";
        assertEquals(expected, actual);

    }
}