package com.sourdoughsoftware;

import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Prompter;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.XmlParser;

import java.util.Scanner;

public class Game {
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    Player player = new Player("Edgar");

    public Game() {
        XmlParser.parseItems();
        XmlParser.parseVerbs();
    }


    public void start() {
        boolean gameOver = false;
        while(!gameOver) {
            System.out.println(Actions.execute(TextParser.parse(prompter.prompt("What do you want to do?"))));
        }
    }
}

