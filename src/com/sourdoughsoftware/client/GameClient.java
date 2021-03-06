package com.sourdoughsoftware.client;
import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.interaction.ChainOfEventException;
import com.sourdoughsoftware.utility.WelcomeScreen;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GameClient {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ChainOfEventException {
        if(args.length > 0) {
            if(args[0].equals("dev")){
                GameState.setDevMode();
            }
        }
        GameState.getDevMode();
        WelcomeScreen w = new WelcomeScreen();
        w.settingTheScene();
        w.loadingScreen();
        w.splash();
    }
}