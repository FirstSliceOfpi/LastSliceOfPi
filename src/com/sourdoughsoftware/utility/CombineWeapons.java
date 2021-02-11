package com.sourdoughsoftware.utility;
/**
 * This class provides a static function for combining weapons in
 * a binary search tree.
 */

import com.sourdoughsoftware.gamepieces.Weapon;

public class CombineWeapons {
    // returns the original weapon if it can not be combined otherwise it returns
    // the higher level combined weapon
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
