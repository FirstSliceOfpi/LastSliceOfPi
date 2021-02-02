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

public class testXML {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("LastSliceOfPi/resources/Rooms.xml"));

        document.getDocumentElement().normalize();

//        Schema schema = null;
//        try {
//            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
//            schema = schemaFactory.newSchema(new File(name));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assert schema != null;
//        Validator validator = schema.newValidator();
//        validator.validate(new DOMSource(document));

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        root.getAttribute("room");
        root.getAttributes();

        NodeList roomList = document.getElementsByTagName("room");
        System.out.println("");
        for (int temp = 0; temp < roomList.getLength(); temp++) {
            Node node = roomList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element individualRoom = (Element) node;
                System.out.println("Room ID: " + individualRoom.getAttribute("id"));
                System.out.println("Room Name: " + individualRoom.getElementsByTagName("roomName").item(0).getTextContent());
                System.out.println(individualRoom.getElementsByTagName("roomDisplay").item(0).getTextContent());
                if (individualRoom.getElementsByTagName("exitWest").getLength() != 0) {
                    System.out.println("Room West Exit: " + individualRoom.getElementsByTagName("exitWest").item(0).getTextContent());
                }
            }

        }

    }
}
