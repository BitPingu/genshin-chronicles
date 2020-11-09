package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    //Fields
    private final ArrayList<ArrayList<String>> inventory = new ArrayList<>();
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
                    damage = attack(getDices()) + getStrength();
                    entity.health -= damage;

                    //makes sure that the enemy does not go below 0
                    if (entity.health < 0)
                        entity.health = 0;

                    System.out.println("\n" + name + " attacks!");
                    Thread.sleep(1000);
                    System.out.println(name + " deals " + damage + " damage!");
                    Thread.sleep(1000);
                    
                    flag = true;
                    break;
                //special
                case "2":
                    if (mp > 0)
                    {
                        System.out.println();
                        for (int i=0; i<moveSet.size(); i++) {
                            System.out.println(i+1 + ") " + moveSet.get(i));
                        }
                        prompt = keyInput.nextLine();
                        damage = 50;
                        entity.health -= damage;
                        System.out.println("\n" + name + " used Wrath Strike!");
                        Thread.sleep(1000);
                        System.out.println(name + " deals " + damage + " damage!");
                        Thread.sleep(1000);
                        flag = true;
                    }
                    System.out.println("You dont have enough mp");
                    
                    break;
                //running
                case "3":
                    System.out.println("Got away safely!");
                    
                    flag = true;
                    break;
                default:
                    System.out.println("Please enter a command");
                    break;

            }
        } while (!flag);

        return entity.health == 0;
    }//end of fight
    
    /************************
     * attack 
     * This method will show the user their available attacks
     * @param diceTotal - how many dices the character has
     * @return - returns damage output
     ************************/
    @Override
    public int attack(int diceTotal)
    {
        int choice;
        
        ArrayList<Integer> dices = new ArrayList<>();
        
        //shows how many dices the player can use
        for (int i = 0; i < diceTotal; i++)
        {
            dices.add(random.nextInt(8) + 1);
            System.out.println("DICE[" + (i + 1) + "]: "+dices.get(i));
        }
        System.out.println("What dice do you want to use?");
        System.out.print("Dice: ");
        //user input(error handle)
        choice = scanN.nextInt();

        return dices.get(choice-1);
    }//end of attack
    
    /*************************
     * Method Name: checkInventory
     * Method Description: Display the player's inventory
     **************************/
    public void checkInventory(ArrayList<Character> partyMembers) {

        //Print inventory
        System.out.println();
        
        //prints the party members and what they have
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
        
        //prints out how much money you have
        System.out.println("\nMoney: " + money +"\n");

        //prints out what the user owns
        for (int i=0; i<inventory.size(); i++) {
            System.out.println(inventory.get(i).get(0) + ": " + inventory.get(i).size());
        }

        System.out.println("Type anything to exit.");
        keyInput.nextLine();

    }//end of checkInventory

    /*************************
     * Method Name: addInventory
     * Method Description: Adds a new item to the player's inventory
     **************************/
    public void addInventory(String item) {

        boolean newItem = true;
        
        //adds to current inventory
        for (int i=0; i<inventory.size(); i++) {
            if (inventory.get(i).contains(item)) {
                inventory.get(i).add(item);
                newItem = false;
            }
        }

        //adds new item into inventory
        if (newItem) {
            inventory.add(new ArrayList<>());
            inventory.get(inventory.size()-1).add(item);
        }

    }//end of addInventory
    
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
            health += 5;
            System.out.println(health);
            
            //shows new MP
            System.out.print("MP: " + mp + " -> ");
            mp += 5;
            System.out.println(mp);
            
            //shows new strength
            System.out.print("Atk: " + strength + " -> ");
            strength += 5;
            System.out.println(strength);
            
            //shows new defence
            System.out.print("Def: " + defence + " -> ");
            defence += 5;
            System.out.println(defence);
            
            //shows new speed
            System.out.print("Spd: " + speed + " -> ");
            speed += 5;
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
