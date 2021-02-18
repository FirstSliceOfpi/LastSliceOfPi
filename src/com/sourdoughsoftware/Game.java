package com.sourdoughsoftware;

import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.CookBook;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.ChainOfEventException;
import com.sourdoughsoftware.interaction.Prompter;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.Colors;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.PrintFiles;
import com.sourdoughsoftware.utility.XmlParser;
import com.sourdoughsoftware.world.Directions;
import com.sourdoughsoftware.world.World;
import org.xml.sax.SAXException;
import static com.sourdoughsoftware.utility.Colors.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    PrintFiles p = new PrintFiles();

    public Game() throws IOException, SAXException, ParserConfigurationException {
//        XmlParser.parseItems();
        XmlParser.parseVerbs();
        XmlParser.parseEnemy();
        XmlParser.parseNouns();
        new Directions();
        new World();
        HashMap<String, Object> pies = XmlParser.parsePies();
        GameState.setTree((ItemTree) pies.get("pieTree"));
        GameState.setFindableWeapons((ArrayList<Pie>) pies.get("findablePies"));
        GameState.setCookBook(new CookBook());
    }

    public void start() throws ChainOfEventException {
        boolean gameOver = false;
        while(!gameOver) {
            if(GameState.getDevMode()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Total Enemies: ");
                sb.append(Enemy.getTotalEnemies());
                sb.append(" Total Hungry: ");
                sb.append(Enemy.getTotalEnemiesHungry());
                sb.append(" Total Alive: ");
                sb.append(Enemy.getTotalEnemiesAlive());
                System.out.println(sb);
            }
            System.out.println("\n" + ANSI_YELLOW + World.getCurrentRoom().getName() + "\n" + ANSI_RESET);
            TextParser.parse(Prompter.prompt("What do you want to do?"));
            System.out.println(Actions.execute());
            if (Enemy.getTotalEnemiesHungry() == 0){
                p.print("goodEnding");
                gameOver = true;
            }else if (Enemy.getTotalEnemiesAlive() == 0) {
                p.print("badEnding");
                gameOver = true;
            }else if (Enemy.getTotalEnemiesAlive() + Enemy.getTotalEnemiesHungry() == Enemy.getTotalEnemies()) {
                p.print("GameLogo");
                System.out.println("Guess you do what you want huh?");
                gameOver = true;
            }
        }

        //TODO print an ending picture
//        p.print("GameLogo");
    }

}

