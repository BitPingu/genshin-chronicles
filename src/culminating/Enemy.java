package culminating;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    //Constructor
    public Enemy(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money) {
        super(name, level, hp, mp, str, def, spd, exp, dice, money);
    }

    //Accessors

    //Mutators

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     * @param partyMembers
     * @param entity
     * @return 
     * @throws java.lang.InterruptedException 
     **************************/
    @Override
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        Random random = new Random();

        int diceRoll, damage;

        switch (random.nextInt(3) + 1) {

            //enemy attack
            case 1:     
            case 2:
                damage = random.nextInt(10) + 1;
                for (int i = 0; i < partyMembers.size(); i++)
                {
                    partyMembers.get(i).currentHealth -= damage;
                    if (partyMembers.get(i).currentHealth < 0)
                    {
                        partyMembers.get(i).currentHealth = 0;
                    }
                }
                
                System.out.println("\n" + name + " attacks!");
                Thread.sleep(1000);
                System.out.println(name + " deals " + damage + " damage!");
                Thread.sleep(1000);
                break;

            //enemy special
            case 3:
                
                damage = 20;
                
                for (int i = 0; i < partyMembers.size(); i++)
                {
                    partyMembers.get(i).currentHealth -= damage;
                    if (partyMembers.get(i).currentHealth < 0)
                    {
                        partyMembers.get(i).currentHealth = 0;
                    }
                }

                System.out.println("\n" + name + " uses a special!");
                Thread.sleep(1000);
                System.out.println(name + " deals " + damage + " damage!");
                Thread.sleep(1000);
                break;

        }

        return entity.health == 0;

    }//end of fight

}//end of enemy
