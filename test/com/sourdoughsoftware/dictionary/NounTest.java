//package com.sourdoughsoftware.dictionary;
//
//import com.sourdoughsoftware.GameState;
//import com.sourdoughsoftware.interaction.Command;
//import com.sourdoughsoftware.interaction.Event;
//import com.sourdoughsoftware.world.World;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static com.sourdoughsoftware.dictionary.VerbGroup.changeDescription;
//import static com.sourdoughsoftware.dictionary.VerbGroup.print;
//import static org.junit.Assert.*;
//
//public class NounTest {
//    ArrayList<Event> light;
//    Noun noun;
//    Verb verb;
//
//    @Before
//    public void setUp() {
//        GameState.getDevMode();
//       light = new ArrayList<>(){{
//            add(new Event(print, "printMe"));
//            add(new Event(changeDescription, "changeMe"));
//        }};
//       noun = new Noun("name", "description");
//       verb = new Verb("print",print);
//        try {
//            new World();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void setAndGetActionLight() {
//        noun.setAction("light", light);
//        assertEquals("print",noun.getAction("light"));
//        assertEquals("printMe", noun.getAction("light"));
//        assertEquals("changeDescription", noun.getAction("light"));
//        assertEquals("changeMe", noun.getAction("light"));
//    }
//
//}