package culminating;

public class Character {

    //Fields
    protected int health, strength, defense, speed;
    protected String weapon, armor;

    //Constructor
    public Character() {

    }

    //Accessors
    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    //Mutators
    public void setHealth(int h) {
        health = h;
    }

    public void setStrength(int s) {
        strength = s;
    }

    public void setDefense(int d) {
        defense = d;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public void setWeapon(String w) {
        weapon = w;
    }

    public void setArmor(String a) {
        armor = a;
    }

    public void navigate(int x, int y) {

    }

}
