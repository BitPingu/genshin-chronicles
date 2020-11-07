package culminating;

import java.util.Random;
import java.util.Scanner;

public class Party extends Character {

    private final Scanner keyInput = new Scanner(System.in);

    //Constructor
    public Party(String name, int level) {
        super(name, level);
        super.distributeStats();
    }

    //Accessors

    //Mutators

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     **************************/
    public boolean fight(Character entity) throws InterruptedException {

        Random random = new Random();

        int prompt, diceRoll, damage;

        System.out.println("\n" + name + " HP: " + health + " | " + name + " HP: " + health + " | " +
                entity.health + " HP: " + entity.health);
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
                System.out.println("\n" + name + " deals " + damage + " damage!");
                Thread.sleep(1000);
                break;

            case 2:
                break;

            case 3:
                System.out.println("Got away safely!");
                break;

        }

        return entity.health == 0;

    }

}
