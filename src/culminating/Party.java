package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Party extends Character {

    //Fields
    private final Scanner keyInput = new Scanner(System.in);

    //Constructor
    public Party(String name, int level) {
        super(name, level);
        distributeStats();
        weapon = "\uD83E\uDD4D Wooden Staff";
        armor = "\uD83D\uDC57 Leather Dress";
    }

    //Accessors

    //Mutators

    public void distributeStats() {
        if (level == 1) {
            moveSet.add("Physic");
            super.distributeStats();
        }
    }

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy.
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
                damage = 0;
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

    }

}
