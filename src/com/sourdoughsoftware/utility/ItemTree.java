package com.sourdoughsoftware.utility;
/**
 *  This class is the BST structure for the weapons
 *  It contains many functions for adding and finding weapons
 */

import com.sourdoughsoftware.gamepieces.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemTree {
    private Node root;
    private int size = 0;

    public ItemTree() {
        root = null;
    }
    // add a weapon to the tree
    public void add(Item item) {
        if (root == null) {
            root = new Node(item);
            root.setId(++size);
            return;
        }
        Node newNode = new Node(item);
        newNode.setId(++size);
        insert(root, newNode);
    }
    // helper function for add()
    private Node insert(Node lastNode, Node newNode) {
        int lastValue = lastNode.getId();
        Node left = lastNode.getLeft();
        Node right = lastNode.getRight();
        if(left == null) {
            lastNode.setLeft(newNode);
            lastNode.getLeft().setParent(lastNode);
        }else if (right == null) {
            lastNode.setRight(newNode);
            lastNode.getRight().setParent(lastNode);
        } else {
            insert(getNextNode(lastValue), newNode);
        }
        return newNode;
    }
    // helper function for insert()
    private Node getNextNode(int lastValue) {
        return find(++lastValue);
    }

    // Weapons can be found by Item object, String name, Node id as an integer
    public Node find(Item item) {
        return DFSHelper(item, this.root, new Node());
    }

    public Node find(String item) {
        return DFSHelper(item, this.root, new Node());
    }

    public Node find(int item) {
        return DFSHelper(item, this.root, new Node());
    }
    // Overloaded DFSHelpers for find functions
    private Node DFSHelper(int item, Node node, Node result) {
        if (node != null) {
            if (node.getId() == item) return node;
            result = DFSHelper(item, node.getLeft(), result);
            if (result != null) {
                if (result.getId() == item) return result;
            }
            result = DFSHelper(item, node.getRight(), result);
        }
        return result;
    }

    private Node DFSHelper(String item, Node node, Node result) {
        if (node != null) {
            if (node.getItem().getName().equals(item)) return node;
            result = DFSHelper(item, node.getLeft(), result);
            if (result != null) {
                Item resultItem = result.getItem();
                if (resultItem != null) {
                    if (resultItem.getName().equals(item)) return result;
                }
            }
            result = DFSHelper(item, node.getRight(), result);
        }
        return result;
    }

    private Node DFSHelper(Item item, Node node, Node result) {
        if (node != null) {
            if (node.getItem() == item) return node;
            result = DFSHelper(item, node.getLeft(), result);
            if (result != null) {
                if (result.getItem() == item) return result;
            }
            result = DFSHelper(item, node.getRight(), result);
        }
        return result;
    }

    // Performs a bread first search and returns all the Nodes(weapons) in an array
    public Node[] getAllItemsBFS() {
        List<Node> items = new ArrayList<>();
        int height = height(root);
        for (int i = 1; i <= height; i++) {
            ArrayList<Node> result = new ArrayList<>();
            items.addAll(getLevelItems(root, i, result));
        }
        return items.toArray(Node[]::new);
    }

    // Helper function for getAllItems but also publicly accessible
    // to find weapons of a certain level
    public ArrayList<Node> getLevelItems(Node root, int level, ArrayList<Node> result) {
        if (root == null) return result;
        if (level == 1) {
            result.add(root);
            return result;
        } else if (level > 1) {
            getLevelItems(root.getLeft(), level - 1, result);
            getLevelItems(root.getRight(), level - 1, result);
        }
        return result;
    }

    // returns the height of the tree from the node passed in (typically the root)
    public int height(Node root) {
        if (root == null) return 0;
        else {
            int lheight = height(root.getLeft());
            int rheight = height(root.getRight());

            if (lheight > rheight) return (lheight + 1);
            else return (rheight + 1);
        }
    }

    // Size is used to set the Node id and is also the current
    // size of the tree
    public int getSize() {
        return size;
    }

    public Node getRoot() {
        return root;
    }

    // getChildren is for determining what two items
    // combine to make the parent item
    public Node[] getChildren(Item item) {
        Node parent = find(item);
        return new Node[]{parent.getLeft(), parent.getRight()};
    }

    // getParentAndSibling is for determining if you
    // have one item will it combine to make another item
    // if so then what other item
    public Node[] getParentAndSibling(Item item) {
        Node firstChild = find(item);
        if(firstChild == root) { return new Node[]{firstChild}; }

        Node parent = firstChild.getParent();
        if (parent.getLeft() != firstChild) {
            return new Node[]{parent, parent.getLeft()};
        } else if (parent.getRight() != firstChild) {
            return new Node[]{parent, parent.getRight()};
        }
        return new Node[]{parent};
    }

    // A version of toString that shows more details for development
    public String treeNodeDetails() {
        StringBuilder result = new StringBuilder();
        Node[] allItemsInTree = getAllItemsBFS();
        for (Node node : allItemsInTree) {
            int id = node.getId();
            if (powerOfTwo(id)) {
                result.append("\n");
            }
            result.append("(").append(node.nodeDetails()).append(") ");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node[] allItemsInTree = getAllItemsBFS();
        for (Node node : allItemsInTree) {
            int id = node.getId();
            if (powerOfTwo(id)) {
                result.append("\n");
            }
            result.append("(").append(node.getItem().toString()).append(") ");
        }
        return result.toString();
    }

    // Helps format the binary tree output for visual display on the console
    private boolean powerOfTwo(int i) {
        for (int x = 0; x < 8 && (Math.pow(2, x) < size); x++) {
            if (Math.pow(2, x) == i) {
                return true;
            }
        }
        return false;
    }
}
