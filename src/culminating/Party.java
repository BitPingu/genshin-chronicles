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
    public Party(String name, int level, int hp, int mp, int str, int def, int spd, int exp, int dice, int money,
                 int cHealth, int cMp, String weapon, String armor, boolean inCurrentParty) {
        super(name, level, hp, mp, str, def, spd, exp, dice, money, cHealth, cMp, weapon, armor, inCurrentParty);
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
        int damage, heal;
        String prompt;
        boolean flag = false;
        
        //checks if partyMem can fight or not
        if (currentHealth == 0)
        {
            setState(false);
        }
        else
        {
            setState(true);
        }

        //alive
        if (state)
        {
            do
            {
                clearScreen();

                //Print Enemy battle info
                System.out.println("Enemy:");
                System.out.println(entity.getName());
                System.out.println("HP: " + entity.getCurrentHealth() + "/" + entity.getHealth());
                System.out.println("MP: " + entity.getCurrentMp() + "/" + entity.getMp());

                //Print Party Members' battle info
                System.out.println("\nTeam:");
                for (int k = 0; k < partyMembers.size(); k++) {
                    System.out.format("%-15s", partyMembers.get(k).getName());
                }
                System.out.println();
                for (int k = 0; k < partyMembers.size(); k++) {
                    System.out.format("%-15s", "HP: " + partyMembers.get(k).getCurrentHealth() + "/"
                            + partyMembers.get(k).getHealth());
                }
                System.out.println();
                for (int k = 0; k < partyMembers.size(); k++) {
                    System.out.format("%-15s", "MP: " + partyMembers.get(k).getCurrentMp() + "/"
                            + partyMembers.get(k).getMp());
                }
                System.out.println("\n");
                
                //user options
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
                        switch(useSpecialMoves())
                        {
                            //Robins specials

                            //Physic
                            case "Physic":
                                heal = random.nextInt((strength * 5) - strength) + strength;

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
                                flag = true;
                                break;

                            //Nosferatu
                            case "Nosferatu":                                
                                damage = strength + random.nextInt(50) + 30;

                                entity.currentHealth -= damage;

                                if (entity.currentHealth < 0)
                                    entity.currentHealth = 0;

                                heal = damage / 8;

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
                                flag = true;
                                break;

                            //Claudes specials

                            //Tunder Bullet
                            case "Thunder Bullet":
                                System.out.println("Choose your first dice");
                                damage = attack(getDices());
                                clearScreen();

                                System.out.println("Choose your Second dice");
                                damage += (attack(getDices()) + strength);

                                System.out.println("\n" + name + " used Thunder bullet!");
                                Thread.sleep(1000);
                                System.out.println(name + " deals " + damage + " damage!");
                                Thread.sleep(1000);

                                entity.currentHealth -= (damage);

                                //makes sure that the enemy does not go below 0
                                if (entity.currentHealth < 0)
                                    entity.currentHealth = 0;

                                Thread.sleep(1000);  
                                flag = true;
                                break;

                            //Final Gambit
                            case "Final Gambit":                                
                                damage = (health - currentHealth) + strength;

                                entity.currentHealth -= damage;

                                if (entity.currentHealth < 0)
                                    entity.currentHealth = 0;


                                System.out.println("\n" + name + " used Final Gambit!");
                                Thread.sleep(1000);
                                System.out.println(name + " deals " + damage + " damage!");
                                Thread.sleep(1000);
                                

                                Thread.sleep(1000);
                                flag = true;
                                break;

                            //Keqing specials

                            //Leap of Faith"
                            case "Leap of Faith":
                                damage = (health - currentHealth) + strength;

                                System.out.println("\n" + name + " used Leap of Faith!");
                                Thread.sleep(1000);

                                

                                Thread.sleep(1000);  
                                flag = true;
                                break;

                            //Assassinate
                            case "Assassinate":          
                                //one to 100
                                int killChance = random.nextInt(100) + 1;

                                

                                if (entity.currentHealth < 0)
                                    entity.currentHealth = 0;

                               

                                System.out.println("\n" + name + " used Nosferatu!");
                                Thread.sleep(1000);
                                System.out.println(name + " deals " + " damage!");
                                Thread.sleep(1000);
                                

                                Thread.sleep(1000);
                                flag = true;
                                break;


                            //error handle - there should be nothing here
                            default:
                                break;
                        }   
                        
//                        else
//                        {
//                            clearScreen();
//                            System.out.println("You are out of mp, you cant use your special");
//                            Thread.sleep(1000);
//                        }
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
    
    /***********************
     * diceTotal 
     * This method is specifically made for Ke
     * @param diceTotal - how many dices the character has
     * @return - returns damage output
     ***********************/
    @Override
    public int diceTotal(int diceTotal)
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
    public String useSpecialMoves() throws InterruptedException
    {
        //declaring local variabels
        int choice;
        
        //error handle
        do 
        {
            for (int i = 0; i < moveSet.size(); i++)
            {
                System.out.println((i +1) +") " + moveSet.get(i) + " - " + ((i + 1) * 10));
            }
            System.out.println("0) Back");
            System.out.println("What special do you want to use?");
            System.out.print("special: ");
            
            //if user uses a number
            if (scanN.hasNextInt()) 
            {
                choice = scanN.nextInt();
                //if user tried to pick a nonExistant dice
                if (choice > (moveSet.size()) && choice != 0) 
                {
                    clearScreen();
                    System.out.println("Please input a Special");
                    Thread.sleep(1000);
                }
                //player picks dice
                else
                {
                    switch(choice)
                    {
                        case 0:
                            clearScreen();
                            return "";
                        //part Members first special
                        case 1:
                            if ((currentMp - 10) >= 0)
                            {
                                currentMp -= 10;
                                return moveSet.get(choice - 1);
                            }
                            else
                            {
                                clearScreen();
                                System.out.println("You dont have enough mp");
                                Thread.sleep(1000);
                                break;
                            }
                            
                        //Part members seconed special
                        case 2:
                            if ((currentMp - 20) >= 0)
                            {
                                currentMp -= 15;
                                return moveSet.get(choice - 1);
                            }
                            else
                            {
                                clearScreen();
                                System.out.println("You dont have enough mp");
                                Thread.sleep(1000);
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
        int hpRate = 0, mpRate = 0, strRate = 0, defRate = 0, spdRate = 0, diceLimit = 0;
        
        //Robin
        if (name.equals("\uD83E\uDDDA Robin"))
        {
            hpRate = random.nextInt(10) + 5;
            mpRate = random.nextInt(15) + 5;
            strRate = random.nextInt(8) + 3;
            defRate = random.nextInt(8) + 3;
            spdRate = random.nextInt(9) + 4;
            diceLimit = 5;
        }
        //Claude
        if (name.equals("\uD83D\uDC68 Claude"))
        {
            hpRate = random.nextInt(10) + 5;
            mpRate = random.nextInt(15) + 5;
            strRate = random.nextInt(8) + 3;
            defRate = random.nextInt(8) + 3;
            spdRate = random.nextInt(9) + 4;
            diceLimit = 5;
        }
        //Keqing
        if (name.equals("\uD83D\uDC69 Keqing"))
        {
            hpRate = random.nextInt(10) + 5;
            mpRate = random.nextInt(15) + 5;
            strRate = random.nextInt(8) + 3;
            defRate = random.nextInt(8) + 3;
            spdRate = random.nextInt(9) + 4;
            diceLimit = 5;
        }

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
            health += hpRate;
            System.out.println(health);
            currentHealth = health;
            
            //shows new MP
            System.out.print("MP: " + mp + " -> ");
            mp += mpRate;
            System.out.println(mp);
            currentMp = mp;
            
            //shows new strength
            System.out.print("Atk: " + strength + " -> ");
            strength += strRate;
            System.out.println(strength);
            
            //shows new defence
            System.out.print("Def: " + defence + " -> ");
            defence += defRate;
            System.out.println(defence);
            
            //shows new speed
            System.out.print("Spd: " + speed + " -> ");
            speed += spdRate;
            System.out.println(speed);
            
            //shows new dices
            if ((level % 2) == 0 && dices != diceLimit)
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
        //Robin or Girl
        if (name.equals("\uD83E\uDDDA Robin") || name.equals("\uD83E\uDDDA Girl"))
        {
            //makes sure that the user has Physic
            if (!moveSet.contains("Physic"))
            {
                moveSet.add("Physic");
            }

            //Party Members second special
            if (level >= 8 && !moveSet.contains("Nosferatu"))
            {
                moveSet.add("Nosferatu");
            }
        }
        //Claude
        if (name.equals("\uD83D\uDC68 Claude"))
        {
            //makes sure that the user has Thunder Bullet
            if (!moveSet.contains("Thunder Bullet"))
            {
                moveSet.add("Thunder Bullet");
            }

            //Party Members second special
            if (level >= 8 && !moveSet.contains("Final Gambit"))
            {
                moveSet.add("Final Gambit");
            }
        }
        //Keqing
        if (name.equals("\uD83D\uDC69 Keqing"))
        {
            //makes sure that the user has Leap Of Faith
            if (!moveSet.contains("Leap of Faith"))
            {
                moveSet.add("Leap of Faith");
            }

            //Party Members second special
            if (level >= 8 && !moveSet.contains("Assassinate"))
            {
                moveSet.add("Assassinate");
            }
        }  
    }//end of checkSpecialMoves
}//end of class
