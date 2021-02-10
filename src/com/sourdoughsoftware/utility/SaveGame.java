package com.sourdoughsoftware.utility;

import com.sourdoughsoftware.Game;
import com.sourdoughsoftware.gamepieces.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveGame {
    private Game game;

    public SaveGame() {
    }


    void save() {


        try {

            FileOutputStream fos = new FileOutputStream("usr.save");

            ObjectOutputStream oos = new ObjectOutputStream(fos);

//            oos.writeObject(game.getPlayer());

            oos.flush();
            oos.close();
            System.out.println("Game saved\n");
        } catch (Exception e) {
            System.out.println("Serialization Error!." + e.getClass() + ": " + e.getMessage());
        }
    }

    void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("usr.save");

            ObjectInputStream ois = new ObjectInputStream(fis);

            Player player = (Player) ois.readObject();
//            game.setPlayer(player);
            ois.close();
        }catch (Exception e) {
            System.out.println("Could not load saved game");
        }
    }

    void setGame(Game game) {
        this.game = game;
    }
}





