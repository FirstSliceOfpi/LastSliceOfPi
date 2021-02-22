package com.sourdoughsoftware;

import com.sourdoughsoftware.world.Room;
import com.sourdoughsoftware.world.World;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
/*
 ________      ________      __________      ___________
 |       |     |Red    |     |        |      |         |
 |Hansel&|     |Riding |     | Frog   | ___  |Shoemaker|
 |Gretel |     | Hood  |     |  Prince|      |         |
 |_______|     |_______|     |________|      |_________|
    |              |             |                |
    |              |             |                |
    |              |             |                |
________      ________      ________          __________
|Cinder-|     |       |     |       |         |         |
| Ella  | ___ | Start | ___ |Snow   |         |Golden   |
|       |     |       |     | White |         |  Goose  |
|_______|     |_______|     |_______|         |_________|
    |             |
    |             |
    |             |
________      __________     _________
|       |     |        |     |        |
| Elsa  | ___ |Rapunzel| ___ |Rumple- |
|       |     |        |     |stilskin|
|_______|     |________|     |________|
                  |
                  |                                         Rapunzel
                  |                                            ^
 _________     _________      ___________                _______________
 |        |    |        |     |         |               |               |
 |Aladdin | ___|Sleeping| ___ |Sheriff  | ****>>>      |     Narnia      |  > Golden Goose
 |        |    | Beauty |     |  Woody  |      Hansel< |                 |
 |________|    |________|     |_________|         &    |_________________|
                                               Gretel
                                                               v
                                                             Start

 */
public class WorldTest {
    World w = new World();
    List<Room> gameMap = w.getGameMap();

    public WorldTest() throws ParserConfigurationException, SAXException, IOException {
    }

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testExitsOfRoomsPositive() {
        System.out.println(gameMap.get(0).roomList);

        // Test exits of Loading Room Start
        assertEquals("{east=start, south=start, north=start, west=start}",String.valueOf(gameMap.get(0).roomList));
        // Test exits of Start
        assertEquals("{east=snow white, south=rapunzel, north=red riding hood, west=cinderella}",String.valueOf(gameMap.get(1).roomList));

        // Test exits of Snow White
        assertEquals("{north=frog prince, west=start}",String.valueOf(gameMap.get(2).roomList));

        // Test exits of Frog Prince
        assertEquals("{east=shoemaker, south=snow white}",String.valueOf(gameMap.get(3).roomList));

        // Test exits of Rapunzel
        assertEquals("{east=rumplestiltskin, south=sleeping beauty, west=elsa}",String.valueOf(gameMap.get(4).roomList));

        // Test exits of Rumplestiltskin
        assertEquals("{west=rapunzel}",String.valueOf(gameMap.get(5).roomList));

        // Test exits of Cinderella
        assertEquals("{east=start, north=hansel and gretel}",String.valueOf(gameMap.get(6).roomList));

        // Test exits of Hansel and Gretel
        assertEquals("{south=cinderella}",String.valueOf(gameMap.get(7).roomList));

        // Test exits of Red Riding Hood
        assertEquals("{south=start}",String.valueOf(gameMap.get(8).roomList));

        // Test exits of Shoemaker
        assertEquals("{south=golden goose, west=frog prince}",String.valueOf(gameMap.get(9).roomList));

        // Test exits of Sleeping Beauty
        assertEquals("{east=sheriff woody, north=rapunzel, west=aladdin}",String.valueOf(gameMap.get(10).roomList));

        // Test exits of Golden Goose
        assertEquals("{north=shoemaker}",String.valueOf(gameMap.get(11).roomList));

        // Test exits of Sheriff Woody
        assertEquals("{east=narnia, west=sleeping beauty}",String.valueOf(gameMap.get(12).roomList));

        // Test exits of Aladdin
        assertEquals("{east=sleeping beauty}",String.valueOf(gameMap.get(13).roomList));

        // Test exits of Narnia
        assertEquals("{east=golden goose, south=start, north=rapunzel, west=hansel and gretel}",String.valueOf(gameMap.get(14).roomList));

        // Test exits of Elsa
        assertEquals("{east=rapunzel, north=cinderella}",String.valueOf(gameMap.get(15).roomList));

    }


    //    @Test
//    public void populateEnemies() throws ParserConfigurationException, SAXException, IOException {
//        List<Room> gameMap = new ArrayList<>();
//        HashMap<String, Enemy> villians = new HashMap<>();
//        List<Enemy> enemies = XmlParser.parseEnemy();
//        for (Enemy enemy: enemies) {
//            villians.put(enemy.getName(),enemy);
//
//        }
//        for (String i : villians.keySet()) {
//            System.out.println(i);
//        }
//        Enemy scar = villians.get("Scar");
//        Enemy gaston = villians.get("Gaston");
//        System.out.println(scar.getName() + scar.getBackground() + scar.getHp());
//        System.out.println(gaston.getName() + gaston.getBackground() + gaston.getHp());
//    }
}