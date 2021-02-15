package com.sourdoughsoftware.dictionary;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NounTest {
    ArrayList<String[]> light;
    Noun noun;

    @Before
    public void setUp() {
       light = new ArrayList<>(){{
            add(new String[]{"print", "printMe"});
            add(new String[]{"changeDescription", "changeMe"});
        }};
       noun = new Noun("name", "description");
    }

    @Test
    public void setAndGetActionLight() {
//        noun.setAction("light", light);
//        assertEquals("print",noun.getAction("light").get(0)[0]);
//        assertEquals("printMe", noun.getAction("light").get(0)[1]);
//        assertEquals("changeDescription", noun.getAction("light").get(1)[0]);
//        assertEquals("changeMe", noun.getAction("light").get(1)[1]);
    }

}