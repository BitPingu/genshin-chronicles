package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Party extends Character {

    //Fields
    private final Scanner keyInput = new Scanner(System.in);
    private final Scanner scanN = new Scanner(System.in);
    private final Random random = new Random();
    
    //Constructor
    public Party(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money) {
        super(name, level, hp, mp, str, def, spd, exp, dice, money);
        weapon = "\uD83E\uDD4D Wooden Staff";
        armor = "\uD83D\uDC57 Leather Dress";
        moveSet.add("Physic");
    }

    //Accessors

    //Mutators

    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy.
     * @param partyMembers - party members
     * @param entity - player(redundant but for overriding)
     * @return - if player character is at 0 hp, becomes true;
     * @throws java.lang.InterruptedException
     **************************/
    @Override
    public boolean fight(ArrayList<Character> partyMembers, Character entity) throws InterruptedException {

        //Variables in fight
        int damage =0;
        String prompt;
        boolean flag = false;

        do
        {
            System.out.println("What will " + name + " do?");
            System.out.println("1) Attack");
            System.out.println("2) Special");
            System.out.println("3) Run");
            prompt = keyInput.nextLine();

            switch (prompt) 
            {
                //attacking
                case "1":
                    damage = (attack(getDices()) + getStrength()) - entity.defence;
                    if (damage < 0) 
                    {
                        damage = 0;
                    }
                    entity.currentHealth -= (damage);

                    //makes sure that the enemy does not go below 0
                    if (entity.currentHealth < 0)
                        entity.currentHealth = 0;

                    System.out.println("\n" + name + " attacks!");
                    Thread.sleep(1000);
                    System.out.println(name + " deals " + damage + " damage!");
                    Thread.sleep(1000);
                    flag = true;
                    break;
                    
                //specials
                case "2":
                    if (currentMp > 0)
                    {
                        currentMp -= 5;
                        System.out.println();
                        for (int i=0; i<moveSet.size(); i++) {
                            System.out.println(i+1 + ") " + moveSet.get(i));
                        }
                        
                        prompt = keyInput.nextLine();
                        for (int i=0; i < partyMembers.size(); i++) {
                            if (partyMembers.get(i).health == partyMembers.get(i).currentHealth) {
                                break;
                            }
                            partyMembers.get(i).currentHealth++;
                            damage++;
                        }
                        System.out.println("\n" + name + " used Physic!");
                        Thread.sleep(1000);
                        
                        for (int i=0; i < partyMembers.size(); i++)
                        {
                            System.out.println(name + " restored " + damage + " health to "
                                    + partyMembers.get(i).name + ".");  
                        }
                        
                        Thread.sleep(1000); 
                        flag = true;
                    }
                    else
                    {
                        System.out.println("You are out of mp, you cant use your special");
                    }
                    break;
                
                //running away
                case "3":
                    if (speed > (random.nextInt(entity.speed) + 2)) 
                    {
                        return true; 
                    }
                    else
                    {
                        System.out.println("You tripped while trying to run");
                        flag = true;
                    }
                //error handle
                default:
                    System.out.println("Please enter a command");
                    break;

            }

        } while (!flag);

        return entity.currentHealth == 0;

    }//end of fight
    
    /***********************
     * attack 
     * This method will show the user their available attacks
     * @param diceTotal - how many dices the character has
     * @return - returns damage output
     ***********************/
    @Override
    public int attack(int diceTotal)
    {
        //declaring local variables
        int choice;
        ArrayList<Integer> dice = new ArrayList<>();
        
        //error trap
        do 
        {
            //shows how many dices the player can use
            for (int i = 0; i < diceTotal; i++)
            {
                dice.add(random.nextInt(8) + 1);
                System.out.println("DICE[" + (i + 1) + "]: " + dice.get(i));
            }
            System.out.println("What dice do you want to use?");
            System.out.print("Dice: ");
            //if user uses a number
            if (scanN.hasNextInt()) 
            {
                choice = scanN.nextInt();
                //if user tried to pick a nonExistant dice
                if (choice > dice.size()) 
                {
                    System.out.println("Please input a dice");
                }
                //player picks dice
                else
                {
                    return dice.get(choice-1);
                }
            }
            //id user picks anytghing but a number
            else
            {
                scanN.nextLine();
                System.out.println("Please input a dice");
            }
        } while (true);

    }//end of attack
    
    /*************************
     * checkLvl
     * This method will check if the user can level up or not
     *************************/
    @Override
    public void checkLvl()
    {
        //declaring base growth of each party member
        int bHp, bMp, bAtk, bDef, bSpd;
        //level up based on level * 20
        if (getExp() >= (getLevel() * 20))
        {
            System.out.println("Level up! " + getName());
            setExp(0);
            
            //shows new level
            System.out.print("Lvl: " + level + " -> ");
            level += 1;
            System.out.println(level);
            
            //shows new heatlh
            System.out.print("HP: " + health + " -> ");
            health += random.nextInt(10) + 5;
            System.out.println(health);
            currentHealth = health;
            
            //shows new MP
            System.out.print("MP: " + mp + " -> ");
            mp += random.nextInt(15) + 5;
            System.out.println(mp);
            currentMp = mp;
            
            //shows new strength
            System.out.print("Atk: " + strength + " -> ");
            strength += random.nextInt(8) + 1;
            System.out.println(strength);
            
            //shows new defence
            System.out.print("Def: " + defence + " -> ");
            defence += random.nextInt(8) + 3;
            System.out.println(defence);
            
            //shows new speed
            System.out.print("Spd: " + speed + " -> ");
            speed += random.nextInt(9) + 4;
            System.out.println(speed);
            
            //shows new dices
            if ((level % 2) == 0 && dices != 6)
            {
                System.out.print("Dices: " + dices + " -> ");
                dices++;
                System.out.println(dices);  
            }
            else
                System.out.println("Dices: " + dices + " -> " + dices);
            
        }
    }//end of checkLvl

}
