package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    //Fields
    private final ArrayList<ArrayList<String>> inventory = new ArrayList<>();
    private final Scanner keyInput = new Scanner(System.in);
    private final Scanner scanN = new Scanner(System.in);

    //Constructor
    public Player(String noName, int level, int hp, int mp, int str, int def, int spd, int exp, int dice) {
        super(noName, level, hp, mp, str, def, spd, exp, dice);
//        distributeStats();
        weapon = "\uD83E\uDD1B Fists";
        armor = "\uD83D\uDC55 Torn Shirt";
        moveSet.add("Wrath Strike");
    }

    //Accessors

    //Mutators

//    public void distributeStats() {
//        if (level == 1) {
//            moveSet.add("Wrath Strike");
//            super.distributeStats();
//        }
//    }

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     **************************/
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        //Variables in fight
        int prompt, damage
        
        System.out.println("What will " + name + " do?");
        System.out.println("1) Attack");
        System.out.println("2) Special");
        System.out.println("3) Run");
        prompt = Integer.parseInt(keyInput.nextLine());

        switch (prompt) {
            //attack
            case 1:
                damage = attack(getDices());
                
                entity.health -= damage;
                
                if (entity.health > 0)
                    entity.health = 0;
                
                System.out.println("\n" + name + " attacks!");
                Thread.sleep(1000);
                System.out.println(name + " deals " + damage + " damage!");
                Thread.sleep(1000);
                break;
            //sepcial
            case 2:
                System.out.println();
                for (int i=0; i<moveSet.size(); i++) {
                    System.out.println(i+1 + ") " + moveSet.get(i));
                }
                prompt = Integer.parseInt(keyInput.nextLine());
                damage = 6;
                entity.health -= damage;
                System.out.println("\n" + name + " used Wrath Strike!");
                Thread.sleep(1000);
                System.out.println(name + " deals " + damage + " damage!");
                Thread.sleep(1000);
                break;
            //running
            case 3:
                System.out.println("Got away safely!");
                break;

        }

        return entity.health == 0;

    }//end of fight
    
    /**
     * attack 
     * This method will show the user their available attacks
     * @param diceTotal - how many dices the character has
     * @return - returns damage output
     */
    @Override
    public int attack(int diceTotal)
    {
        Random random = new Random();
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
        choice = scanN.nextInt()

        return dices.get(choice-1);
    }//end of attack
    
    
    
    /*************************
     * Method Name: checkInventory
     * Method Description: Display the player's inventory
     **************************/
    public void checkInventory(ArrayList<Character> partyMembers) {

        char prompt;

        //Print inventory
        System.out.println();
        for (int i=0; i<partyMembers.size(); i++) {
            System.out.println(partyMembers.get(i).getName());
            System.out.println("Weapon: " + partyMembers.get(i).getWeapon());
            System.out.println("Armor: " + partyMembers.get(i).getArmor());
            System.out.println();
        }

        for (int i=0; i<inventory.size(); i++) {
            System.out.println(inventory.get(i).get(0) + ": " + inventory.get(i).size());
        }

        System.out.println("Type 'i' to exit.");
        prompt = keyInput.nextLine().charAt(0);

    }//end of checkInventory

    /*************************
     * Method Name: addInventory
     * Method Description: Adds a new item to the player's inventory
     **************************/
    public void addInventory(String item) {

        boolean newItem = true;

        for (int i=0; i<inventory.size(); i++) {
            if (inventory.get(i).contains(item)) {
                inventory.get(i).add(item);
                newItem = false;
            }
        }

        if (newItem) {
            inventory.add(new ArrayList<>());
            inventory.get(inventory.size()-1).add(item);
        }

    }//end of addInventory
   
}//end of class
