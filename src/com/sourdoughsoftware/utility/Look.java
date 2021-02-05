package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import com.sourdoughsoftware.Player;
import com.sourdoughsoftware.Room;

import java.util.List;

public class Look {

    /**
     * This method will allow the player to see a short description of adjoining rooms.
     * It takes in the following parameters and returns a string to Game.
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
                else {
                    switch(exit) {
                        case 0:
                            result = "This is start. Nothing here to see.";
                            break;
                        case 1:
                            result = "Is that an apple reflected in a mirror? Because you could use a little snack.";
                            break;
                        case 2:
                            result = "Do you wonder if that tiny crown is waterproof? Also, do you smell amphibians?";
                            break;
                        case 3:
                            result = "There is so much hair in this room. And no door in sight.";
                            break;
                        case 4:
                            result = "This room could be worth its weight in gold.";
                            break;
                        case 5:
                            result = "Are those keys to a pumpkin carriage?!?!";
                            break;
                        case 6:
                            result = "If you have a sweet tooth, you are going to love this room.";
                            break;
                        case 7:
                            result = "My, but what big ears those are!";
                            break;
                        case 8:
                            result = "Those might be the tiniest shoeprints you've ever seen.";
                            break;
                        case 9:
                            result = "This room looks perfect for a nice, looooooong nap.";
                            break;
                        case 10:
                            result = "Nothing much to see in here, but there is definitely a distinct FOWL smell.";
                            break;
                            }
                }
            }
        }
        return result;
    }

    /**
     * This method allows the player to look at various items in room.
     * It takes the following parameters and returns a string to Game.
     * @param playerRoomID
     * @param response
     * @param roomList
     * @param itemList
     * @return
     */
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