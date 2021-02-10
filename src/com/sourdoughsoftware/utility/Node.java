package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;

public class Node {
    Item item;
    Node left, right;
    int id;
    public Node(Item item) {
        this.item = item;
        left = null;
        right = null;
        id = 0;
    }

    public Node() {
        this.item = null;
        left = null;
        right = null;
        id = 0;
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

    @Override
    public String toString() {
       return Integer.toString(id);
    }
}
