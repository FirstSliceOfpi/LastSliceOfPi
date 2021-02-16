//package com.sourdoughsoftware.utility;
//
//import com.sourdoughsoftware.gamepieces.Item;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class ItemTreeTest {
//    ItemTree tree;
//    Item item1;
//    Item item2;
//    Item item3;
//    Item item4;
//    Item item5;
//    Item item6;
//    Item item7;
//    Item item8;
//    Item item9;
//    Item item10;
//
//    @Before
//    public void setUp() {
//        tree = new ItemTree();
//    }
//
//    public Item buildTree() {
//        item1 = new Item("1","1");
//        item2 = new Item("2", "2");
//        item3 = new Item("3", "3");
//        item4 = new Item("4","4");
//        item5 = new Item("5","5");
//        item6 = new Item("6","6");
//        item7 = new Item("7","7");
//        item8 = new Item("8","8");
//        item9 = new Item("9", "9");
//        item10 = new Item("10","10");
//        tree.add(item1);
//        tree.add(item2);
//        tree.add(item3);
//        tree.add(item4);
//        tree.add(item5);
//        tree.add(item6);
//        tree.add(item7);
//        tree.add(item8);
//        tree.add(item9);
//        tree.add(item10);
//        item10.getAction("light");
//        return item1;
//    }
//
//    @Test
//    public void addOneItemToEmptyTree() {
//        item1 = new Item("1","1");
//        tree.add(item1);
//        assertEquals(1, tree.getSize());
//    }
//
//    @Test
//    public void addThreeItemsToEmptyTree() {
//        item1 = new Item("1","1");
//        item2 = new Item("2","2");
//        item3 = new Item("3","3");
//        tree.add(item1);
//        tree.add(item2);
//        tree.add(item3);
//        System.out.println(tree);
//        assertEquals(3, tree.getSize());
//        assertSame(tree.getRoot().getItem(), item1);
//    }
//
//    @Test
//    public void addTenItemsToEmptyTree() {
//        Item item = buildTree();
//        assertEquals(10, tree.getSize());
//        assertSame(tree.getRoot().getItem(), item);
//        assertEquals(4, tree.height(tree.getRoot()));
//    }
//
//    @Test
//    public void findAnItemInTree() {
//        buildTree();
//        assertSame(tree.find(item1).getItem(), item1);
//        assertSame(tree.find(item2).getItem(), item2);
//        assertSame(tree.find(item3).getItem(), item3);
//        assertSame(tree.find(item4).getItem(), item4);
//        assertSame(tree.find(item5).getItem(), item5);
//        assertSame(tree.find(item6).getItem(), item6);
//        assertSame(tree.find(item7).getItem(), item7);
//    }
//
//    @Test
//    public void notFindAnItemInTree() {
//        buildTree();
//        Item item8 = new Item("8","8");
//        assertNotSame(tree.find(item8).getItem(), item8);
//    }
//
//    @Test
//    public void getAllItemsBFS() {
//        buildTree();
//        Node[] allItems = tree.getAllItemsBFS();
//        int id = 0;
//        for(Node node : allItems) {
//            assertEquals(node.getId(), ++id);
//        }
//    }
//
//    @Test
//    public void getLevelItems() {
//        buildTree();
//        ArrayList<Node> result = new ArrayList<>();
//        ArrayList<Node> nodes = tree.getLevelItems(tree.getRoot(),3,result);
//        assertEquals("[4, 5, 6, 7]", nodes.toString());
//    }
//
//    @Test
//    public void toStringTest() {
//        buildTree();
//        System.out.println(tree);
//        assertEquals("\n(1) \n(2) (3) \n(4) (5) (6) (7) \n(8) (9) (10) ", tree.toString());
//        }
//
//    @Test
//    public void getChildrenTest() {
//        buildTree();
//        Item[] result = new Item[]{item2, item3};
//        Item[] output = new Item[2];
//        Node[] nodes = tree.getChildren(item1);
//        output[0] = nodes[0].getItem();
//        output[1] = nodes[1].getItem();
//        assertArrayEquals(result, output);
//        result[0] = item4;
//        result[1] = item5;
//        nodes = tree.getChildren(item2);
//        output[0] = nodes[0].getItem();
//        output[1] = nodes[1].getItem();
//        assertArrayEquals(result, output);
//        result = new Item[]{item8, item9};
//        nodes = tree.getChildren(item4);
//        output[0] = nodes[0].getItem();
//        output[1] = nodes[1].getItem();
//        assertArrayEquals(result, output);
//    }
//
//    @Test
//    public void getParentAndSiblingTest() {
//        buildTree();
//        Item[] result = new Item[]{item1, item3};
//        Item[] output = new Item[2];
//        Node[] nodes = tree.getParentAndSibling(item2);
//        output[0] = nodes[0].getItem();
//        output[1] = nodes[1].getItem();
//        assertArrayEquals(result, output);
//        result[0] = item3;
//        result[1] = item7;
//        nodes = tree.getParentAndSibling(item6);
//        output[0] = nodes[0].getItem();
//        output[1] = nodes[1].getItem();
//        assertArrayEquals(result, output);
//    }
//}