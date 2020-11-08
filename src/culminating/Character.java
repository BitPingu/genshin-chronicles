package culminating;

import java.util.ArrayList;

public class Character {

    //Fields
    protected ArrayList<String> moveSet = new ArrayList<>();
    protected int level, health, strength, defense, speed, exp, mp, maxHealth, dices;
    protected String name, weapon, armor;

    //Constructor
    public Character(String n, int l, int h, int m, int s, int d, int sp, int ep, int di) {
        name = n;
        level = l;
        health = h;
        mp = m;
        strength = s;
        defense = d;
        speed = sp;
        exp = ep;
        dices = di;
        maxHealth = health;
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

    public int getDices() {
        return dices;
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

    public void setDices(int di) {
        dices = di;
    }

    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {
        return false;
    }

    public int attack(int diceTotal) {
        return 0;
    }
    
    public void checkInventory(ArrayList<Character> partyMembers) {

    }

    public void addInventory(String item) {

    }

}//end of class
