package culminating;

import java.util.ArrayList;

public class Character {

    //Fields
    protected ArrayList<String> moveSet = new ArrayList<>();
    protected int level, health, strength, defense, speed, exp, mp, maxHealth;
    protected String name, weapon, armor;

    //Constructor
    public Character(String n, int l) {
        name = n;
        level = l;
    }

    //Accessors
    public int getLevel() {
        return level;
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

    public int getExp() {
        return exp;
    }

    public int getMp() {
        return mp;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    //Mutators
    public void setLevel(int l) {
        level = l;
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

    public void setExp(int e) {
        exp = e;
    }

    public void setMp(int m) {
        mp = m;
    }

    public void setMaxHealth(int m) {
        maxHealth = m;
    }

    public void setName(String n) {
        name = n;
    }

    public void setWeapon(String w) {
        weapon = w;
    }

    public void setArmor(String a) {
        armor = a;
    }

    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {
        return false;
    }
    
    public void checkInventory(ArrayList<Character> partyMembers) {

    }

    public void addInventory(String item) {

    }

    public void distributeStats() {
        if (level == 1) {
            health = 20;
            strength = 10;
            defense = 8;
            speed = 8;
            exp = 0;
            mp = 20;
            maxHealth = health;
        }
    }

}//end of class
