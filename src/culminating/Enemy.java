package culminating;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    //Constructor
    public Enemy(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice) {
        super(name, level, hp, mp, str, def, spd, exp, dice);
//        super.distributeStats();
    }

    //Accessors

    //Mutators

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     **************************/
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        Random random = new Random();

        int prompt, diceRoll, damage;

        prompt = random.nextInt(2) + 1;

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
                System.out.println("\n" + name + " uses a special!");
                Thread.sleep(1000);
                break;

        }

        return entity.health == 0;

    }

}
