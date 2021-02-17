package com.sourdoughsoftware.gamepieces;

import com.sourdoughsoftware.GameState;

public class Enemy extends Item implements java.io.Serializable{


//    private String name;
    private String enemyClass;
    private int hp;
    private String weaponType;
    private String background;
    private String pie;
    private String deadtext;
    private boolean fed = false;
    private static int totalEnemiesAlive = 0;
    private static int totalEnemiesHungry = 0;
    private static int totalEnemies = 0;




    public Enemy(String name, String background) {
        super(name, background);
        totalEnemiesAlive++;
        totalEnemiesHungry++;

    }

    // Single ctor
    public Enemy(String name, String enemyClass, int hp, String pie, String background, String deadtext) {
        super(name, background);
//        this.name = name;
        totalEnemiesAlive++;
        totalEnemiesHungry++;
        totalEnemies++;
        
        this.enemyClass = enemyClass;
        this.hp = hp;
        this.background = background;
        this.pie = pie;
        this.deadtext = deadtext;
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

    public static int getTotalEnemiesAlive() {
        return totalEnemiesAlive;
    }

    public static int getTotalEnemiesHungry() {
        return totalEnemiesHungry;
    }

    public static int getTotalEnemies() {
        return totalEnemies;
    }

    public static void decrementEnemiesAlive() {
        totalEnemiesAlive--;
    }

    public String getEnemyClass() {
        return enemyClass;
    }

    public String getDeadtext() {
        return deadtext;
    }

    public String feed(Pie pie) {
        if(fed) {
            return getName() + " is full from the " + this.pie + " still.";
        }
        if(this.pie.equals(pie.getName())) {
            fed = true;
            totalEnemiesHungry++;
            GameState.getCookBook().addRecipe();
            return getName() + " loved it. Ate it in one bite.";
        } else {
            return "That's not what " + getName() + " wants.";
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
