package culminating;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    //Constructors

    public Enemy(String name, int level, String weapon, String armor) {
        super(name, level, weapon, armor);

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
                this.speed = (level * 3) + 3;
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

            //Dragon
            case "\uD83D\uDC32 Dragon":
                this.health = (level * 100) + 200;
                this.mp = (level * 25) + 20;
                this.strength = (level * 25) + 10;
                this.defence = (level * 15) + 5;
                this.speed = (level * 100) + 2;
                this.exp = (level * 100) + 10;
                this.money = (level * 100) + 15;

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
     * @param partyMembers - current party members
     * @param entity - user/players
     * @return 
     * @throws java.lang.InterruptedException 
     **************************/
    @Override
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        Random random = new Random();

        int damage, target;

        switch (random.nextInt(5) + 1) {

            //enemy attack - will attack one person at a time
            case 1:     
            case 2:
            case 3:
            case 4:
                //targets which member you have
                target = random.nextInt(partyMembers.size());
                
                //damage calculation
                damage = (strength + random.nextInt(8) + 1 
                        - partyMembers.get(target).defence);
                
                //damage cant go tonegative(else they will heal)
                if (damage < 0) 
                {
                    damage = 0;
                }
                
                //damages the target
                partyMembers.get(target).currentHealth -= (damage);
                
                //makes sure the target is not negative hp
                if (partyMembers.get(target).currentHealth < 0)
                {
                    System.out.println(partyMembers.get(target).getName() + " has fallen!");
                    partyMembers.get(target).currentHealth = 0;
                }
                
                System.out.println("\n" + name + " is attacking " 
                        + partyMembers.get(target).name + ".");
                Thread.sleep(1000);
                System.out.println(name + " dealt " + damage + " damage!");
                Thread.sleep(1000);
                break;

            //enemy special - attacks everyone
            case 5:
                if ((currentMp - 5) >= 0) 
                {
                    currentMp -= 5;
                    System.out.println("\n" + name + " uses a special!");
                    Thread.sleep(1000);

                    //damages everyone
                    for (int i = 0; i < partyMembers.size(); i++)
                    {
                        damage = ((level * 20) + strength) - partyMembers.get(i).defence;

                        if (damage < 0) 
                        {
                            damage = 0;
                        }

                        partyMembers.get(i).currentHealth -= (damage);
                        System.out.println(name + " dealt " + damage + " damage to " 
                                + partyMembers.get(i).getName() + "!");

                        //fallen message
                        if (partyMembers.get(i).currentHealth <= 0)
                        {
                            System.out.println(partyMembers.get(i).getName() + " has fallen!");
                            partyMembers.get(i).currentHealth = 0;
                        }
                        Thread.sleep(1000);
                    }
                }
                
                
                break;

        }

        return entity.currentHealth == 0;

    }//end of fight

}//end of enemy
 