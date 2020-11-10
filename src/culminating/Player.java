package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    //Fields
    private ArrayList<ArrayList<String>> inventory = new ArrayList<>();
    private final Scanner keyInput = new Scanner(System.in);
    private final Scanner scanN = new Scanner(System.in);
    private final Random random = new Random();

    //Constructor
    public Player(String noName, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money) {
        super(noName, level, hp, mp, str, def, spd, exp, dice, money);
        weapon = "\uD83E\uDD1B Fists";
        armor = "\uD83D\uDC55 Torn Shirt";
        moveSet.add("Wrath Strike");
    }

    //Accessors
    public ArrayList<ArrayList<String>> getInventory() {
        return inventory;
    }

    //Mutators
    public void setInventory (ArrayList<ArrayList<String>> i) {
        inventory = i;
    }

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy.
     **************************/
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        //Variables in fight
        int damage;
        String prompt;
        boolean flag = false;
        
        do
        {
            //displays user choice
            System.out.println("What will " + name + " do?");
            System.out.println("1) Attack");
            System.out.println("2) Special");
            System.out.println("3) Run");
            prompt = keyInput.nextLine();

            switch (prompt) 
            {
                //attack
                case "1":
                    damage = (attack(getDices()) + getStrength()) - entity.defence;
                    
                    if (damage < 0) 
                    {
                        damage = 0;
                    }
                    
                    entity.currentHealth -= (damage);

                    //makes sure that the enemy does not go below 0
                    if (entity.currentHealth < 0)
                        entity.currentHealth = 0;

                    System.out.println("\n" + name + " attacks!");
                    Thread.sleep(1000);
                    System.out.println(name + " deals " + damage + " damage!");
                    Thread.sleep(1000);
                    
                    flag = true;
                    break;
                //special
                case "2":
                    if ((currentMp - 5) > 0)
                    {
                        currentMp -= 5;
                        for (int i=0; i<moveSet.size(); i++) {
                            System.out.println(i+1 + ") " + moveSet.get(i));
                        }
                        prompt = keyInput.nextLine();
                        
                        damage = 1000 - entity.defence;//dont forget to change back to a reasonable number
                        
                        entity.currentHealth -= (damage);
                        System.out.println("\n" + name + " used Wrath Strike!");
                        Thread.sleep(1000);
                        System.out.println(name + " deals " + damage + " damage!");
                        Thread.sleep(1000);
                        
                        //makes sure that the enemy does not go below 0
                        if (entity.currentHealth < 0)
                        entity.currentHealth = 0;  
                        flag = true;
                    }
                    else
                    {
                        System.out.println("You are out of mp, you cant use your special");
                    }
                    System.out.println("You dont have enough mp");
                    
                    break;
                //running
                case "3":
                    if (speed > (random.nextInt(entity.speed) + 2)) 
                    {
                        return true; 
                    }
                    else
                    {
                        System.out.println("You tripped while trying to run");
                        flag = true;
                    }
                
                //Error handleing
                default:
                    System.out.println("Please enter a command");
                    break;

            }
        } while (!flag);

        return entity.currentHealth == 0;
    }//end of fight
    
    /************************
     * attack 
     * This method will show the user their available attacks
     * @param diceTotal - how many dices the character has
     * @return - returns damage output
     ************************/
    @Override
    public int attack(int diceTotal) throws InterruptedException
    {
        //declaring local variables
        int choice;
        ArrayList<Integer> dice = new ArrayList<>();
        
        //error handle
        do 
        {
            //shows how many dices the player can use
            for (int i = 0; i < diceTotal; i++)
            {
                dice.add(random.nextInt(8) + 1);
                System.out.println("DICE[" + (i + 1) + "]: " + dice.get(i));
            }
            System.out.println("What dice do you want to use?");
            System.out.print("Dice: ");
            //if user uses a number
            if (scanN.hasNextInt()) 
            {
                choice = scanN.nextInt();
                //if user tried to pick a nonExistant dice
                if (choice > dice.size()) 
                {
                    System.out.println("Please input a dice");
                }
                //player picks dice
                else
                {
                    return dice.get(choice-1);
                }
            }
            //if a user picks anything but a number
            else
            {
                scanN.nextLine();
                System.out.println("Please input a dice");
            }
        } while (true);
    }//end of attack
    
    /*************************
     * Method Name: printInventory
     * Method Description: Display the player's inventory
     **************************/
    public void printInventory(ArrayList<Character> partyMembers) {
        
        //Prints the party members and what they have
        for (int i=0; i<partyMembers.size(); i++) {
            System.out.println(partyMembers.get(i).getName());
            System.out.println("Lvl: " + partyMembers.get(i).getLevel());
            System.out.println("HP: " + partyMembers.get(i).getHealth());
            System.out.println("MP: " + partyMembers.get(i).getMp());
            System.out.println("Atk: " + partyMembers.get(i).getStrength());
            System.out.println("Def: " + partyMembers.get(i).getDefence());
            System.out.println("Spd: " + partyMembers.get(i).getSpeed());
            System.out.println("Exp: " + (partyMembers.get(i).getLevel()*20) + " / " +partyMembers.get(i).getExp());
            System.out.println("Dices: " + partyMembers.get(i).getDices());
            System.out.println("Weapon: " + partyMembers.get(i).getWeapon());
            System.out.println("Armor: " + partyMembers.get(i).getArmor());
            System.out.println();
        }
        
        //Prints out how much money you have
        System.out.println("\nMoney: " + money +"\n");

        //Prints out what the user owns (sorted)
        for (int i=0; i<inventory.size(); i++) {
            System.out.println(inventory.get(i).get(0) + ": " + inventory.get(i).size());
        }

        System.out.println("Type anything to exit.");
        keyInput.nextLine();

    }//end of printInventory

    /*************************
     * Method Name: sortInventory
     * Method Description: Sort the player's inventory using selection sort.
     **************************/
    public void sortInventory(ArrayList<ArrayList<String>> inventory) {

        int minIndex;
        ArrayList<String> minValue;
        String[] tokens1, tokens2;

        for (int i=0; i<inventory.size(); i++) {

            minIndex = i;
            minValue = inventory.get(i);
            tokens1 = inventory.get(i).get(0).split(" ");

            for (int j=i+1; j<inventory.size(); j++) {

                tokens2 = inventory.get(j).get(0).split(" ");
                if (tokens2[1].compareTo(tokens1[1]) < 0) {
                    minIndex = j;
                    minValue = inventory.get(j);
                }

            }

            inventory.set(minIndex, inventory.get(i));
            inventory.set(i, minValue);

        }

    }//end of sortInventory

    /*************************
     * Method Name: checkInventory
     * Method Description: Check the player's inventory to find a certain item and amount using binary search.
     **************************/
    public boolean checkInventory(ArrayList<ArrayList<String>> inventory, String item, int amount) throws InterruptedException {

        int first = 0, middle = 0, last = inventory.size()-1;
        boolean found = false;
        String[] tokens1 = item.split(" ");
        String[] tokens2;

        while (!found && first <= last) {

            middle = (first + last) / 2;
            tokens2 = inventory.get(middle).get(0).split(" ");

            if (tokens2[1].equals(tokens1[1])) {
                found = true;
            } else if (tokens2[1].compareTo(tokens1[1]) < 0) {
                first = middle + 1;
            } else if (tokens2[1].compareTo(tokens1[1]) > 0) {
                last = middle - 1;
            }

        }

        if (!found) {
            System.out.println("You don't have any " + item + ".");
            Thread.sleep(1000);
        } else if (inventory.get(middle).size() < amount) {
            System.out.println("You don't have enough " + item + ".");
            Thread.sleep(1000);
            found = false;
        }

        return found;

    }//end of checkInventory

    /*************************
     * Method Name: addInventory
     * Method Description: Adds a new item to the player's inventory.
     **************************/
    public void addInventory(String item) {

        boolean newItem = true;
        
        //Check if item exists in inventory
        for (int i=0; i<inventory.size(); i++) {

            if (inventory.get(i).contains(item)) {
                inventory.get(i).add(item);
                newItem = false;
            }

        }

        //Add new item to inventory
        if (newItem) {
            inventory.add(new ArrayList<>());
            inventory.get(inventory.size()-1).add(item);
        }

        //Sorts inventory
        sortInventory(inventory);

    }//end of addInventory

    /*************************
     * Method Name: removeInventory
     * Method Description: Removes an item from the player's inventory.
     **************************/
    public void removeInventory(String item, int amount) {

        //Look for item in inventory
        for (int i=0; i<inventory.size(); i++) {

            if (inventory.get(i).contains(item)) {

                if (inventory.get(i).size() - amount == 0) {
                    //If item is exact amount, remove entire row
                    inventory.remove(i);
                    break;
                } else {
                    for (int j = 0; j < amount; j++) {
                        inventory.get(i).remove(item);
                    }
                }

            }

        }

    }//end of removeInventory
    
    /*************************
     * checkLvl
     * THis method will check if the user can level up or not
     *************************/
    @Override
    public void checkLvl()
    {
        //level up based on level * 20
        if (getExp() >= (getLevel() * 20))
        {
            System.out.println("Level up! " + getName());
            setExp(0);
            
            //shows new level
            System.out.print("Lvl: " + level + " -> ");
            level += 1;
            System.out.println(level);
            
            //shows new heatlh
            System.out.print("HP: " + health + " -> ");
            health += random.nextInt(15) + 5;
            System.out.println(health);
            currentHealth = health;
            
            //shows new MP
            System.out.print("MP: " + mp + " -> ");
            mp += random.nextInt(6) + 2;
            System.out.println(mp);
            currentMp = mp;
            
            //shows new strength
            System.out.print("Atk: " + strength + " -> ");
            strength += random.nextInt(17) + 5;
            System.out.println(strength);
            
            //shows new defence
            System.out.print("Def: " + defence + " -> ");
            defence += random.nextInt(15) + 3;
            System.out.println(defence);
            
            //shows new speed
            System.out.print("Spd: " + speed + " -> ");
            speed += random.nextInt(8) + 2;
            System.out.println(speed);
            
            //shows new dices
            if ((level % 2) == 0 && dices != 6)
            {
                System.out.print("Dices: " + dices + " -> ");
                dices++;
                System.out.println(dices);  
            }
            else
                System.out.println("Dices: " + dices + " -> " + dices);

        }

    }//end of checkLvl
 
}//end of class
