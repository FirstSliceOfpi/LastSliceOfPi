package com.sourdoughsoftware;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Story {

    //    I use this for practice and quick testing - remove before final code
//    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
//        roomDisplayXML(4);
//    }

    /**
     * This method will display game information from
     * separate .txt files
     */
    // TODO: adapt this for XML
    static void readFile() {
        try {
            File myFile = new File("LastSliceOfPi/resources/rapunzel.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String storyLine = myReader.nextLine();
                System.out.println(storyLine);
            }
            myReader.close();
        } catch (FileNotFoundException error) {
            System.out.println("That file is NOT found.");
            error.printStackTrace();
        }
    }

    /**
     * This method will display game information from
     * separate .txt files.
     * <p>
     * It will take an integer as a room[index].
     * <p>
     * It can throw FileNotFoundException
     * and ArrayIndexOutOfBoundsException
     */
    // TODO: adapt this for XML
    static String readFileArray(int roomIndex) {

        String output = "";

        String[] roomFiles = {"cinderella.txt",
                "frogprince.txt",
                "hanselandgretel.txt",
                "rapunzel.txt",
                "redridinghood.txt",
                "rumplestiltskin.txt",
                "shoemaker.txt",
                "sleepingbeauty.txt",
                "snowwhite.txt",
                "goldengoose.txt"};

        try {
            File myFile = new File("LastSliceOfPi/resources/" + roomFiles[roomIndex]);
            Scanner myReader = new Scanner(myFile);
            String storyLine = "";
            while (myReader.hasNextLine()) {
                storyLine += myReader.nextLine() + "\n";
            }
            myReader.close();
            output = storyLine;
        } catch (FileNotFoundException error) {
            output = "That file is NOT found.";
            error.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException error) {
            output = "That room is NOT found";
            error.printStackTrace();
        }
        return output;

    }
//    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//
//        Document document = builder.parse(new File("LastSliceOfPi/resources/Rooms.xml"));
//
//        document.getDocumentElement().normalize();
//
//        Element root = document.getDocumentElement();
//        System.out.println(root.getNodeName());
//        root.getAttribute("room");
//        root.getAttributes();
//
//        NodeList roomList = document.getElementsByTagName("roomDisplay");
//        System.out.println(roomList.getAttribute("1display"));
////        for (int temp = 0; temp < roomList.getLength(); temp++) {
////            Node node = roomList.item(temp);
////            if (node.getNodeType() == Node.ELEMENT_NODE) {
////                Element individualRoom = (Element) node;
////                System.out.println("Room ID: " + individualRoom.getAttribute("id"));
////                System.out.println("Room Name: " + individualRoom.getElementsByTagName("roomName").item(0).getTextContent());
////                System.out.println(individualRoom.getElementsByTagName("roomDisplay").item(0).getTextContent());
////                if (individualRoom.getElementsByTagName("exitWest").getLength() != 0) {
////                    System.out.println("Room West Exit: " + individualRoom.getElementsByTagName("exitWest").item(0).getTextContent());
////                }
////            }
////
////        }
//
//    }

}