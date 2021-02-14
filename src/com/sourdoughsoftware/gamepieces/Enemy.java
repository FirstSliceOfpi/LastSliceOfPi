package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Command;
import org.w3c.dom.NodeList;

public class Enemy extends Noun implements java.io.Serializable{


    private String name;
    private String enemyClass;
    private int hp;
    private String weaponType;
    private String background;




    public Enemy(String name, String background) {
        super(name, background);

    }

    // Single ctor
    public Enemy(String name, String enemyClass, int hp, String weaponType, String background) {
        this.name = name;
        this.enemyClass = enemyClass;
        this.hp = hp;
        this.weaponType = weaponType;
        this.background = background;
        setAttackable(true);
    }




    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnemyClass() {
        return enemyClass;
    }

    public void setEnemyClass(String enemyClass) {
        this.enemyClass = enemyClass;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
