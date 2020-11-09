package culminating;

import java.util.ArrayList;

public class Character {

    //Fields
    protected ArrayList<String> moveSet = new ArrayList<>();
    protected int level, health, strength, defence, speed, exp, mp, currentHealth, currentMp, dices, money;
    protected String name, weapon, armor;

    //Constructor
    public Character(String n, int l, int h, int m, int s, int d, int sp, int ep, int di, int mo) {
        name = n;
        level = l;
        health = h;
        mp = m;
        strength = s;
        defence = d;
        speed = sp;
        exp = ep;
        dices = di;
        currentHealth = health;
        currentMp = mp;
        money = mo;
    }
    
    //Accessors
    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }
    
    public int getMp() {
        return mp;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getExp() {
        return exp;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    
    public int getCurrentMp()
    {
        return currentMp;
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

    public int getMoney()
    {
        return money;
    }

    public ArrayList<ArrayList<String>> getInventory() {
        return null;
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

    public void setDefence(int d) {
        defence = d;
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

    public void setCurrentHealth(int h) {
        currentHealth = h;
    }

    public void setCurrentMp(int m)
    {
        currentMp = m;
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
    
    /**************************
     * gainExpMoney
     * This method is a way to distubute the exp
     * @param entity 
     **************************/
    public void gainExpMoney(Character entity)
    {
        //gives exp based on enemies level and exp hold
        exp += (entity.getExp() * entity.getLevel());
        money += (entity.getLevel() * entity.getMoney());
    }

    public void checkLvl()
    {

    }//end of checkLvl
    
}//end of class
