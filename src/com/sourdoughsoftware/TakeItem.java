package com.sourdoughsoftware;

import java.util.List;

public class TakeItem {
    public static void takeItem(Player p1, List<Room> rooms, Item item) {
        for (Room r1 : rooms) {
            if (p1.getPlayerRoomID().equals(r1.getRoomID())) {
                List<Item> items = r1.getRoomItems();
                if (items.contains(item)) {
                    r1.removeItem(item);
                    p1.addInventory(item);
                    System.out.println("You added a " + item.getName() + " to your inventory.");
                    System.out.println(r1.getRoomItems().toString());
                    System.out.println(p1.getInventory().toString());
                }
                else {
                    System.out.println("You don't see a " + item.getName());
                }
                break;
            }
        }
    }
}
