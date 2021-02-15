package com.sourdoughsoftware.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintFiles {
    public PrintFiles() {
    }

    public void print(String fileToRead) {
        if (fileToRead != null) {
            String results = null;
            try {
                results = Files.readString(Path.of("resources", fileToRead));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(results);
        } else {
            System.out.println("Sorry file not in path.");
        }
    }
}




