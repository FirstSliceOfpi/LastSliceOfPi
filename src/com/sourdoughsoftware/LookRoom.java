package com.sourdoughsoftware;

import java.util.List;

public class LookRoom {

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
}
