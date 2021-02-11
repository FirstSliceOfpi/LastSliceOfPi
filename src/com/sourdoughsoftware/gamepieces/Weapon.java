package com.sourdoughsoftware.gamepieces;

/**
 * The Weapon class extends the Item class.
 * Additional variables are added to a weapon such as attackPoints and victory.
 *
 */
public class Weapon extends Item{
    private final int attackPoints;
    private final String victory;

    public Weapon(String name, String description, int attackPoints, String victory) {
        super(name, description);
        this.attackPoints = attackPoints;
        this.victory = victory;
        setWieldable(true);
    }

    // return the attackPoints assigned to this weapon
    public int getAttackPoints() {
        return this.attackPoints;
    }

    // return the victory phrase when beating an enemy with this weapon
    public String getVictory() {
        return this.victory;
    }

    public String toString() {
        return super.toString();
    }
}
