package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.gamepieces.Item;

public class Node {
    private Item item;
    private Node left, right, parent;
    private int id;
    public Node(Item item) {
        this.item = item;
        left = null;
        right = null;
        id = 0;
        parent = null;
    }

    public Node() {
        this.item = null;
        left = null;
        right = null;
        id = 0;
        parent = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node node) {
        this.left = node;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node node) {
        this.right = node;
    }



    @Override
    public String toString() {
       return Integer.toString(id);
    }

    public String nodeDetails() {
        int left = 0;
        int right = 0;
        int parent = 0;
        if(getLeft() != null) {
            left = getLeft().getId();
        }
        if(getRight() != null) {
            right = getRight().getId();
        }
        if(getParent() != null) {
            parent = getParent().getId();
        }
        return "ID: " + Integer.toString(id)
                + " Left: " + Integer.toString(left)
                + " Right: " + Integer.toString(right)
                + " Parent: " + Integer.toString(parent);
    }
}
