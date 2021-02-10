package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTreeTest {
    ItemTree tree;
    Item item1;
    Item item2;
    Item item3;
    Item item4;
    Item item5;
    Item item6;
    Item item7;

    @Before
    public void setUp() throws Exception {
        tree = new ItemTree();
    }

    public Item buildTree() {
        item1 = new Item();
        item2 = new Item();
        item3 = new Item();
        item4 = new Item();
        item5 = new Item();
        item6 = new Item();
        item7 = new Item();
        item1.setName("1");
        item2.setName("2");
        item3.setName("3");
        item4.setName("4");
        item5.setName("5");
        item6.setName("6");
        item7.setName("7");
        tree.add(item1);
        tree.add(item2);
        tree.add(item3);
        tree.add(item4);
        tree.add(item5);
        tree.add(item6);
        tree.add(item7);
        return item1;
    }

    @Test
    public void addOneItemToEmptyTree() {
        Item item = new Item();
        item.setName("1");
        tree.add(item);
        assertTrue(tree.getSize() == 1);
    }

    @Test
    public void addThreeItemsToEmptyTree() {
        item1 = new Item();
        item2 = new Item();
        item3 = new Item();
        item1.setName("1");
        item2.setName("2");
        item3.setName("3");
        tree.add(item1);
        tree.add(item2);
        tree.add(item3);
        assertTrue(tree.getSize() == 3);
        assertTrue(tree.getRoot().item == item1);
    }

    @Test
    public void addSevenItemsToEmptyTree() {
        Item item = buildTree();
        assertTrue(tree.getSize() == 7);
        assertTrue(tree.getRoot().item == item);
        assertTrue(tree.height(tree.getRoot()) == 3);
    }

    @Test
    public void findAnItemInTree() {
        buildTree();
        assertTrue(tree.find(item1).getItem() == item1);
        assertTrue(tree.find(item2).getItem() == item2);
        assertTrue(tree.find(item3).getItem() == item3);
        assertTrue(tree.find(item4).getItem() == item4);
        assertTrue(tree.find(item5).getItem() == item5);
        assertTrue(tree.find(item6).getItem() == item6);
        assertTrue(tree.find(item7).getItem() == item7);
    }

    @Test
    public void getAllItemsBFS() {
        buildTree();
        Node[] allItems = tree.getAllItemsBFS();
        int id = 0;
        for(Node node : allItems) {
            assertTrue(node.getId() == ++id);
        }
    }

    @Test
    public void getLevelItems() {
        buildTree();
        ArrayList<Node> result = new ArrayList<>();
        ArrayList<Node> nodes = tree.getLevelItems(tree.getRoot(),3,result);
        assertTrue(nodes.toString().equals("[4, 5, 6, 7]"));
    }

    @Test
    public void toStringTest() {
        buildTree();
        System.out.println(tree);
        assertEquals("\n1 \n2 3 \n4 5 6 7 ", tree.toString());
    }

}