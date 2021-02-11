package com.sourdoughsoftware.utility;
/**
 * Contains static functions for parsing
 * various xml documents for game play
 */

import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Weapon;
import com.sourdoughsoftware.world.Room;
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
                //Create new Enemy Object
                String name = singleEnemy.getElementsByTagName("name").item(0).getTextContent();
                String Background = singleEnemy.getElementsByTagName("background").item(0).getTextContent();
                enemy = new Enemy(name, Background);
                enemy.setHp(Integer.parseInt(singleEnemy.getElementsByTagName("hp").item(0).getTextContent()));
                enemy.setWeaponType(singleEnemy.getElementsByTagName("weaponType").item(0).getTextContent());
                enemy.setEnemyClass(singleEnemy.getElementsByTagName("class").item(0).getTextContent());
                //addInteractions(singleEnemy, enemy);
                //Add Enemy to list
                enemies.add(enemy);
            }
        }
        return enemies;
    }

    public static HashMap<String, Object> parseWeapons() {
        ItemTree tree = new ItemTree();
        ArrayList<Weapon> findableWeapons = new ArrayList<>();
        HashMap<String,Object> result = new HashMap<>();
        try {
            Document document = loadXML("resources/Weapons.xml");

            NodeList nodeList = document.getElementsByTagName("weapon");

            for (int current = 0; current < nodeList.getLength(); current++) {
                Node node = nodeList.item(current);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element currentElement = (Element) node;
                    String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
                    String description = currentElement.getElementsByTagName("description").item(0).getTextContent();
                    String victory = currentElement.getElementsByTagName("victory").item(0).getTextContent();
                    String attackPoints = currentElement.getElementsByTagName("attackPoints").item(0).getTextContent();
                    String findable = currentElement.getElementsByTagName("findable").item(0).getTextContent();
                    Weapon weapon = new Weapon(name, description, Integer.parseInt(attackPoints), victory);
                    tree.add(weapon);
                    if(findable.equals("true")) {
                        findableWeapons.add(weapon);
                    }
                }
            }
        } catch(ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
        result.put("findableWeapons", findableWeapons);
        result.put("weaponTree", tree);
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
