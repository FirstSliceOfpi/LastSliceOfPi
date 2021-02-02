package com.sourdoughsoftware;

import java.util.List;

public class RoomChange {

    public static void changeRoom(Integer id, String response, List<Room> roomList, Player p1) {

        switch (response) {
            case "west":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "north":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "south":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "east":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "northwest":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "northeast":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "southwest":
                changePlayerLocation(id, response, roomList, p1);
                break;
            case "southeast":
                changePlayerLocation(id, response, roomList, p1);
                break;

            default:

        }


    }


    private static void changePlayerLocation(Integer id, String response, List<Room> roomList, Player player1) {

        for (Room room : roomList) {
            if (room.getId().equals(id)) {
                Integer exit = room.getExitByID(response);
                if (exit == null) {
                    System.out.println("You cannot go that way");
                    break;
                }
                System.out.println(room.getDescription());
                player1.setLocationId(exit);
                System.out.println("Player in: " + player1.getLocation());
                break;
            }
        }
    }
}