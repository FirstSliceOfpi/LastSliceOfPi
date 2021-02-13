package com.sourdoughsoftware.utility;
/**
 * Contains static functions for parsing
 * various xml documents for game play
 */

import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.parseBoolean;

public class XmlParser {

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

    public static List<Enemy> parseEnemy() throws ParserConfigurationException, IOException, SAXException {
        List<Enemy> enemies = new ArrayList<>();
        Enemy enemy;
        Document document;
        String dir = System.getProperty("user.dir") + "/resources/Enemies.xml";
        document = loadXML(dir);
        NodeList nList = document.getElementsByTagName("enemy");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element singleEnemy = (Element) node;
                //Create new Room Object
                enemy = new Enemy();
                enemy.setHp(Integer.parseInt(singleEnemy.getElementsByTagName("hp").item(0).getTextContent()));
                enemy.setName(singleEnemy.getElementsByTagName("name").item(0).getTextContent());
                enemy.setBackground(singleEnemy.getElementsByTagName("background").item(0).getTextContent());
                enemy.setWeaponType(singleEnemy.getElementsByTagName("weaponType").item(0).getTextContent());
                enemy.setEnemyClass(singleEnemy.getElementsByTagName("class").item(0).getTextContent());
                //addInteractions(singleEnemy, enemy);
                //Add Room to list
                enemies.add(enemy);
            }
        }
        return enemies;
    }

    public static HashMap<String, Object> parsePies() {
        ItemTree tree = new ItemTree();
        ArrayList<Pie> findablePies = new ArrayList<>();
        HashMap<String,Object> result = new HashMap<>();
        try {
            Document document = loadXML("resources/Pies.xml");

            NodeList nodeList = document.getElementsByTagName("pie");

            for (int current = 0; current < nodeList.getLength(); current++) {
                Node node = nodeList.item(current);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element currentElement = (Element) node;
                    String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
                    String description = currentElement.getElementsByTagName("description").item(0).getTextContent();
                    String victory = currentElement.getElementsByTagName("victory").item(0).getTextContent();
                    String attackPoints = currentElement.getElementsByTagName("attackPoints").item(0).getTextContent();
                    NodeList modifiers = currentElement.getElementsByTagName("modifiers").item(0).getChildNodes();
                    Pie pie = new Pie(name, description, Integer.parseInt(attackPoints), victory);
                    for(int i = 0; i < modifiers.getLength(); i++) {
                        String modifierName = "set" + modifiers.item(i).getNodeName();
                        boolean modifierValue = parseBoolean(modifiers.item(i).getTextContent());
                        try {
                            pie.getClass().getMethod(modifierName, Boolean.TYPE).invoke(pie, modifierValue);
                        }catch(Exception e) {
                            //do nothing
                        }
                    }
                    tree.add(pie);
                    // Left findable pies as an array list for testing purposes
                    if(pie.isFindable()) {
                        findablePies.add(pie);
                    }
                }
            }
        } catch(ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
        result.put("findablePies", findablePies);
        result.put("pieTree", tree);
        return result;
    };

    private static Document loadXML(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        document.getDocumentElement().normalize();
        return document;
    }
}
