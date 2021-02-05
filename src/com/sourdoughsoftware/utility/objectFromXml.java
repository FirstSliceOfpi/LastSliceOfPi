package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import com.sourdoughsoftware.Room;
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
 * Class to load XML files, and methods to parse them for use by game
 */
public class objectFromXml {

    public static List<Room> parseRoom() throws ParserConfigurationException, IOException, SAXException {
        List<Room> rooms = new ArrayList<>();
        Room room;
        // Uncomment next line on windows systems
//        Document document = loadXML("LastSliceOfPi/resources/Rooms.xml");
//         Uncomment next line on *nix systems
         Document document = loadXML("resources/Rooms.xml");
        NodeList nList = document.getElementsByTagName("room");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element individualRoom = (Element) node;
                //Create new Room Object
                room = new Room();
                room.setRoomID(Integer.parseInt(individualRoom.getAttribute("id")));
                room.setName(individualRoom.getElementsByTagName("roomName").item(0).getTextContent());
                if (checkElementLength(individualRoom, "description")) {
                    room.setDescription(individualRoom.getElementsByTagName("description").item(0).getTextContent());
                }
                if (checkElementLength(individualRoom, "shortDescription")) {
                    room.setShortDescription(individualRoom.getElementsByTagName("shortDescription").item(0).getTextContent());
                }
                checkDirections(room, individualRoom);
                //Add Room to list
                rooms.add(room);
            }
        }
        return rooms;
    }

    public static List<Item> parseItems() throws ParserConfigurationException, IOException, SAXException {
        List<Item> items = new ArrayList<>();
        Item item;

        // Uncomment next line on windows systems
//        Document document = loadXML("LastSliceOfPi/resources/Items.xml");
        // Uncomment next line on *nix systems
        Document document = loadXML("resources/Items.xml");
        NodeList nList = document.getElementsByTagName("item");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element singleItem = (Element) node;
                //Create new Room Object
                item = new Item();
                item.setItemID(Integer.parseInt(singleItem.getAttribute("id")));
                item.setName(singleItem.getElementsByTagName("name").item(0).getTextContent());
                if (checkElementLength(singleItem, "description")) {
                    item.setDescription(singleItem.getElementsByTagName("description").item(0).getTextContent());
                }
                if (checkElementLength(singleItem, "pival")) {
                    item.setPival(singleItem.getElementsByTagName("pival").item(0).getTextContent());
                }
                addInteractions(singleItem, item);
                //Add Room to list
                items.add(item);
            }
        }
        return items;
    }

    private static void addInteractions(Element singleItem, Item item) {
        for (String verb : Verbs.getAllVerbs()) {
            if (checkElementLength(singleItem, verb)) {
                item.setVerbInteraction(verb, singleItem.getElementsByTagName(verb).item(0).getTextContent());
            }
        }
    }

    private static Document loadXML(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        document.getDocumentElement().normalize();
        return document;
    }

    private static void checkDirections(Room room, Element individualRoom) {
        if (checkElementLength(individualRoom, "exitWest")) {
            room.addExitbyID("west", Integer.parseInt(individualRoom.getElementsByTagName("exitWest").item(0).getTextContent()));
            room.addExitbyID("w", Integer.parseInt(individualRoom.getElementsByTagName("exitWest").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitEast")) {
            room.addExitbyID("east", Integer.parseInt(individualRoom.getElementsByTagName("exitEast").item(0).getTextContent()));
            room.addExitbyID("e", Integer.parseInt(individualRoom.getElementsByTagName("exitEast").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitNorth")) {
            room.addExitbyID("north", Integer.parseInt(individualRoom.getElementsByTagName("exitNorth").item(0).getTextContent()));
            room.addExitbyID("n", Integer.parseInt(individualRoom.getElementsByTagName("exitNorth").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitSouth")) {
            room.addExitbyID("south", Integer.parseInt(individualRoom.getElementsByTagName("exitSouth").item(0).getTextContent()));
            room.addExitbyID("s", Integer.parseInt(individualRoom.getElementsByTagName("exitSouth").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitNorthwest")) {
            room.addExitbyID("northwest", Integer.parseInt(individualRoom.getElementsByTagName("exitNorthwest").item(0).getTextContent()));
            room.addExitbyID("nw", Integer.parseInt(individualRoom.getElementsByTagName("exitNorthwest").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitNortheast")) {
            room.addExitbyID("northeast", Integer.parseInt(individualRoom.getElementsByTagName("exitNortheast").item(0).getTextContent()));
            room.addExitbyID("ne", Integer.parseInt(individualRoom.getElementsByTagName("exitNortheast").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitSouthwest")) {
            room.addExitbyID("southwest", Integer.parseInt(individualRoom.getElementsByTagName("exitSouthwest").item(0).getTextContent()));
            room.addExitbyID("sw", Integer.parseInt(individualRoom.getElementsByTagName("exitSouthwest").item(0).getTextContent()));
        }
        if (checkElementLength(individualRoom, "exitSoutheast")) {
            room.addExitbyID("southeast", Integer.parseInt(individualRoom.getElementsByTagName("exitSoutheast").item(0).getTextContent()));
            room.addExitbyID("se", Integer.parseInt(individualRoom.getElementsByTagName("exitSoutheast").item(0).getTextContent()));
        }
    }

    private static boolean checkElementLength(Element individualRoom, String element) {
        return individualRoom.getElementsByTagName(element).getLength() != 0;
    }
}