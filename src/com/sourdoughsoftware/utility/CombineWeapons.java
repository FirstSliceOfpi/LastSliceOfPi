package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.gamepieces.Weapon;

public class CombineWeapons {
    public static Weapon combine(Weapon weapon1, Weapon weapon2, ItemTree tree) {
        Node[] parentAndSibling = tree.getParentAndSibling(weapon1);
        Weapon sibling;
        if(parentAndSibling[1] != null) {
            sibling = (Weapon) parentAndSibling[1].getItem();
            if (sibling == weapon2) {
                return (Weapon) parentAndSibling[0].getItem();
            }
        }
        return weapon1;
    }
}
