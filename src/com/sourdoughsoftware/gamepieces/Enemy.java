package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.VerbGroup;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.interaction.Actions;
import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.interaction.Event;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Enemy extends Item implements java.io.Serializable{


//    private String name;
    private String enemyClass;
    private int hp;
    private String weaponType;
    private String background;
    private String pie;



    public Enemy(String name, String background) {
        super(name, background);

    }

    // Single ctor
    public Enemy(String name, String enemyClass, int hp, String pie, String background) {
        super(name, background);
//        this.name = name;
        this.enemyClass = enemyClass;
        this.hp = hp;
        this.background = background;
        this.pie = pie;
        setAttackable(true);
//        this.setAction("feed", new ArrayList<>(){{add(new Event(VerbGroup.feed, "Im hungry"));}});


    }




    //Getters & Setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getEnemyClass() {
        return enemyClass;
    }

    public String feed(Pie pie) {
        if(this.pie.equals(pie.getName())) {
            return getName() + " loved it. Ate it in one bite.";
        } else {
            return "thats not what " + getName() + " wants";
        }
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
