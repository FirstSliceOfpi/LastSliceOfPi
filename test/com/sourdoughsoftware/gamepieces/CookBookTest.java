package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.utility.ItemTree;
import com.sourdoughsoftware.utility.XmlParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.sourdoughsoftware.utility.Colors.*;
import static org.junit.Assert.*;

public class CookBookTest {
    HashMap<String, Object> findable;
    ItemTree tree;
    ArrayList<Pie> pies;
    CookBook cb;


    @Before
    public void setUp() throws Exception {
        GameState.getDevMode();
        findable = XmlParser.parsePies();
        tree = (ItemTree) findable.get("pieTree");
        GameState.setTree(tree);
        pies = (ArrayList<Pie>) findable.get("findablePies");
        GameState.setFindableWeapons(pies);
        cb = new CookBook();
        GameState.setCookBook(cb);
    }

    @Test
    public void addRecipeTest() {
        int beforeRecipeSize = cb.getRecipesSize();
        int beforeCurrentRecipeSize = cb.getCurrentRecipesSize();
        assertEquals(8,beforeRecipeSize);
        assertEquals(0,beforeCurrentRecipeSize);
        cb.addRecipe();
        assertEquals(--beforeRecipeSize, cb.getRecipesSize());
        assertEquals(++beforeCurrentRecipeSize, cb.getCurrentRecipesSize());
        cb.addRecipe();
        assertEquals(--beforeRecipeSize, cb.getRecipesSize());
        assertEquals(++beforeCurrentRecipeSize, cb.getCurrentRecipesSize());
        cb.addRecipe();
        assertEquals(--beforeRecipeSize, cb.getRecipesSize());
        assertEquals(++beforeCurrentRecipeSize, cb.getCurrentRecipesSize());
        assertEquals(5,beforeRecipeSize);
        assertEquals(3,beforeCurrentRecipeSize);
    }

    @Test
    public void getRecipesTest() {
        assertEquals("The cook book is empty",cb.getRecipes() );
        cb.addRecipe();
        String result = cb.getRecipes();
        result.contains(ANSI_UNDERLINE + ANSI_BLUE + "Pie Cookbook" + ANSI_RESET + "\n");
    }

    @Test
    public void getDescription() {
        assertEquals("The cook book is empty",cb.getRecipes() );
        cb.addRecipe();
        String result = cb.getDescription();
        result.contains(ANSI_UNDERLINE + ANSI_BLUE + "Pie Cookbook" + ANSI_RESET + "\n");
    }
}