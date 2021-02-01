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

public class PopulateRoomsFromXML {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        List<Room> rooms = parseRoomXML();
        System.out.println(rooms.toString());
    }

    private static List<Room> parseRoomXML() throws ParserConfigurationException, IOException, SAXException {
        List<Room> rooms = new ArrayList<>();
        Room room = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("LastSliceOfPi/resources/Rooms.xml"));
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
                if (individualRoom.getElementsByTagName("exitWest").getLength() != 0) {
                    room.addExitbyID("west", room.getId());
                }
                //Add Room to list
                rooms.add(room);
            }
        }return rooms;
    }
}
