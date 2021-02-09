package com.sourdoughsoftware.client;

import com.sourdoughsoftware.Game;
import com.sourdoughsoftware.utility.WelcomeScreen;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GameClient {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//        Game myGame = new Game();
//        myGame.start();
        WelcomeScreen w = new WelcomeScreen();
        w.loadingScreen();
        w.splash();
    }
}