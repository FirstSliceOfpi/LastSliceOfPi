package com.sourdoughsoftware.gamepieces;
/**
 * CookBook extends the Noun class
 * This class holds all possible recipes and recipes currently discovered by the player
 */

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.utility.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static com.sourdoughsoftware.utility.Colors.ANSI_BLUE;
import static com.sourdoughsoftware.utility.Colors.ANSI_UNDERLINE;
import static com.sourdoughsoftware.utility.Colors.ANSI_RESET;

public class CookBook extends Noun implements Serializable {
    private ArrayList<String> recipes = new ArrayList<>();
    private ArrayList<String> currentRecipes = new ArrayList();

    /**
     * Primary constructor using findableWeapons in GameState
     */
    public CookBook() {
        super("cook book", "its a cookbook");
        ArrayList<Pie> findablePies = GameState.getFindableWeapons();
        for(int i = 0; i < findablePies.size(); i+=2) {
            Node parent = GameState.getTree().find(findablePies.get(i)).getParent();
            String ingredient1 = findablePies.get(i).getName();
            String ingredient2 = findablePies.get(i+1).getName();
            String recipe = parent.getItem().getName() + " = " + ingredient1 + " + " + ingredient2;
            recipes.add(recipe);
        }
    }

    /**
     * Allows adding a new recipe to the cookbook until all recipes are found
     */
    public String addRecipe() {
        Random rand = new Random();
        if(recipes.size() >0) {
            int index = rand.nextInt(recipes.size());
            String recipe = recipes.get(index);
            currentRecipes.add(recipe);
            recipes.remove(index);
            return "Added new recipe! Check your cook book.";
        }
        return "You know all the recipes!";
    }

    /**
     * Provides a cookbook string output of currently discovered recipes
     * @return
     */
    public String getRecipes() {
        if(currentRecipes.size() == 0) {
            return "The cook book is empty";
        }
        StringBuilder cookBook = new StringBuilder();
        cookBook.append(ANSI_UNDERLINE);
        cookBook.append(ANSI_BLUE);
        cookBook.append("Pie Cookbook");
        cookBook.append(ANSI_RESET);
        for(int i = 0; i < currentRecipes.size(); i++) {
            cookBook.append("\n");
            cookBook.append(i).append(") ").append(currentRecipes.get(i));
        }
        return cookBook.toString();
    }

    /**
     * Alternate method to call getRecipes()
     * @return
     */
    public String getDescription() {
        return getRecipes();
    }

}
