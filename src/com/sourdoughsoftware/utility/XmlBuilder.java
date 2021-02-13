package com.sourdoughsoftware.utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.*;

public class XmlBuilder {
    private final HashMap<String, ArrayList<HashMap<String, String>>> words;

    public XmlBuilder(String file) {
        words = readXMLFile(file);
        rebuildXMLFile();
    }

    private void rebuildXMLFile() {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Items");
            doc.appendChild(rootElement);

            // Add the first table
            for (int id = 0; id < words.get("pie").size(); id++) {
                Element item = doc.createElement("item");
                item.setAttribute("id", Integer.toString(id));
                rootElement.appendChild(item);
                Set keys = words.get("pie").get(id).keySet();
                addNodes(keys, doc, id, item);
            }

            NodeList items = doc.getElementsByTagName("item");
            // Add the second table
            for (int i = 0; i < items.getLength(); i++) {
                Element modElement = doc.createElement("modifiers");
                items.item(i).appendChild(modElement);
                for (int row = 0; row < words.get("modifiers").size(); row++) {
                    Set keys = words.get("modifiers").get(row).keySet();
                    int id = Integer.parseInt(words.get("modifiers").get(row).get("id"));
                    if ((id-1) == i) {
                        NodeList field;
                        Node modifier = null;
                        Node action = null;
                        for (Object key : keys) {
                            String column = key.toString();
                            String value = words.get("modifiers").get(row).get(column);
                            if (column.equals("id")) continue;
                            if (column.equals("field")) {
                                field = modElement.getElementsByTagName(value);
                                if (field.getLength() == 0) {
                                    modifier = doc.createElement(value);
                                    modElement.appendChild(modifier);
                                } else {
                                    modifier = field.item(0);
                                }
                            }
                            if (modifier != null) {
                                if (column.equals("action")) {
                                    action = createActionNode(doc, value, modifier);
                                }
                                if (key.toString().equals("arg")) {
                                    Element arg = doc.createElement("arg");
                                    arg.appendChild(doc.createTextNode(value));
                                    assert action != null;
                                    action.appendChild(arg);
                                }
                            }
                        }
                    }

                }
            }

            saveXmlFile(doc);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private Node createActionNode(Document doc, String value, Node modifier) {
        Node action = doc.createElement("action");
        action.appendChild(doc.createTextNode(value));
        modifier.appendChild(action);
        return action;
    }

    private void addNodes(Set keys, Document doc, int id, Element item) {
        for (Object key : keys) {
            if (key.toString().equals("id")) continue;
            String value = words.get("pie").get(id).get(key.toString());
            Element subItem = doc.createElement(key.toString());
            subItem.appendChild(doc.createTextNode(value));
            item.appendChild(subItem);
        }
    }

    private void saveXmlFile(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./resources/Nouns.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private HashMap<String, ArrayList<HashMap<String, String>>> readXMLFile(String file) {
        HashMap<String, ArrayList<HashMap<String, String>>> words = new HashMap<>();
        try {
            File xmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document wordDoc = dBuilder.parse(xmlFile);
            wordDoc.getDocumentElement().normalize();
            words = buildWordMap(wordDoc);

        } catch (javax.xml.parsers.ParserConfigurationException parseE) {
            System.out.println(parseE);
        } catch (java.io.IOException ioE) {
            System.out.println(ioE);
        } catch (org.xml.sax.SAXException saxException) {
            System.out.println(saxException);
        } catch (Exception e) {
            System.out.println(e);
        }
        return words;
    }

    private HashMap<String, ArrayList<HashMap<String, String>>> buildWordMap(Document wordDoc) {
        HashMap<String, ArrayList<HashMap<String, String>>> words = new HashMap<>();
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "/Workbook/Worksheet";
        try {
            NodeList worksheet = (NodeList) xpath.evaluate(expression, wordDoc, XPathConstants.NODESET);
            for (int i = 0; i < worksheet.getLength(); i++) {
                addTableToWords(worksheet.item(i), words);
            }
        } catch (javax.xml.xpath.XPathExpressionException e) {
            System.out.println(e);
        }
        return words;
    }

    private void addTableToWords(Node table, HashMap<String, ArrayList<HashMap<String, String>>> words) {
        String expression = "Table/Row";
        String tableName = table.getAttributes().getNamedItem("ss:Name").getNodeValue();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            NodeList rows = (NodeList) xpath.evaluate(expression, table, XPathConstants.NODESET);
            NodeList keys = (NodeList) xpath.evaluate("Cell", rows.item(0), XPathConstants.NODESET);
            // add column keys to a List
            LinkedList<String> columnKeys = new LinkedList<>();
            for (int i = 0; i < keys.getLength(); i++) {
                String columnName = keys.item(i).getTextContent();
                columnKeys.add(columnName);
            }
            // add remaining rows

            for (int i = 1; i < rows.getLength(); i++) {
                LinkedHashMap<String, String> row = new LinkedHashMap<>();
                NodeList cells = (NodeList) xpath.evaluate("Cell", rows.item(i), XPathConstants.NODESET);
                // add each cell in the row
                for (int j = 0; j < cells.getLength(); j++) {
                    row.put(columnKeys.get(j), cells.item(j).getTextContent());
                }
                list.add(row);
            }
        } catch (javax.xml.xpath.XPathExpressionException e) {
            System.out.println(e);
        }
        words.put(tableName, list);
    }
}
