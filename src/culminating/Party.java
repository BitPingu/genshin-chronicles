package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Party extends Character {

    //Fields
    private final Scanner keyInput = new Scanner(System.in);
    private final Scanner scanN = new Scanner(System.in);
    
    //Constructor
    public Party(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice) {
        super(name, level, hp, mp, str, def, spd, exp, dice);
//        distributeStats();
        weapon = "\uD83E\uDD4D Wooden Staff";
        armor = "\uD83D\uDC57 Leather Dress";
        moveSet.add("Physic");
    }

    //Accessors

    //Mutators

//    public void distributeStats() {
//        if (level == 1) {
//            moveSet.add("Physic");
//            super.distributeStats();
//        }
//    }
    
    

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy.
     **************************/
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        //Variables in fight
        int prompt, damage;

        System.out.println("What will " + name + " do?");
        System.out.println("1) Attack");
        System.out.println("2) Special");
        System.out.println("3) Run");
        prompt = Integer.parseInt(keyInput.nextLine());

        switch (prompt) {
            case 1:
                damage = attack(getDices());
                
                entity.health -= damage;
                
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
                for (int i=0; i<5; i++) {
                    if (partyMembers.get(0).health == partyMembers.get(0).maxHealth) {
                        break;
                    }
                    partyMembers.get(0).health++;
                    damage++;
                }
                System.out.println("\n" + name + " used Physic!");
                Thread.sleep(1000);
                System.out.println(name + " restored " + damage + " health to " + partyMembers.get(0).name + ".");
                Thread.sleep(1000);
                break;

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
        
        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 0; i < diceTotal; i++)
        {
            dice.add(random.nextInt(6) + 1);
            System.out.println("DICE[" + (i + 1) + "]: "+dice.get(i));
        }
        System.out.println("What dice do you want to use?");
        
        choice = scanN.nextInt();

        return dice.get(choice-1);
    }//end of attack

}
