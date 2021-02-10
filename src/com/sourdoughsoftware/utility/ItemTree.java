package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemTree {
    private Node root;
    private int size = 0;

    public ItemTree() {
        root = null;
    }

    public void add(Item item) {
        if(root == null) {
            root = new Node(item);
            root.setId(++size);
            return;
        }
        insert(root, item, size+1);
    };

    private void insert(Node node, Item item, int value) {
        int id = node.getId();
        if (id < value) {
            if (node.left == null) {
                node.left = new Node(item);
                node.left.setId(++size);
                return;
            } else if (node.right == null) {
                node.right = new Node(item);
                node.right.setId(++size);
                return;
            } else {
                if(node.left.left == null || node.left.right == null) {
                    insert(node.left, item, value);
                }else{
                    insert(node.right, item, value);
                }
            }
        }
    }

    public Node find(Item item) {
        return DFSHelper(item, this.root, new Node());
    };

    private Node DFSHelper(Item item, Node node, Node result) {
        if(node != null){
            if(node.getItem() == item) return node;
            result = DFSHelper(item, node.left, result);
            if(result.getItem() == item) return result;
            result = DFSHelper(item, node.right, result);
        }
        return result;
    };

    public Node[] getAllItemsBFS() {
        List<Node> items = new ArrayList<>();
        int height = height(root);
        for(int i = 1; i <= height; i++) {
            ArrayList<Node> result = new ArrayList<>();
            items.addAll(getLevelItems(root, i, result));
        }
        Node[] nodes = items.toArray(Node[]::new);
        return nodes;
    };

    public ArrayList<Node> getLevelItems (Node root, int level, ArrayList<Node> result) {
        if (root == null) return result;
        if (level == 1) {
            result.add(root);
            return result;
        }
        else if (level > 1)
        {
            getLevelItems(root.left, level-1, result);
            getLevelItems(root.right, level-1, result);
        }
        return result;
    }

    public int height(Node root) {
        if (root == null) return 0;
        else {
            int lheight = height(root.left);
            int rheight = height(root.right);

            if (lheight > rheight) return(lheight+1);
            else return(rheight+1);
        }
    }

    public int getSize() {
      return size;
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        String result = "";
        Node[] allItemsInTree = getAllItemsBFS();
        for(Node node : allItemsInTree) {
            int id = node.getId();
            if(powerOfTwo(id)) {
                result += "\n";
            }
            result += node.toString() + " ";
        }
        return result;
    }

    private boolean powerOfTwo(int i) {
        for(int x = 0; x < 8 && (Math.pow(2,x) < size); x++) {
            if (Math.pow(2, x) == i) {
                return true;
            }
        }
        return false;
    }


}
