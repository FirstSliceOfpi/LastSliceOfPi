package com.sourdoughsoftware;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// These imports are .* because I have to make it work
// I will learn which specific files are necessary

public class Story {

//    I use this for practice and quick testing - remove before final code
//    public static void main(String[] args) {
//        readFileArray(9);
//    }

    /**
     * This method will display game information from
     * separate .txt files
     */
    // TODO: adapt this for XML
     static void readFile() {
        try{
            File myFile = new File("LastSliceOfPi/resources/rapunzel.txt");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()) {
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
     *
     * It will take an integer as a room[index].
     *
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

        try{
            File myFile = new File("LastSliceOfPi/resources/" + roomFiles[roomIndex]);
            Scanner myReader = new Scanner(myFile);
            String storyLine = "";
            while(myReader.hasNextLine()) {
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

}
