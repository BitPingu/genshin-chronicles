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
    private int counter, specialAtk, specialDef;
    private boolean special = false, state;

    //Constructor
    public Player(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money,
                  int cHealth, int cMp, String weapon, String armor, boolean inCurrentParty) {
        super(name, level, hp, mp, str, def, spd, exp, dice, money, cHealth, cMp, weapon, armor, inCurrentParty);
        state = true;
        checkSpecialMoves();
    }

    //Accessors
    public ArrayList<ArrayList<String>> getInventory() {
        return inventory;
    }

    public boolean getSpecial()
    {
        return special;
    }
    public int getSpecialAtk()
    {
        return specialAtk;
    }
    public int getSpecialDef()
    {
        return specialDef;
    }
    
    //Mutators
    public void setInventory (ArrayList<ArrayList<String>> i) 
    {
        inventory = i;
    }

    public void setCounter(int count)
    {
        counter = count;
    }

    public void setSpecial(boolean sp)
    {
        special = sp;
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
        
        //special counter for Combat Rally
        if (special)
        {
            counter--;
            if (counter == 0)
            {
                special = false;
                for (int i = 0; i < partyMembers.size(); i++)
                {
                    partyMembers.get(i).strength -= specialAtk;
                    partyMembers.get(i).defence -= specialDef; 

                }
                
                System.out.println("Everyone is back to normal");
            }
        }
        
        do
        {
            clearScreen();

            //Print Enemy battle info
            System.out.println("Enemy:");
            System.out.println(entity.getName());
            System.out.println("HP: " + entity.getCurrentHealth() + "/" + entity.getHealth());
            System.out.println("MP: " + entity.getCurrentMp() + "/" + entity.getMp());

            //Print Party Members' battle info
            System.out.println("\nTeam:");
            for (int k = 0; k < partyMembers.size(); k++) {
                System.out.format("%-15s", partyMembers.get(k).getName());
            }
            System.out.println();
            for (int k = 0; k < partyMembers.size(); k++) {
                System.out.format("%-15s", "HP: " + partyMembers.get(k).getCurrentHealth() + "/"
                        + partyMembers.get(k).getHealth());
            }
            System.out.println();
            for (int k = 0; k < partyMembers.size(); k++) {
                System.out.format("%-15s", "MP: " + partyMembers.get(k).getCurrentMp() + "/"
                        + partyMembers.get(k).getMp());
            }
            System.out.println("\n");
            
            //displays user choice
            System.out.println("What will " + name + " do?");
            System.out.println("1) Attack");
            System.out.println("2) Special");
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
                        switch(useSpecialMoves())
                        {
                            //wrath Strike
                            case "Wrath Strike":
                                damage = ((level * 20) + strength) - entity.defence;

                                if (damage < 0)
                                {
                                    damage = 0;
                                }
                        
                                entity.currentHealth -= (damage);
                                System.out.println("\n" + name + " used Wrath Strike!");
                                Thread.sleep(1000);
                                System.out.println(name + " deals " + damage + " damage!");
                                Thread.sleep(1000);

                                //makes sure that the enemy does not go below 0
                                if (entity.currentHealth < 0)
                                    entity.currentHealth = 0;  
                                flag = true;
                                break;
                                
                            //combat rally
                            case "Combat Rally":
                                special = true;
                                counter = 3;
                                specialAtk = (strength / 4);
                                System.out.println(specialAtk);
                                specialDef = (defence / 4);
                                System.out.println(specialDef);
                                
                                //gives stat buff to oeveryone in party
                                System.out.println("\n" + name + " used Combat Rally!");
                                Thread.sleep(1000);
                                for (int i = 0; i < partyMembers.size(); i++)
                                {
                                    System.out.println(partyMembers.get(i).name + "'s Strength and Defense Increased.");
                                    Thread.sleep(1000);
                                    partyMembers.get(i).strength += specialAtk;
                                    partyMembers.get(i).defence += specialDef; 
                                    flag = true;
                                }
                                break;
                                
                            //error handle - there should be nothing here
                            default:
                                break;
                        }                  
                    break;
                
                //Error handleing
                default:
                    System.out.println("Please enter a command.");
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
                System.out.println("DICE [" + (i + 1) + "]: " + dice.get(i));
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
                    System.out.println("Please input a dice.");
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
                System.out.println("Please input a dice.");
            }
        } while (true);
    }//end of attack
    
    @Override
    public String useSpecialMoves() throws InterruptedException
    {
        //declaring local variabels
        int choice;
        
        //error handle
        do 
        {
            clearScreen();
            for (int i = 0; i < moveSet.size(); i++)
            {
                System.out.println((i +1) +") " + moveSet.get(i) + " - " + ((i + 1) * 10));
            }
            System.out.println("0) Back");
            System.out.println("What special do you want to use?");
            System.out.print("special: ");
            
            //if user uses a number
            if (scanN.hasNextInt()) 
            {
                choice = scanN.nextInt();
                //if user tried to pick a nonExistant dice
                if (choice > (moveSet.size()) && choice != 0) 
                {
                    clearScreen();
                    System.out.println("Please input a Special.");
                    Thread.sleep(1000);
                }
                //player picks dice
                else
                {
                    switch(choice)
                    {
                        case 0:
                            clearScreen();
                            return "";
                        case 1:
                            if ((currentMp - 10) >= 0)
                            {
                                currentMp -= 10;
                                return moveSet.get(choice - 1);
                            }
                            else
                            {
                                clearScreen();
                                System.out.println("You don't have enough mp.");
                                Thread.sleep(1000);
                                break;
                            }
                            
                        case 2:
                            if ((currentMp - 20) >= 0 && !special)
                            {
                                currentMp -= 15;
                                return moveSet.get(choice - 1);
                            }
                            else if(special)
                            {
                                clearScreen();
                                System.out.println("You already have a buff.");
                                Thread.sleep(1000);
                            }
                            else
                            {
                                clearScreen();
                                System.out.println("You don't have enough mp.");
                                Thread.sleep(1000);
                                break;
                            }
                    } 

                }
            }
            //if a user picks anything but a number
            else
            {
                scanN.nextLine();
                System.out.println("Please input a special.");
            }
        } while (true);
    }//end of useSpecialMove
     
    /*************************
     * Method Name: printInventory
     * Method Description: Display the player's inventory
     **************************/
    public void printInventory() 
    {
        //Prints out what the user owns (sorted)
        System.out.println(name + "'s Inventory\n");
        for (int i=0; i<inventory.size(); i++) {
            System.out.println(inventory.get(i).get(0) + ": " + inventory.get(i).size());
        }

        System.out.println("\nType anything to exit.");
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
            clearScreen();
            System.out.println("Level up! " + getName());
            setExp(0);
            
            //shows new level
            System.out.print("Lvl: " + level + " -> ");
            level += 1;
            System.out.println(level);
            
            //shows new heatlh
            System.out.print("HP: " + health + " -> ");
            health += random.nextInt(125 - 75) + 75; //  75 - 125
            System.out.println(health);
            currentHealth = health;
            
            //shows new MP
            System.out.print("MP: " + mp + " -> ");
            mp += random.nextInt(15 - 8) + 8; // 8-15
            System.out.println(mp);
            currentMp = mp;
            
            //shows new strength
            System.out.print("Atk: " + strength + " -> ");
            strength += random.nextInt(25 -18) + 18; //18-25
            System.out.println(strength);
            
            //shows new defence
            System.out.print("Def: " + defence + " -> ");
            defence += random.nextInt(29 - 17) + 17; //17-29
            System.out.println(defence);
            
            //shows new speed
            System.out.print("Spd: " + speed + " -> ");
            speed += random.nextInt(55 - 22) + 22; //22 - 55
            System.out.println(speed);
            
            //shows new dices, dice max for user, 9
            if ((level % 2) == 0 && dices != 9)
            {
                System.out.print("Dices: " + dices + " -> ");
                dices++;
                System.out.println(dices);  
            }
            else
                System.out.println("Dices: " + dices + " -> " + dices);
            
            checkSpecialMoves();
        }
    }//end of checkLvl
    
    /**
     * specialMove
     * This method is a way to show when part members and players will gain a special move(and possibly enemies)
     */
    @Override
    public void checkSpecialMoves()
    {
        //makes sure that the user has WraithStrike
            if (!moveSet.contains("Wrath Strike"))
            {
                moveSet.add("Wrath Strike");
            }

            //users second special
            if (level >= 8 && !moveSet.contains("Combat Rally"))
            {
                moveSet.add("Combat Rally");
            }
    }//end of checkSpecialMoves
    
    
 
}//end of class
