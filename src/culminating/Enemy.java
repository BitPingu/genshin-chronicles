package culminating;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    //Constructor
    public Enemy(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money) 
    {
        //distrobutes stats using character(only used for name and level)
        super(name, level, hp, mp, str, def, spd, exp, dice, money);
        
        //distrobutes stats based on what enemy it is
        switch(name)
        {
            //zombie
            case "\uD83E\uDDDF Zombie":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;
                break;

            //Goblin
            case "\uD83D\uDC7A Goblin":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;

            //Ogre
            case "\uD83D\uDC79 Ogre":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;

            //Ghost
            case "\uD83D\uDC7B Ghost":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;

            //Alien
            case "\uD83D\uDC7D Alien":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;

            //Octopus
            case "\uD83D\uDC19 Octopus":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;

            //Skeleton
            case "\uD83D\uDC80 Skeleton":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;
                
            //Golem
            case "\uD83E\uDD16 Golem":
                this.health = 78;
                this.mp = 25;
                this.strength = 10;
                this.defence = 5;
                this.speed = 5;
                this.exp = 10;
                this.money = 10;

                break;
        }
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
                damage = strength + random.nextInt(10) + 1;
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

        return entity.currentHealth == 0;

    }//end of fight

}//end of enemy
