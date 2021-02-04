package com.sourdoughsoftware;

import java.util.List;

public class LookRoom {

    /**
     * This method takes in the current room, player's response, room list and play
     * @param roomID
     * @param response
     * @param roomList
     * @param player1
     * @return
     */
    public static String roomLook(Integer roomID, String response, List<Room> roomList, Player player1) {
        String result = "";
        for (Room room : roomList) {
            if (room.getRoomID().equals(roomID)) {
                Integer exit = room.getExitByID(response);
                if (exit == null) {
                    result = "Nothing to see here.";
                    break;
                }
                player1.setPlayerRoomID(exit);
                result = room.getShortDescription();
                Room tempRoom = new Room();
                tempRoom = room.setRoomID(exit);
                System.out.println(tempRoom.getRoomID());

                result = tempRoom.getShortDescription();
            }
        }
        return result;
    }

}
