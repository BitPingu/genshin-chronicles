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
                this.health = (level * 38) + 40;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;
                
                break;

            //Goblin
            case "\uD83D\uDC7A Goblin":
                this.health = (level * 25) + 33;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Ogre
            case "\uD83D\uDC79 Ogre":
                this.health = (level * 30) + 90;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Ghost
            case "\uD83D\uDC7B Ghost":
                this.health = (level * 23) + 23;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;
                
                break;

            //Alien
            case "\uD83D\uDC7D Alien":
                this.health = (level * 40) + 61;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Octopus
            case "\uD83D\uDC19 Octopus":
                this.health = (level * 22) + 22;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Skeleton
            case "\uD83D\uDC80 Skeleton":
                this.health = (level * 24) + 90;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;
                
            //Golem
            case "\uD83E\uDD16 Golem":
                this.health = (level * 65) + 120;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;
        }
        
        currentHealth = this.health;
        currentMp = this.mp;
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

        int damage;

        switch (random.nextInt(5) + 1) {

            //enemy attack
            case 1:     
            case 2:
            case 3:
            case 4:
                damage = strength + random.nextInt(8) + 1;
                for (int i = 0; i < partyMembers.size(); i++)
                {
                    partyMembers.get(i).currentHealth -= (damage - partyMembers.get(i).defence);
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
            case 5:
                
                damage = 20;
                
                for (int i = 0; i < partyMembers.size(); i++)
                {
                    partyMembers.get(i).currentHealth -= (damage - partyMembers.get(i).defence);
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
