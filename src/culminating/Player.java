package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    //Fields
    private final ArrayList<ArrayList<String>> inventory = new ArrayList<>();
    private final Scanner keyInput = new Scanner(System.in);

    //Constructor
    public Player(String noName, int level) {
        super(noName, level);
        distributeStats();
        weapon = "\uD83E\uDD1B Fists";
        armor = "\uD83D\uDC55 Torn Shirt";
    }

    //Accessors

    //Mutators

    public void distributeStats() {
        if (level == 1) {
            moveSet.add("Wrath Strike");
            super.distributeStats();
        }
    }

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     **************************/
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        Random random = new Random();

        //Variables in fight
        int prompt, diceRoll, damage = 0;

        System.out.println("What will " + name + " do?");
        System.out.println("1) Attack");
        System.out.println("2) Special");
        System.out.println("3) Run");
        prompt = Integer.parseInt(keyInput.nextLine());

        switch (prompt) {
            case 1:
                diceRoll = random.nextInt(6) + 1;
                for (int i=0; i<diceRoll; i++) {
                    if (entity.health == 0) {
                        break;
                    }
                    entity.health--;
                    damage++;
                }
                System.out.println("\n" + name + " attacks!");
                Thread.sleep(1000);
                System.out.println(name + " deals " + damage + " damage!");
                Thread.sleep(1000);
                break;

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

            case 3:
                System.out.println("Got away safely!");
                break;

        }

        return entity.health == 0;

    }//end of fight
    
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
