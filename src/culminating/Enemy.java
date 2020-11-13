package culminating;
//V2
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    //Constructors
    public Enemy(String name, int level, String weapon, String armor) {
        super(name, level, weapon, armor);

        //distributes stats based on what enemy it is
        switch(name)
        {
            //zombie
            case "\uD83E\uDDDF Zombie":
                this.health = (level * 58) + 100;
                this.mp = (level * 5) + 10;
                this.strength = (level * 15) + 10;
                this.defence = (level * 8) + 10;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Goblin
            case "\uD83D\uDC7A Goblin":
                this.health = (level * 47) + 80;
                this.mp = (level * 5) + 10;
                this.strength = (level * 10) + 10;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Ogre
            case "\uD83D\uDC79 Ogre":
                this.health = (level * 30) + 120;
                this.mp = (level * 5) + 10;
                this.strength = (level * 25) + 10;
                this.defence = (level * 15) + 10;
                this.speed = (level * 3) + 3;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Ghost
            case "\uD83D\uDC7B Ghost":
                this.health = (level * 20) + 169;
                this.mp = (level * 5) + 10;
                this.strength = (level * 10) + 25;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Alien
            case "\uD83D\uDC7D Alien":
                this.health = (level * 40) + 90;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 5;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Octopus
            case "\uD83D\uDC19 Octopus":
                this.health = (level * 69) + 88;
                this.mp = (level * 5) + 10;
                this.strength = (level * 8) + 8;
                this.defence = (level * 8) + 8;
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
                this.health = (level * 65) + 150;
                this.mp = (level * 5) + 10;
                this.strength = (level * 5) + 10;
                this.defence = (level * 5) + 5;
                this.speed = (level * 3) + 2;
                this.exp = (level * 10) + 10;
                this.money = (level * 5) + 15;

                break;

            //Dragon
            case "\uD83D\uDC32 Dragon":
                this.health = (level * 200) + 1000;
                this.mp = (level * 25) + 20;
                this.strength = (level * 40) + 20;
                this.defence = (level * 15) + 10;
                this.speed = (level * 40) + 2;
                this.exp = (level * 100) + 10;
                this.money = (level * 100) + 15;

                break;
        }

        currentHealth = this.health;
        currentMp = this.mp;
    }

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
        boolean flag = false;

        do
        {
            Main.clearScreen();
            switch (random.nextInt(5) + 1) 
            {

                //enemy attack - will attack one person at a time
                case 1:     
                case 2:
                case 3:
                case 4:

                    //targets which member you have, makes sure that it targets alive members
                    do
                    {
                        target = random.nextInt(partyMembers.size());

                        //damage calculation
                        damage = (strength + random.nextInt(40-15) + 15 
                                - partyMembers.get(target).defence);   
                    } while (!partyMembers.get(target).getState());

                    //damage cant go tonegative(else they will heal)
                    if (damage < 0) 
                    {
                        damage = 0;
                    }

                    //damages the target
                    partyMembers.get(target).currentHealth -= (damage);
                    
                    System.out.println("\n" + name + " is attacking " 
                            + partyMembers.get(target).name + ".");
                    Thread.sleep(1000);
                    System.out.println(name + " dealt " + damage + " damage!");
                    Thread.sleep(1000);
                    
                    //makes sure the target is not negative hp
                    if (partyMembers.get(target).currentHealth <= 0)
                    {
                        System.out.println(partyMembers.get(target).getName() + " has fallen!");
                        partyMembers.get(target).currentHealth = 0;
                        partyMembers.get(target).state = false;
                        Thread.sleep(1000);
                    }

                    flag = true;
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

                            //only hits those that are alive
                            if (partyMembers.get(i).getState())
                            {
                                partyMembers.get(i).currentHealth -= (damage);
                                System.out.println(name + " dealt " + damage + " damage to " 
                                        + partyMembers.get(i).getName() + "!");  

                                //fallen message
                                if (partyMembers.get(i).currentHealth <= 0)
                                {
                                    System.out.println(partyMembers.get(i).getName() + " has fallen!");
                                    partyMembers.get(i).currentHealth = 0;
                                    partyMembers.get(i).state = false;
                                    Thread.sleep(1000);
                                }

                                Thread.sleep(1000);
                            }                        
                        }
                        flag = true;
                    }
                    break;
            }
        } while (!flag);

        return entity.currentHealth == 0;

    }//end of fight

}//end of enemy
 