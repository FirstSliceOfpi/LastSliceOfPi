package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Player;
import com.sourdoughsoftware.Room;

import java.util.List;

public class RoomChange {

    public static String changeRoom(Integer roomID, String response, List<Room> roomList, Player p1) {
        return changePlayerLocation(roomID, response, roomList, p1);
    }


    private static String changePlayerLocation(Integer roomID, String response, List<Room> roomList, Player player1) {
        String result = "";
        for (Room room : roomList) {
            if (room.getRoomID().equals(roomID)) {
                Integer exit = room.getExitByID(response);
                if (exit == null) {
                    result = "You cannot go that way";
                    break;
                }
                player1.setPlayerRoomID(exit);
                result = "You are in room: " + player1.getPlayerRoomID();
                break;
            }
        }
        return result;
    }
}
