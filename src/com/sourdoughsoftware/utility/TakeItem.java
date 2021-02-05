package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import com.sourdoughsoftware.Player;
import com.sourdoughsoftware.Room;

import java.util.List;

public class TakeItem {
    public static String takeItem(Player p1, List<Room> rooms, Item item) {
        String message = "You  see a " + item.getName();
        for (Room r1 : rooms) {
            if (p1.getPlayerRoomID().equals(r1.getRoomID())) {
                List<Item> items = r1.getRoomItems();
                if (items.contains(item)) {
                    r1.removeItem(item);
                    p1.addInventory(item);
                    message = "You added a " + item.getName() + " to your inventory.";
                    System.out.println(r1.getRoomItems().toString());
                    System.out.println(p1.getInventory().toString());
                    break;
                }
            }
        }
        return message;
    }
}
