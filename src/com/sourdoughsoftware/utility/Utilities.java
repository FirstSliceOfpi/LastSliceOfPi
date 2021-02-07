package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import com.sourdoughsoftware.Room;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static String getRoomDescription(Integer playerLocation, List<Room> rooms) {
        String result = "";
        for (Room room : rooms) {
            if (playerLocation.equals(room.getRoomID())) {
                if (room.getDescription() == null) {
                    room.setDescription("Room " + room.getRoomID() + " is missing a description!");
                    result = room.getDescription();
                } else {
                    result = room.getDescription();
                }
            }
        }
        return result;
    }
    public static List<Item> getPlayerRoomItems(Integer playerRoomID, List<Room> rooms) {
        List<Item> specificItems = new ArrayList<>();
        for (Room room : rooms) {
            if (playerRoomID.equals(room.getRoomID())) {
                specificItems = room.getRoomItems();
            }
        }
        return specificItems;
    }

}
