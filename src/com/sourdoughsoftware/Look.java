package com.sourdoughsoftware;

import java.util.List;

public class Look {

    public static String roomLook(Integer roomID, String response, List<Room> roomList, Player player1) {
        String result = "";
        for (Room room : roomList) {
            if (room.getRoomID().equals(roomID)) {
                Integer exit = room.getExitByID(response);
                if (exit == null) {
                    result = "Nothing to see here.";
                    break;
                }
                result = room.getShortDescription();
            }
        }
        return result;
    }

    public static String itemLook(Integer playerRoomID, String response, List<Room> roomList, List<Item> itemList) {
        String result = "What are you looking at?";
        // This broken nested loop should be refactored into a non nested loop
        outer:
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(response)) {
                for (Room room : roomList) {
                    if (playerRoomID.equals(room.getRoomID()) && room.getRoomItems().contains(item)) {
                        result = item.getDescription();
                        break outer;
                    }
                }
            }

        }
        return result;
    }
}