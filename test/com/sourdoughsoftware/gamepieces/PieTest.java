package com.sourdoughsoftware.gamepieces;

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
    public void setUp() throws Exception {
        HashMap<String, Object> xmlWeapons = XmlParser.parsePies();
        tree = (ItemTree) xmlWeapons.get("weaponTree");
        pies = (ArrayList) xmlWeapons.get("findableWeapons");
    }

    @Test
    public void verifyTreeStructure() {
        assertEquals(31, tree.getSize());
        assertSame(tree.find(pies.get(0)).getId(), 16);
        assertEquals("ham sandwich", tree.getRoot().getItem().getName());
    }
    @Test
    public void getAttackPoints() {
        Pie pie = (Pie) tree.getRoot().getItem();
        assertSame(pie.getAttackPoints(), 5);
    }

    @Test
    public void getVictory() {
        Pie pie = (Pie) tree.getRoot().getItem();
        assertEquals("The smell of Grandma's ham sandwich causes your attacker to give up and join you for dinner.", pie.getVictory());
    }

    @Test
    public void combineWeapons() {
        Pie beadedbracelet = (Pie) tree.find(pies.get(14)).getItem();
        Pie wetnoodle = (Pie) tree.find(pies.get(15)).getItem();
        Pie bbgun = CombinePies.combine(beadedbracelet, wetnoodle, tree);
        assertEquals("bb gun", bbgun.getName());
        Pie rustynail = (Pie) tree.find(pies.get(12)).getItem();
        Pie jarofjam = (Pie) tree.find(pies.get(13)).getItem();
        Pie clawhammer = CombinePies.combine(rustynail, jarofjam, tree);
        assertEquals("claw hammer", clawhammer.getName());
        Pie crossbow = CombinePies.combine(bbgun, clawhammer, tree);
        assertEquals("crossbow", crossbow.getName());
    }

    @Test
    public void wontCombineWeapons() {
        Pie jarofjam = (Pie) tree.find(pies.get(13)).getItem();
        Pie beadedbracelet = (Pie) tree.find(pies.get(14)).getItem();
        Pie parent = CombinePies.combine(jarofjam, beadedbracelet, tree);
        assertEquals("jar of jam", jarofjam.getName());
    }
}