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
    }

    private void insert(Node node, Item item, int value) {
        int id = node.getId();
        if (id < value) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(item));
                node.getLeft().setId(++size);
                node.getLeft().setParent(node);
            } else if (node.getRight() == null) {
                node.setRight(new Node(item));
                node.getRight().setId(++size);
                node.getRight().setParent(node);
            } else {
                if(node.getLeft().getLeft() == null || node.getLeft().getRight() == null) {
                    insert(node.getLeft(), item, value);
                }else{
                    insert(node.getRight(), item, value);
                }
            }
        }
    }

    public Node find(Item item) {
        return DFSHelper(item, this.root, new Node());
    }

    private Node DFSHelper(Item item, Node node, Node result) {
        if(node != null){
            if(node.getItem() == item) return node;
            result = DFSHelper(item, node.getLeft(), result);
            if(result.getItem() == item) return result;
            result = DFSHelper(item, node.getRight(), result);
        }
        return result;
    }

    public Node[] getAllItemsBFS() {
        List<Node> items = new ArrayList<>();
        int height = height(root);
        for(int i = 1; i <= height; i++) {
            ArrayList<Node> result = new ArrayList<>();
            items.addAll(getLevelItems(root, i, result));
        }
        return items.toArray(Node[]::new);
    }

    public ArrayList<Node> getLevelItems (Node root, int level, ArrayList<Node> result) {
        if (root == null) return result;
        if (level == 1) {
            result.add(root);
            return result;
        }
        else if (level > 1)
        {
            getLevelItems(root.getLeft(), level-1, result);
            getLevelItems(root.getRight(), level-1, result);
        }
        return result;
    }

    public int height(Node root) {
        if (root == null) return 0;
        else {
            int lheight = height(root.getLeft());
            int rheight = height(root.getRight());

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

    public Node[] getChildren(Item item) {
        Node parent = find(item);
        return new Node[]{parent.getLeft(), parent.getRight()};
    }

    public Node[] getParentAndSibling(Item item) {
        Node firstChild = find(item);
        Node parent = firstChild.getParent();
        if(parent.getLeft() != firstChild) {
            return new Node[]{parent, parent.getLeft()};
        }else if(parent.getRight() != firstChild) {
            return new Node[]{parent, parent.getRight()};
        }
        return new Node[]{parent};
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node[] allItemsInTree = getAllItemsBFS();
        for(Node node : allItemsInTree) {
            int id = node.getId();
            if(powerOfTwo(id)) {
                result.append("\n");
            }
            result.append(node.toString()).append(" ");
        }
        return result.toString();
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
