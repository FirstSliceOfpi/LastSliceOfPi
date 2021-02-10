package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Game;
import com.sourdoughsoftware.interaction.Prompter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Welcome screen displays a welcome message to the player when the game begins.
 *
 * @author Tyrone Moore, Kelson Smith, and Gina Villegas
 * @version 1.0.0
 */
public class WelcomeScreen {
    public static final String WELCOME_MESSAGE = "Welcome to \"The Last Slice of Pi\"";
    PrintFiles p = new PrintFiles();
    Prompter parser;
    SaveGame save = new SaveGame();

    public WelcomeScreen() {
        parser = new Prompter(new Scanner(System.in));
    }


    /**
     * @return the welcome message
     */
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }


    public void loadingScreen() {
        p.print("GameLogo");
        System.out.println("Press Enter to continue...");

        try {
            System.in.read();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void splash() throws ParserConfigurationException, SAXException, IOException {
        System.out.println("1. Start new story\n" +
                "2. Continue from where you left off\n");
        String gameType = parser.prompt("Please make a selection >>> ");
        if (gameType.matches("1")) {
            Game myGame = new Game();
            myGame.start();
        }else if (gameType.matches("2")) {
            try {
                Game myGame = new Game();
                save.setGame(myGame);
                save.loadGame();
                myGame.start();
            }catch (NullPointerException e) {
                splash();
            }
        }else {
            System.out.println("Invalid selection, please choose either 1 or 2.");
            splash();
        }
    }



}
