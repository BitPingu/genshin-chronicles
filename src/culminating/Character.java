package culminating;

public class Character {

    //Fields
    protected int level, health, strength, defense, speed, ep;
    protected String name, weapon, armor;
    protected boolean tutorial;

    //Constructor
    public Character(String n, int l) {
        name = n;
        level = l;
    }

    //Accessors
    public String setName() {
        return name;
    }

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

    public boolean isTutorial() {
        return tutorial;
    }

    //Mutators
    public void setName(String s) {
        name = s;
    }
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

    public void setTutorial(boolean t) {
        tutorial = t;
    }

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     **************************/
    public boolean fight(Character entity) throws InterruptedException {
        return false;
    }

    public String navigate() throws InterruptedException {
        return null;
    }

    public void addInventory(String item) {

    }

    public void distributeStats() {
        health = 20;
        strength = 10;
        defense = 8;
        speed = 8;
    }

}
