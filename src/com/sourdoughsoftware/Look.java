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
                player1.setPlayerRoomID(exit);  //temporarily put player in room to get looked at
                System.out.println(player1.getPlayerRoomID()); //check room ID (which is right)
                System.out.println(room.getDescription()); //check description, still old room
                for (Room peekRoom : roomList) {
                    if ((player1.getPlayerRoomID()).equals(peekRoom.getRoomID())) {
                        System.out.println(player1.getPlayerRoomID());
                       result = room.getShortDescription();
                    }
                player1.setPlayerRoomID(roomID);
                }
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
