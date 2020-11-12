package culminating;

import java.util.ArrayList;

public class Character {

    //Fields
    protected ArrayList<ArrayList<String>> inventory = new ArrayList<>();
    protected ArrayList<String> moveSet = new ArrayList<>();
    protected int level, health, strength, defence, speed, exp, mp, currentHealth, currentMp, dices, money, specialDef,
            specialAtk;
    protected String name, weapon, armor;
    protected boolean state, inCurrentParty;

    //Constructor
    public Character(String n, int l, int h, int m, int s, int d, int sp, int ep, int di, int mo, int ch, int cm,
                     String w, String a, boolean ic) {
        name = n;
        level = l;
        health = h;
        mp = m;
        strength = s;
        defence = d;
        speed = sp;
        exp = ep;
        dices = di;
        money = mo;
        currentHealth = ch;
        currentMp = cm;
        weapon = w;
        armor = a;
        inCurrentParty = ic;
        state = true;
        calcEquipment(w, a);
    }

    public Character(String n, int l, String w, String a) {
        name = n;
        level = l;
        weapon = w;
        armor = a;
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
    
    public boolean getSpecial()
    {
        return false;
    }
    
    public int getSpecialAtk()
    {
        return specialAtk;
    }

    public int getSpecialDef()
    {
        return specialDef;
    }
    public boolean getState()
    {
        return state;
    }

    public boolean getInCurrentParty() {
        return inCurrentParty;
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
    
    public void setState(boolean s) {
        state = s;
    }

    public void setMoney(int m) {
        money = m;
    }

    public void setInventory(ArrayList<ArrayList<String>> i) {
        inventory = i;
    }
    
    public void setCounter(int count)
    {
    }

    public void setSpecial(boolean sp)
    {
    }
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {
        return false;
    }

    public int attack(int diceTotal) throws InterruptedException
    {
        return 0;
    }

    public void printInventory() {

    }

    public boolean checkInventory(ArrayList<ArrayList<String>> inventory, String item, int amount) throws InterruptedException {
        return false;
    }

    public void addInventory(String item) {

    }

    public void removeInventory(String item, int amount) {

    }

    public void setInCurrentParty(boolean i) {
        inCurrentParty = i;
    }

    /**************************
     * calcEquipment
     * This method calculates and adds stats based on equipment
     * @param w - weapon
     * @param a - armor
     **************************/
    public void calcEquipment (String w, String a) {
        String[] tokens1 = w.split(" ");
        strength += Integer.parseInt(tokens1[3]);
        String[] tokens2 = a.split(" ");
        defence += Integer.parseInt(tokens2[3]);
    }
    
    /**************************
     * gainExpMoney
     * This method is a way to distubute the exp
     * @param ex - exp
     * @param mo - money
     **************************/
    public void gainExpMoney(int ex, int mo)
    {
        //gives exp based on enemies level and exp hold
        exp += ex;
        money += mo;
    }

    public void checkLvl()
    {
    }//end of checkLvl
    
    /**
     * checkSpecialMoves
     * This method is a way to show when part members and players will gain a special move(and possibly enemies)
     */
    public void checkSpecialMoves()
    {
    }//end of checkSpecialMoves
    /**
     * checkSpecialMoves
     * This method is a way to show when part members and players will gain a special move(and possibly enemies)
     */
    public String useSpecialMoves() throws InterruptedException
    {
        return null;
    }//end of checkSpecialMoves
    
    public String toString() {
        return name + " " + level + " " +  health + " " +  mp + " " +  strength + " " +  defence + " " +  speed + " " +
                exp + " " +  dices + " " +  money + " " + currentHealth + " " + currentMp + " " + weapon + " " + armor +
                " " + inCurrentParty;
    }
    
    /*************************
     * Method Name: clearScreen
     * Method Description: Clear screen for world.
     **************************/
    public void clearScreen()
    {
        for (int i = 0; i < 40; i++) 
        {
            System.out.println("");    
        }
    }//end of clearScreen
    
}//end of class
