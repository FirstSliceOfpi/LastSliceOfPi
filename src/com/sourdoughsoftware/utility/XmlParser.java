package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Item;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
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

public class XmlParser {

    private static Document loadXML(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        document.getDocumentElement().normalize();
        return document;
    }

    public static void parseItems() {

        try {
            Document document = loadXML("resources/Items.xml");

            NodeList nodeList = document.getElementsByTagName("item");

            for (int current = 0; current < nodeList.getLength(); current++) {
                Node node = nodeList.item(current);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element currentElement = (Element) node;
                    String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
                    String description = currentElement.getElementsByTagName("description").item(0).getTextContent();

                    new Item(name, description);

                }
            }
        } catch(ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void parseVerbs() {

        try {
            Document document = loadXML("resources/Verbs.xml");

            NodeList nodeList = document.getElementsByTagName("verb");

            for (int current = 0; current < nodeList.getLength(); current++) {
                Node node = nodeList.item(current);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element currentElement = (Element) node;
                    String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
                    VerbGroup group = VerbGroup.valueOf(currentElement.getElementsByTagName("group").item(0).getTextContent());
                    new Verb(name, group);

                }
            }
        } catch(ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }

    }



}
