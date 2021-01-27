package com.sourdoughsoftware;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Story {

    /** This method will display the story from another text-based file
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
}
