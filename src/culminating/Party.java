package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Party extends Character {

    //Fields
    private final Scanner keyInput = new Scanner(System.in);
    private final Scanner scanN = new Scanner(System.in);
    private final Random random = new Random();
    private boolean state;
    
    //Constructor
    public Party(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money,
                 String weapon, String armor) {
        super(name, level, hp, mp, str, def, spd, exp, dice, money, weapon, armor);
        state = true;
        checkSpecialMoves();
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
        int damage = 0, heal;
        String prompt;
        boolean flag = false;
        
        //checks if partyMem can fight or not
        if (currentHealth == 0)
        {
            state = false;
        }
        else
        {
            state = true;
        }

        //alive
        if (state)
        {
            do
            {
                System.out.println("What will " + name + " do?");
                System.out.println("1) Attack");
                System.out.println("2) Special");
                prompt = keyInput.nextLine();

                switch (prompt) 
                {
                    //attacking
                    case "1":
                        damage = (attack(getDices()) + strength) - entity.defence;
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
                        if ((currentMp - 5) >= 0)
                        {
                            switch(useSpecialMoves())
                            {
                                //Physic
                                case "Physic":
                                    heal = random.nextInt(strength * 5) + strength;

                                    System.out.println("\n" + name + " used Physic!");
                                    Thread.sleep(1000);

                                    for (int i=0; i < partyMembers.size(); i++)
                                    {
                                        partyMembers.get(i).currentHealth += heal;
                                        //makes sure that the heal does not over heal
                                        if (partyMembers.get(i).currentHealth > partyMembers.get(i).health)
                                        {
                                            partyMembers.get(i).currentHealth = partyMembers.get(i).health;
                                        }

                                        System.out.println(name + " restored " + heal + " health to "
                                                + partyMembers.get(i).name + ".");
                                        Thread.sleep(1000);
                                    }

                                    Thread.sleep(1000);  
                                    break;

                                //Nosferatu
                                case "Nosferatu":                                
                                    damage = strength + random.nextInt(50) + 30;

                                    entity.currentHealth -= damage;

                                    if (entity.currentHealth < 0)
                                        entity.currentHealth = 0;

                                    heal = damage / 5;

                                    System.out.println("\n" + name + " used Nosferatu!");
                                    Thread.sleep(1000);
                                    System.out.println(name + " deals " + damage + " damage!");
                                    Thread.sleep(1000);
                                    for (int i = 0; i < partyMembers.size(); i++)
                                    {
                                        partyMembers.get(i).currentHealth += heal;
                                        //makes sure that the heal does not over heal
                                        if (partyMembers.get(i).currentHealth > partyMembers.get(i).health)
                                        {
                                            partyMembers.get(i).currentHealth = partyMembers.get(i).health;
                                        }

                                        System.out.println(name + " restored " + heal + " health to "
                                                + partyMembers.get(i).name + "."); 
                                    }

                                    Thread.sleep(1000);
                                    break;

                                //error handle - there should be nothing here
                                default:
                                    break;
                            }

                            flag = true;
                        }
                        else
                        {
                            System.out.println("You are out of mp, you cant use your special");
                        }
                        break;

                    //error handle
                    default:
                        System.out.println("Please enter a command");
                        break;
                }
            } while (!flag);   
        }
        

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
                dice.add(random.nextInt(6) + 1);
                System.out.println("DICE [" + (i + 1) + "]: " + dice.get(i));
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
    
    /**
     * useSpecialMove
     * TThis method will let used for fight
     */
    @Override
    public String useSpecialMoves()
    {
        //declaring local variabels
        int choice;
        
        //error handle
        do 
        {
            for (int i = 0; i < moveSet.size(); i++)
            {
            System.out.println((i +1) +") " + moveSet.get(i));
            }
            
            System.out.println("What special do you want to use?");
            System.out.print("special: ");
            //if user uses a number
            if (scanN.hasNextInt()) 
            {
                choice = scanN.nextInt();
                //if user tried to pick a nonExistant dice
                if (choice > (moveSet.size())) 
                {
                    System.out.println("Please input a dice");
                }
                //player picks dice
                else
                {
                    switch(choice)
                    {
                        //Physic
                        case 1:
                            if ((currentMp - 5) > 0)
                            {
                                currentMp -= 5;
                                return moveSet.get(choice - 1);
                            }
                            else
                            {
                                System.out.println("You dont have enough mp");
                                break;
                            }
                            
                        //Nosferatu
                        case 2:
                            if ((currentMp - 15) > 0)
                            {
                                currentMp -= 15;
                                return moveSet.get(choice - 1);
                            }
                            else
                            {
                                System.out.println("You dont have enough mp");
                                break;
                            }
                    } 
                }
            }
            //if a user picks anything but a number
            else
            {
                scanN.nextLine();
                System.out.println("Please input a special");
            }
        } while (true);
    }//end of useSpecialMove
    
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
            System.out.println("Level Up! " + getName());
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
            if ((level % 2) == 0 && dices != 7)
            {
                System.out.print("Dices: " + dices + " -> ");
                dices++;
                System.out.println(dices);  
            }
            else
                System.out.println("Dices: " + dices + " -> " + dices);
            
            checkSpecialMoves();
        }
    }//end of checkLvl
    
    /**
     * specialMove
     * This method is a way to show when part members and players will gain a special move(and possibly enemies)
     */
    @Override
    public void checkSpecialMoves()
    {
        
        //makes sure that the user has WraithStrike
        if (!moveSet.contains("Physic"))
        {
            moveSet.add("Physic");
        }

        //users second special
        if (level >= 5 && !moveSet.contains("Nosferatu"))
        {
            moveSet.add("Nosferatu");
        }
    }//end of checkSpecialMoves
    
    

}
