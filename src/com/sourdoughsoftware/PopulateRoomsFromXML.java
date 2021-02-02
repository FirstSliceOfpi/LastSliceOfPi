package com.sourdoughsoftware;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds game rooms from XML file
 */
public class PopulateRoomsFromXML {

    public static List<Room> parseRoomXML() throws ParserConfigurationException, IOException, SAXException {
        List<Room> rooms = new ArrayList<>();
        Room room = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/Users/tyronemoore/cs5044-workspace/LastSliceOfPi/resources/Rooms.xml"));
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("room");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element individualRoom = (Element) node;
                //Create new Room Object
                room = new Room();
                room.setId(Integer.parseInt(individualRoom.getAttribute("id")));
                room.setName(individualRoom.getElementsByTagName("roomName").item(0).getTextContent());
//                System.out.println(individualRoom.getElementsByTagName("description").item(0).getTextContent());
                room.setDescription(individualRoom.getElementsByTagName("roomDisplay").item(0).getTextContent());
//                if (individualRoom.getElementsByTagName("items").getLength() != 0) {
//                    System.out.println("Room items" + individualRoom.getElementsByTagName("items").item(0).getTextContent());
//                    String item = individualRoom.getElementsByTagName("items").item(0).getTextContent();
//                    room.addToRoom(item);
//                }
                if (isExit(individualRoom, "exitWest")) {
                    room.addExitbyID("west", Integer.parseInt(individualRoom.getElementsByTagName("exitWest").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitEast")) {
                    room.addExitbyID("east", Integer.parseInt(individualRoom.getElementsByTagName("exitEast").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitNorth")) {
                    room.addExitbyID("north", Integer.parseInt(individualRoom.getElementsByTagName("exitNorth").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitSouth")) {
                    room.addExitbyID("south", Integer.parseInt(individualRoom.getElementsByTagName("exitSouth").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitNortheast")) {
                    room.addExitbyID("northeast", Integer.parseInt(individualRoom.getElementsByTagName("exitNortheast").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitNorthwest")) {
                    room.addExitbyID("northwest", Integer.parseInt(individualRoom.getElementsByTagName("exitNorthwest").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitSoutheast")) {
                    room.addExitbyID("southeast", Integer.parseInt(individualRoom.getElementsByTagName("exitSoutheast").item(0).getTextContent()));
                }
                if (isExit(individualRoom, "exitSouthwest")) {
                    room.addExitbyID("southwest", Integer.parseInt(individualRoom.getElementsByTagName("exitSouthwest").item(0).getTextContent()));
                }

                // Adds each room to a list
                rooms.add(room);
            }
        }
        return rooms;
    }


    private static boolean isExit(Element individualRoom, String exit) {
        return individualRoom.getElementsByTagName(exit).getLength() != 0;
    }
}
