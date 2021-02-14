package com.sourdoughsoftware.gamepieces;

/**
 * The Pie class extends the Item class.
 * Additional variables are added to a weapon such as attackPoints and victory.
 *
 */
public class Pie extends Item{
    private final int attackPoints;
    private final String victory;

    public Pie(String name, String description, int attackPoints, String victory) {
        super(name, description);
        setGrabable(true);
        this.attackPoints = attackPoints;
        this.victory = victory;
        setWieldable(true);
        setDropable(true);
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
