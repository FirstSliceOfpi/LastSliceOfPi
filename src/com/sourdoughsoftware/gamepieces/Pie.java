package com.sourdoughsoftware.gamepieces;

/**
 * The Pie class extends the Item class.
 * Additional variables are added to a weapon such as attackPoints and victory.
 *
 */
public class Pie extends Item {
    private int attackPoints;
    private final String victory;

    /**
     * Constructor automatically sets grabable,wieldable, and dropable to true.
     * Provides a victory string in the event of defeating an enemy with this Pie.
     * @param name
     * @param description
     * @param attackPoints
     * @param victory
     */
    public Pie(String name, String description, int attackPoints, String victory) {
        super(name, description);
        setGrabable(true);
        this.attackPoints = attackPoints;
        this.victory = victory;
        setWieldable(true);
        setDropable(true);
    }

    /**
     *  return the attackPoints assigned to this weapon
     */
    public int getAttackPoints() {
        return this.attackPoints;
    }

    public void setAttackPoints(int i) {
        attackPoints = i;
    }

    /**
     * return the victory phrase when beating an enemy with this weapon
     * @return
     */
    public String getVictory() {
        return this.victory;
    }

    public String toString() {
        return super.toString();
    }
}
