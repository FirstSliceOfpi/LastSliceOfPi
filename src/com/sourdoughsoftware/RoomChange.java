package com.sourdoughsoftware;

import java.util.List;

public class RoomChange {

    public static String changeRoom(Integer roomID, String response, List<Room> roomList, Player p1) {
        String result = "";
        switch (response) {
            case "west":
            case "southeast":
            case "southwest":
            case "northeast":
            case "east":
            case "south":
            case "northwest":
            case "north":
                result = changePlayerLocation(roomID, response, roomList, p1);
                break;
            default:

        }
        return result;
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
                System.out.println(room.getDescription());
                player1.setPlayerRoomID(exit);
                result = "Player in: " + player1.getPlayerRoomID();
                break;
            }
        }
        return result;
    }
}