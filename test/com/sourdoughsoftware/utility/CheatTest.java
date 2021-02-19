package com.sourdoughsoftware.utility;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CheatTest {

    @Test
    public void showCheatArtTest() throws InterruptedException, IOException {
        Cheat cheat = Cheat.getInstance();
        cheat.showCheatArt();
    }
}