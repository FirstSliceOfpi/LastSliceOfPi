package com.sourdoughsoftware.utility;
/**
 * Contains static functions for parsing
 * various xml documents for game play
 */

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.interaction.Event;
import com.sourdoughsoftware.world.Room;
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
import java.util.Objects;

import static java.lang.Boolean.parseBoolean;

public class XmlParser {

//    public static void parseItems() {
//
//        try {
//            Document document = loadXML("resources/Items.xml");
//
//            NodeList nodeList = document.getElementsByTagName("item");
//
//            for (int current = 0; current < nodeList.getLength(); current++) {
//                Node node = nodeList.item(current);
//
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element currentElement = (Element) node;
//                    String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
//                    String description = currentElement.getElementsByTagName("description").item(0).getTextContent();
//
//                    new Item(name, description);
//
//                }
//            }
//        } catch (ParserConfigurationException | IOException | SAXException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

    public static void parseNouns() {
        HashMap<String, Noun> temp = new HashMap<>();

        ItemTree tree = new ItemTree();
        try {
            Document document = loadXML("resources/Nouns.xml");
            NodeList nodeList = document.getElementsByTagName("item");

            for (int current = 0; current < nodeList.getLength(); current++) {
                Node node = nodeList.item(current);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element currentElement = (Element) node;

                    String name = Objects.requireNonNull(
                            currentElement.getElementsByTagName("name"))
                            .item(0).getTextContent();
                    String description = Objects.requireNonNull(
                            currentElement.getElementsByTagName("description"))
                            .item(0).getTextContent();
                    String attackPoints = Objects.requireNonNull(
                            currentElement.getElementsByTagName("attackPoints"))
                            .item(0).getTextContent();
                    boolean findable = parseBoolean(Objects.requireNonNull(
                            currentElement.getElementsByTagName("Findable"))
                            .item(0).getTextContent());
                    Item noun = new Item(name, description);
                    try {
                        noun.setFindable(findable);
                    } catch (Exception e) {
                        if (GameState.getDevMode()) System.out.println(e);
                    }
                    temp.put(name, noun);

                    NodeList modifiers = Objects.requireNonNull(
                            currentElement.getElementsByTagName("modifiers"))
                            .item(0).getChildNodes();

                    ArrayList<Event> eventsList;

                    for (int i = 0; i < modifiers.getLength(); i++) {
                        Node modNode = modifiers.item(i);

                        if(modNode.getNodeType() != Node.ELEMENT_NODE ) { continue; }
                        Element mod = (Element) modNode;
                        String key = ((Element) modNode).getAttribute("key");
                        Noun keyNoun = temp.get(key);


                        String modName = mod.getNodeName(); // <light>
                        if(Dictionary.INSTANCE.getVerb(modName) == null) {
                            new Verb(modName, VerbGroup.unique);
                        }
                        eventsList = addActions(mod, keyNoun);
                        noun.setAction(modName, eventsList);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }

    ;

    private static ArrayList<Event> addActions(Element mod, Noun key) {
        ArrayList<Event> eventList = new ArrayList<>();
        NodeList actions = mod.getElementsByTagName("action");
        if(actions.getLength() == 0) return eventList;
        Event event = null;
        for (int j = 0; j < actions.getLength(); j++) {
            Node action = actions.item(j); // <action>
            NodeList children = action.getChildNodes();
            String argument = null;
            for(int i = 0; i < children.getLength(); i++) {
                if(children.item(i).getNodeType() != 1) { continue; }
                argument = children.item(i).getTextContent().strip();
            }
            String verb = action.getFirstChild().getTextContent().strip();
            event = new Event(VerbGroup.valueOf(verb), argument, key); // [print, The candle is lit]
            eventList.add(event);
        }
        return eventList;
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
        } catch (ParserConfigurationException | IOException | SAXException e) {
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
                String background = singleEnemy.getElementsByTagName("background").item(0).getTextContent();
                int hp = Integer.parseInt(singleEnemy.getElementsByTagName("hp").item(0).getTextContent());
                String foodAlergies = singleEnemy.getElementsByTagName("foodAlergies").item(0).getTextContent();
                String enemyClass = singleEnemy.getElementsByTagName("class").item(0).getTextContent();
                String deadtext = singleEnemy.getElementsByTagName("deadtext").item(0).getTextContent();
                //addInteractions(singleEnemy, enemy);
                //Add Enemy to list
                enemy = new Enemy(name, enemyClass ,hp, foodAlergies,background,deadtext);
                enemies.add(enemy);

            }
        }
        return enemies;
    }

    public static ArrayList<Room> parseRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            Document document = loadXML("resources/Rooms.xml");
            NodeList nodeList = document.getElementsByTagName("room");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element roomElement = (Element) node;
//                    String roomID = roomElement.getElementsByTagName("roomID").item(0).getTextContent();
                    String name = roomElement.getElementsByTagName("roomName").item(0).getTextContent();
                    String description = roomElement.getElementsByTagName("description").item(0).getTextContent();

                    Room room = new Room(name,description);
                    for (int j =0; j< roomElement.getElementsByTagName("exit").getLength(); j++) {
                        Element el = (Element) roomElement.getElementsByTagName("exit").item(j);

                        String direction = el.getElementsByTagName("compass").item(0).getTextContent();
                        String directionName = el.getElementsByTagName("rose").item(0).getTextContent();

                        room.roomList.put(direction,directionName);
                    }
                    rooms.add(room);

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public static HashMap<String, Object> parsePies() {
        ItemTree tree = new ItemTree();
        ArrayList<Pie> findablePies = new ArrayList<>();
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Noun> temp = new HashMap<>();
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
                    temp.put("name", pie);
                    for (int i = 0; i < modifiers.getLength(); i++) {
                        if(modifiers.item(i).getNodeType() != Node.ELEMENT_NODE) { continue; }
                        String modiName = modifiers.item(i).getNodeName();
                        if(!(modiName.equals("Wieldable") || modiName.equals("Findable") || modiName.equals("Grabable"))) {
                            ArrayList<Event> eventsList;
                                Node modNode = modifiers.item(i);

                                if(modNode.getNodeType() != Node.ELEMENT_NODE ) { continue; }
                                Element mod = (Element) modNode;
                                String key = ((Element) modNode).getAttribute("key");
                                Noun keyNoun = temp.get(key);

                                String modName = mod.getNodeName(); // <light>
                                if(Dictionary.INSTANCE.getVerb(modName) == null) {
                                    new Verb(modName, VerbGroup.unique);
                                }
                                eventsList = addActions(mod, keyNoun);
                                pie.setAction(modName, eventsList);
                                continue;
                            }
                        else {
                            String modifierName = "set" + modiName;
                            boolean modifierValue = parseBoolean(modifiers.item(i).getTextContent());
                            try {
                                pie.getClass().getMethod(modifierName, Boolean.TYPE).invoke(pie, modifierValue);
                            } catch (Exception e) {
                                if (GameState.getDevMode()) System.out.println(e);
                            }
                        }
                    }
                    tree.add(pie);
                    // Left findable pies as an array list for testing purposes
                    if (pie.isFindable()) {
                        findablePies.add(pie);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            if(GameState.getDevMode()) System.out.println(e.getMessage());
        }
        result.put("findablePies", findablePies);
        result.put("pieTree", tree);
        return result;
    }

    ;

    private static Document loadXML(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        document.getDocumentElement().normalize();
        return document;
    }
}
