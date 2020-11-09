package culminating;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class World {

    //Field objects in World
    private ArrayList<ArrayList<String>> world = new ArrayList<>();
    private final ArrayList<ArrayList<String>> villagesVisited = new ArrayList<>();
    private final Scanner keyInput = new Scanner(System.in);
    private final Random random = new Random();
    private Clip clip;

    private final ArrayList<Character> partyMembers = new ArrayList<>();
    
    //name, level, hp, mp, str, def, spd, exp, dice
    private final Character player  = new Player("\uD83E\uDDDD Traveller", 1, 50, 25, 10, 5, 5, 0, 4, 0);
    private final Character healer = new Party("\uD83E\uDDDA Girl", 1, 35, 55, 6, 6, 5, 0, 3, 0);
    private final Character soldier = new Party("\uD83D\uDE4E Link", 1, 50, 25,10, 5, 5, 0, 4, 0);
    private final Character mage = new Party("\uD83D\uDC70 Mona", 1, 50, 25,10, 5, 5, 0, 4, 0);
    private final Character archer = new Party("\uD83D\uDC68 Claude", 1, 50, 25,10, 5, 5, 0, 4, 0);
    private final Character rogue = new Party("\uD83D\uDC69 Keqing", 1, 50, 25,10, 5, 5, 0, 4, 0);

    private final Character zombie = new Enemy("\uD83E\uDDDF Zombie", 1 , 50, 25, 10, 5, 5, 10, 4, 10);
    private final Character ogre = new Enemy("\uD83D\uDC79 Ogre", 1, 50, 25, 10, 5, 5, 20, 4, 20);
    private final Character goblin = new Enemy("\uD83D\uDC7A Goblin", 1, 50, 25, 10, 5, 5, 30, 4, 30);
    private final Character ghost = new Enemy("\uD83D\uDC7B Ghost", 1, 50, 25, 10, 5, 5, 40, 4, 40);
    private final Character alien = new Enemy("\uD83D\uDC7D Alien", 1, 50, 25, 10, 5, 5, 50, 4, 50);
    private final Character octopus = new Enemy("\uD83D\uDC19 Octopus", 1, 50, 25, 10, 5, 5, 60, 4, 60);
    private final Character skeleton = new Enemy("\uD83D\uDC80 Skeleton", 1, 50, 25, 10, 5, 5, 70, 4, 70);
    private final Character golem = new Enemy("\uD83E\uDD16 Golem", 1, 50, 25, 10, 5, 5, 80, 4, 80);

    //Field variables in World
    private int row, column, xPos, yPos;
    private boolean finishTutorial;

    //Constructors
    public World()
    {
        System.out.println("test");
        initWorld();
        
    }

    public World(ArrayList<ArrayList<String>> w, int r, int c, int x, int y, boolean f) {
        world = w;
        row = r;
        column = c;
        xPos = x;
        yPos = y;
        finishTutorial = f;
    }

    //Accessors
    public ArrayList<ArrayList<String>> getWorld() {
        return world;
    }

    public Character getPlayer() {
        return player;
    }

    public Character getHealer() {
        return healer;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isFinishTutorial() {
        return finishTutorial;
    }

    //Mutators
    public void setWorld(ArrayList<ArrayList<String>> w) {
        world = w;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setColumn(int c) {
        column = c;
    }

    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
    }

    public void setFinishTutorial(boolean f) {
        finishTutorial = f;
    }

    /*************************
     * Method Name: initWorld
     * Method Description: Initializes the world through a 5x5 grid and sets player starting position.
     **************************/
    public void initWorld() {

        for (int i=0; i<5; i++) {
            world.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                world.get(i).add("\uD83D\uDFE9");//Grass
            }
        }

        row = 2;
        column = 2;

    }//end of initWorld

    /*************************
     * Method Name: start
     * Method Description: Prints the text dialogue at the start of the game.
     **************************/
    public void start() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        partyMembers.add(player);

        world.get(0).set(2, "\uD83E\uDDDA");//Fairy
        world.get(0).set(3, "\uD83D\uDC79");//Ogre
        
        clearScreen();

        //Start of the game - waking up and saving person
        System.out.println("\nYou wake up on a grassy field to the sound of a girl screaming.");
        Thread.sleep(1000);
        System.out.println(healer.getName() + ": Someone please help me!");
        Thread.sleep(1000);
        music("overworld.wav");
        navigate();

        //After first battle - meeting the waifu and setting the name
        System.out.println("\n" + healer.getName() + ": Thanks for saving me! My name is Robin. What is yours?");
        Thread.sleep(1000);
        healer.setName("\uD83E\uDDDA Robin");
        System.out.println(healer.getName() + ": What?! You don't remember your name? Then I should call you...");

        System.out.print("My name is: ");
        player.setName("\uD83E\uDDDD " + keyInput.nextLine());

        if (player.name.equals("")) {
            player.setName("...");
        }

        System.out.println(healer.getName() + ": I shall call you " + player.getName() + "!");
        Thread.sleep(1000);
        System.out.println(healer.getName() + ": Here is a map...");
        System.out.println(player.name + " received a map!");
        Thread.sleep(1000);
        getPlayer().addInventory("\uD83D\uDDFA Map");
        //Robin introduces the player to the world, and the goal of the game

        finishTutorial = true;

        //Replace with grass
        world.get(row).set(column-1, "\uD83D\uDFE9");
        world.get(row).set(column, "\uD83D\uDFE9");

        //Call navigate method
        navigate();

    }//end of start

    /*************************
     * Method Name: navigate
     * Method Description: Allows the player to explore the world using the asdw keys.
     **************************/
    public void navigate() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        //Variables in navigate
        String currentPosition;
        String movement;
        int spawn;
        boolean safe;

        while (true) {

            clearScreen();

            //Get current tile of player location, replace it with player
            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "\uD83E\uDDDD");//Elf

            //Display player info
            System.out.println("Player: " + player.getName());
            System.out.println("Level: " + player.getLevel());
            System.out.println("Exp: " + player.getExp());
            System.out.println("Money: " + player.getMoney());
            System.out.println("[asdw]: Move");
            if (finishTutorial) {
                System.out.println("[i]: Inventory ");
                System.out.println("[m]: Map ");
                System.out.println("[l]: Save and Quit ");
            }

            //Display player's field of vision
            System.out.println();
            for (int i=row-2; i<row + 3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    System.out.print(world.get(i).get(j) + "\t");
                }
                System.out.println();
            }

            //Movement user input
            System.out.println("x: "+xPos + ", y: " + yPos);
            System.out.print("Movement: ");
            movement = keyInput.nextLine().toLowerCase();

            //Replace player position with original tile
            world.get(row).set(column, currentPosition);

            switch (movement) {

                case "a":
                    column--;
                    xPos--;
                    break;
                case "s":
                    row++;
                    yPos--;
                    break;
                case "d":
                    column++;
                    xPos++;
                    break;
                case "w":
                    row--;
                    yPos++;
                    break;
                case "i":
                    if (finishTutorial) {
                        //Use inventory
                        getPlayer().checkInventory(partyMembers);
                    }
                    break;
                case "m":
                    if (finishTutorial) {
                        //Use map
                        world.get(row).set(column, "\uD83E\uDDDD");
                        map();
                        world.get(row).set(column, currentPosition);
                    }
                    break;
                case "l":
                    if (finishTutorial) {
                        return;
                    }
                default:

            }

            //Generate world as player moves
            if (column - 1 == 0) {
                column++;
                for (int j=0; j<world.size(); j++) {
                    world.get(j).add(0, chunk());
                }
            } else if (row + 1 == world.size()-1) {
                world.add(new ArrayList<>());
                for (int j=0; j<world.get(row).size(); j++) {
                    world.get(row + 2).add(chunk());
                }
            } else if (column + 1 == world.get(row).size()-1) {
                for (int j=0; j<world.size(); j++) {
                    world.get(j).add(chunk());
                }
            } else if (row - 1 == 0) {
                row++;
                world.add(0, new ArrayList<>());
                for (int j=0; j<world.get(row).size(); j++) {
                    world.get(row - 2).add(chunk());
                }
            }

            //Prevent player from escaping during tutorial
            if (!finishTutorial && (xPos < -2 || yPos < -2 || xPos > 2 || yPos > 2)) {

                if ("a".equals(movement)) {
                    column++;
                    xPos++;
                } else if ("s".equals(movement)) {
                    row--;
                    yPos++;
                } else if ("d".equals(movement)) {
                    column--;
                    xPos--;
                } else if ("w".equals(movement)) {
                    row++;
                    yPos--;
                }
                System.out.println("Where are you going? You can't leave the person!");
                Thread.sleep(1000);

            }

            //Initiate event depending on player position
            switch (world.get(row).get(column)) {

                case "\uD83C\uDFDB":
                    //Go to dungeon
                    dungeon();
                    break;

                case "\uD83C\uDFD8":
                    //Go to village
                    village();
                    break;

                case "\uD83E\uDDDF":
                    //Fight Zombie
                    System.out.println("You encountered a " + zombie.getName() + "!");
                    Thread.sleep(1000);
                    battle(zombie);
                    break;

                case "\uD83D\uDC7A":
                    //Fight Goblin
                    System.out.println("You encountered a " + goblin.getName() + "!");
                    Thread.sleep(1000);
                    battle(goblin);
                    break;

                case "\uD83D\uDC79":
                    //Fight Ogre
                    System.out.println("You encountered a " + ogre.getName() + "!");
                    Thread.sleep(1000);
                    battle(ogre);
                    if (!finishTutorial) {
                        return;
                    }
                    break;

                case "\uD83D\uDC7B":
                    //Fight Ghost
                    System.out.println("You encountered a " + ghost.getName() + "!");
                    Thread.sleep(1000);
                    battle(ghost);
                    break;

                case "\uD83D\uDC7D":
                    //Fight Alien
                    System.out.println("You encountered a " + alien.getName() + "!");
                    Thread.sleep(1000);
                    battle(alien);
                    break;

                case "\uD83D\uDC19":
                    //Fight Octopus
                    System.out.println("You encountered a " + octopus.getName() + "!");
                    Thread.sleep(1000);
                    battle(octopus);
                    break;

                case "\uD83D\uDC80":
                    //Fight Skeleton
                    System.out.println("You encountered a " + skeleton.getName() + "!");
                    Thread.sleep(1000);
                    battle(skeleton);
                    break;

                case "\uD83E\uDD16":
                    //Fight Golem
                    System.out.println("You encountered a " + golem.getName() + "!");
                    Thread.sleep(1000);
                    battle(golem);
                    break;

                case "\uD83E\uDD62":
                    //Collect wood
                    System.out.println("You collected some wood.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83E\uDD62 Wood");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83E\uDD4C":
                    //Collect stone
                    System.out.println("You collected some stone.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83E\uDD4C Stone");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF4E":
                    //Collect apples
                    System.out.println("You collected some apples.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83C\uDF4E Apple");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83D\uDC8E":
                    //Collect ores
                    System.out.println("You collected some ore.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83D\uDC8E Ore");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF44":
                    //Collect mushrooms
                    System.out.println("You collected some mushrooms.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83C\uDF44 Mushroom");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83D\uDC1B":
                    //Collect critters
                    System.out.println("You caught some critters.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83D\uDC1B Critter");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF52":
                    //Collect berries
                    System.out.println("You collected some berries.");
                    Thread.sleep(1000);
                    getPlayer().addInventory("\uD83C\uDF52 Berries");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83E\uDDF0":
                    //Collect open chest
                    System.out.println("You opened a chest.");
                    Thread.sleep(1000);
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

            }

            //Check starting area and village within FOV
            safe = false;
            for (int i=row-2; i<row + 3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    if (world.get(i).get(j).equals("\uD83D\uDFE9") || world.get(i).get(j).equals("\uD83C\uDFD8")) {
                        safe = true;
                        break;
                    }
                }
            }

            //Spawn enemies and/or items when moving and no starting area or village in FOV
            if (finishTutorial && ("a".equals(movement) || "s".equals(movement) || "d".equals(movement) ||
                    "w".equals(movement)) && !safe) {

                spawn = random.nextInt(2);
                if (spawn == 0) {
                    spawnEnemy();
                }
                spawn = random.nextInt(2);
                if (spawn == 0) {
                    spawnItem();
                }

            }

        }

    }//end of navigate

    /*************************
     * Method Name: chunk
     * Method Description: Generates a random chunk in the world.
     * @return chunk - chunk to be generated
     **************************/
    public String chunk() {

        //Variables in chunk
        int generation;
        String chunk;

        //Determine random generation
        generation = random.nextInt(100)+1;

        if (generation <= 60) {
            //Tree
            chunk = "\uD83C\uDF33";
        } else if (generation <= 90) {
            //Alt Tree
            chunk = "\uD83C\uDF32";
        } else if (generation <= 99) {
            //Dungeon
            chunk = "\uD83C\uDFDB";
        } else {
            //Village
            chunk = "\uD83C\uDFD8";
        }

        return chunk;

    }//end of chunk

    /*************************
     * Method Name: spawnEnemy
     * Method Description: Spawns a random enemy in the player's field of vision.
     **************************/
    public void spawnEnemy() {

        //Variables in spawnEnemy
        int spawn, enemyRow = 0, enemyColumn = 0;
        String enemy;

        //Determine random enemy
        spawn = random.nextInt(100)+1;

        if (spawn <= 30) {
            //Zombie
            enemy = "\uD83E\uDDDF";
        } else if (spawn <= 40) {
            //Goblin
            enemy = "\uD83D\uDC7A";
        } else if (spawn <= 50) {
            //Ogre
            enemy = "\uD83D\uDC79";
        } else if (spawn <= 60) {
            //Ghost
            enemy = "\uD83D\uDC7B";
        } else if (spawn <= 70) {
            //Alien
            enemy = "\uD83D\uDC7D";
        } else if (spawn <= 80) {
            //Octopus
            enemy = "\uD83D\uDC19";
        } else if (spawn <= 90) {
            //Skeleton
            enemy = "\uD83D\uDC80";
        } else {
            //Golem
            enemy = "\uD83E\uDD16";
        }

        //Determine random spawn coordinates
        spawn = random.nextInt(24)+1;

        switch (spawn) {

            case 1:
                enemyRow = row - 2;
                enemyColumn = column - 2;
                break;
            case 2:
                enemyRow = row - 2;
                enemyColumn = column - 1;
                break;
            case 3:
                enemyRow = row - 2;
                enemyColumn = column;
                break;
            case 4:
                enemyRow = row - 2;
                enemyColumn = column + 1;
                break;
            case 5:
                enemyRow = row - 2;
                enemyColumn = column + 2;
                break;
            case 6:
                enemyRow = row - 1;
                enemyColumn = column - 2;
                break;
            case 7:
                enemyRow = row - 1;
                enemyColumn = column - 1;
                break;
            case 8:
                enemyRow = row - 1;
                enemyColumn = column;
                break;
            case 9:
                enemyRow = row - 1;
                enemyColumn = column + 1;
                break;
            case 10:
                enemyRow = row - 1;
                enemyColumn = column + 2;
                break;
            case 11:
                enemyRow = row;
                enemyColumn = column - 2;
                break;
            case 12:
                enemyRow = row;
                enemyColumn = column - 1;
                break;
            case 13:
                enemyRow = row;
                enemyColumn = column + 1;
                break;
            case 14:
                enemyRow = row;
                enemyColumn = column + 2;
                break;
            case 15:
                enemyRow = row + 1;
                enemyColumn = column - 2;
                break;
            case 16:
                enemyRow = row + 1;
                enemyColumn = column - 1;
                break;
            case 17:
                enemyRow = row + 1;
                enemyColumn = column;
                break;
            case 18:
                enemyRow = row + 1;
                enemyColumn = column + 1;
                break;
            case 19:
                enemyRow = row + 1;
                enemyColumn = column + 2;
                break;
            case 20:
                enemyRow = row + 2;
                enemyColumn = column - 2;
                break;
            case 21:
                enemyRow = row + 2;
                enemyColumn = column - 1;
                break;
            case 22:
                enemyRow = row + 2;
                enemyColumn = column;
                break;
            case 23:
                enemyRow = row + 2;
                enemyColumn = column + 1;
                break;
            case 24:
                enemyRow = row + 2;
                enemyColumn = column + 2;
                break;

        }

        //Spawn enemy in world
        world.get(enemyRow).set(enemyColumn, enemy);

    }//end of spawnEnemy

    /*************************
     * Method Name: spawnItem
     * Method Description: Spawns a random item in the player's field of vision (and if lucky a chest).
     **************************/
    public void spawnItem() {

        //Variables in spawnItem
        int spawn, itemRow = 0, itemColumn = 0;
        String item;

        //Determine random enemy
        spawn = random.nextInt(100)+1;

        if (spawn <= 30) {
            //Wood
            item = "\uD83E\uDD62";
        } else if (spawn <= 40) {
            //Stone
            item = "\uD83E\uDD4C";
        } else if (spawn <= 50) {
            //Apple
            item = "\uD83C\uDF4E";
        } else if (spawn <= 60) {
            //Ore
            item = "\uD83D\uDC8E";
        } else if (spawn <= 70) {
            //Mushroom
            item = "\uD83C\uDF44";
        } else if (spawn <= 80) {
            //Critter
            item = "\uD83D\uDC1B";
        } else if (spawn <= 90) {
            //Berries
            item = "\uD83C\uDF52";
        } else {
            //Chest
            item = "\uD83E\uDDF0";
        }

        //Determine random spawn coordinates
        spawn = random.nextInt(24)+1;

        switch (spawn) {

            case 1:
                itemRow = row - 2;
                itemColumn = column - 2;
                break;
            case 2:
                itemRow = row - 2;
                itemColumn = column - 1;
                break;
            case 3:
                itemRow = row - 2;
                itemColumn = column;
                break;
            case 4:
                itemRow = row - 2;
                itemColumn = column + 1;
                break;
            case 5:
                itemRow = row - 2;
                itemColumn = column + 2;
                break;
            case 6:
                itemRow = row - 1;
                itemColumn = column - 2;
                break;
            case 7:
                itemRow = row - 1;
                itemColumn = column - 1;
                break;
            case 8:
                itemRow = row - 1;
                itemColumn = column;
                break;
            case 9:
                itemRow = row - 1;
                itemColumn = column + 1;
                break;
            case 10:
                itemRow = row - 1;
                itemColumn = column + 2;
                break;
            case 11:
                itemRow = row;
                itemColumn = column - 2;
                break;
            case 12:
                itemRow = row;
                itemColumn = column - 1;
                break;
            case 13:
                itemRow = row;
                itemColumn = column + 1;
                break;
            case 14:
                itemRow = row;
                itemColumn = column + 2;
                break;
            case 15:
                itemRow = row + 1;
                itemColumn = column - 2;
                break;
            case 16:
                itemRow = row + 1;
                itemColumn = column - 1;
                break;
            case 17:
                itemRow = row + 1;
                itemColumn = column;
                break;
            case 18:
                itemRow = row + 1;
                itemColumn = column + 1;
                break;
            case 19:
                itemRow = row + 1;
                itemColumn = column + 2;
                break;
            case 20:
                itemRow = row + 2;
                itemColumn = column - 2;
                break;
            case 21:
                itemRow = row + 2;
                itemColumn = column - 1;
                break;
            case 22:
                itemRow = row + 2;
                itemColumn = column;
                break;
            case 23:
                itemRow = row + 2;
                itemColumn = column + 1;
                break;
            case 24:
                itemRow = row + 2;
                itemColumn = column + 2;
                break;

        }

        //Spawn item in world
        world.get(itemRow).set(itemColumn, item);

    }//end of spawnItem

    /*************************
     * Method Name: map
     * Method Description: Displays the entire world that has been explored.
     **************************/
    public void map() {

        //Print map
        System.out.println();
        for (int i=0; i<world.size(); i++) {
            for (int j=0; j<world.get(i).size(); j++) {
                System.out.print(world.get(i).get(j) + "\t");
            }
            System.out.println();
        }

        System.out.println("Type anything to exit.");
        keyInput.nextLine();

    }//end of map

    /*************************
     * Method Name: battle
     * Method Description: Displays the battle system.
     * @param entity - Enemy to be fought.
     **************************/
    public void battle(Character entity) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        boolean win = false;

        //Call music method
        clip.stop();
        music("battle.wav");

        if (!finishTutorial) {
            //First battle - no equipment only fists
            System.out.println(healer.getName() + ": Wait, you know how to fight?");
            Thread.sleep(1000);
            System.out.println(healer.getName() + ": Ok! I'm a healer, so I can help you when you're injured.");
            Thread.sleep(1000);
            partyMembers.add(healer);
        }

        //Loops through Party Members' and Enemy turns
        for (int i=0;; i++)
        {
            //Loops through each Party Members' turn
            for (int j = 0; j < partyMembers.size(); j++) 
            {

                clearScreen();

                //Print Party Members' battle info
                for (int k = 0; k < partyMembers.size(); k++) 
                {
                    System.out.println(partyMembers.get(k).getName() + " - HP: " + partyMembers.get(k).getHealth());
                }

                //Print Enemy battle info
                System.out.println();
                System.out.println(entity.getName() + " - HP: " + entity.getHealth());
                System.out.println();
                
                //If one of the Party Members defeats the enemy
                if (partyMembers.get(j).fight(partyMembers, entity)) 
                {
                    win = true;
                    break;
                }

            }

            if (win) 
            {
                //Player (and Team Members) win
                System.out.println(entity.name + " dies!");
                Thread.sleep(1000);

                //Distribute exp to all Party Members
                System.out.println();
                for (int j = 0; j < partyMembers.size(); j++)
                {
                    partyMembers.get(j).gainExpMoney(entity);
                    partyMembers.get(j).checkLvl();
                }
                Thread.sleep(2000);
                break;
            }

            //If a Party Member dies
            if (entity.fight(partyMembers, player)) {
                System.out.println("Oh no you've died!");
                System.exit(0);
            }

        }

        //Replace enemy on map and reset their health
        world.get(row).set(column, "\uD83C\uDF33");
        entity.setHealth(entity.health);

        //Call music method
        clip.stop();
        music("overworld.wav");

    }

    /*************************
     * Method Name: dungeon
     * Method Description: Displays the dungeon.
     **************************/
    public void dungeon() {

        System.out.println("You arrived at a dungeon. Will you enter?");

        System.out.println("You entered the dungeon.");
        System.out.println("Floor 1");
        System.out.println("Floor 2");
        System.out.println("Floor 3");

    }//end of dungeon

    /*************************
     * Method Name: village
     * Method Description: Displays the village.
     **************************/
    public void village() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        //ArrayList for available quests in current village
        ArrayList<String> quests = null;

        //Variables in village
        String prompt;
        int newQuests, questSelect;
        boolean visitedVillage = false, enoughItems;

        //User prompt to enter village
        do {
            System.out.println("You arrived at a village. Will you enter? (y/n)");
            prompt = keyInput.nextLine();
        } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));

        if (prompt.equalsIgnoreCase("y")) {

            //Call music method
            clip.stop();
            music("village.wav");

            //Check if village is already visited
            for (int i=0; i<villagesVisited.size(); i++) {

                if (villagesVisited.get(i).contains(xPos + "," + yPos)) {
                    //Retrieve available quests for specific village
                    quests = villagesVisited.get(i);
                    visitedVillage = true;
                    break;
                }

            }

            //If new village
            if (!visitedVillage) {

                //Add to villages visited
                villagesVisited.add(new ArrayList<>());
                villagesVisited.get(villagesVisited.size()-1).add(xPos + "," + yPos);

                //Assign random number of quests to village
                newQuests = random.nextInt(3) + 1;
                for (int j=0; j<newQuests; j++) {
                    villagesVisited.get(villagesVisited.size()-1).add(quest());
                }
                quests = villagesVisited.get(villagesVisited.size()-1);

            }

            do {

                clearScreen();

                //User prompt inside village
                System.out.println("Welcome to village.");
                System.out.println("1) Rest at Inn");//pay 50
                System.out.println("2) Visit the Store");
                System.out.println("3) Visit the Workshop");
                System.out.println("4) Talk to Villagers");
                System.out.println("5) Exit");
                prompt = keyInput.nextLine();

                //Go to user selection
                switch (prompt) {

                    case "1":
                        //Rest at Inn (recover health of all party members)
                        do {
                            System.out.println("Pay 50 rupees to rest at Inn? (y/n)");
                            prompt = keyInput.nextLine();
                        } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));

                        if (prompt.equalsIgnoreCase("y")) {
                            System.out.println("You and your team's health are fully restored!");
                            Thread.sleep(1000);
                            for (int j=0; j<partyMembers.size(); j++) {
                                partyMembers.get(j).setHealth(partyMembers.get(j).getHealth());
                            }
                        }
                        break;

                    case "2":
                        //Visit the Store (purchase equipment)
                        System.out.println("Store Clerk: Hey there! How may I help ya?");
                        System.out.println("1) Weapons and Armor");
                        System.out.println("2) Items");
                        System.out.println("3) See ya!");
                        prompt = keyInput.nextLine();
                        System.out.println("Store Clerk: Come back soon!");
                        Thread.sleep(1000);
                        break;

                    case "3":
                        //Visit the Workshop (crafting)
                        System.out.println(player.name + ": Hmm... what should I craft?");
                        System.out.println("Type 'r' to return.");
                        prompt = keyInput.nextLine();
                        break;

                    case "4":
                        //Talk to Villagers (view available quests)
                        do {

                            enoughItems = false;

                            //Print available quests for specific village
                            if (quests.size() > 0) {
                                for (int i = 1; i < quests.size(); i++) {
                                    System.out.println(i + ") " + quests.get(i));
                                }
                            } else {
                                System.out.println("There are no available quests.");
                            }
                            System.out.println("Type 0 to return.");
                            questSelect = Integer.parseInt(keyInput.nextLine());

                            //User prompt to select available quest
                            if (questSelect > 0 && questSelect < quests.size()) {

                                //Tokenize quest selected to get individual values
                                String[] tokens = quests.get(questSelect).split(" ");
                                String item = tokens[4] + " " + tokens[5];
                                int amount = Integer.parseInt(tokens[3]);

                                //Loop through each item in player's inventory
                                for (int i=0; i<player.getInventory().size(); i++) {

                                    //Check if player has enough of the item required
                                    if (player.getInventory().get(i).contains(item) &&
                                            player.getInventory().get(i).size() >= amount) {

                                        enoughItems = true;

                                        //User prompt to give item required
                                        do {
                                            System.out.println("Give " + amount + " " + item + " to " + tokens[0] + "? (y/n)");
                                            prompt = keyInput.nextLine();
                                        } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));

                                        if (prompt.equalsIgnoreCase("y")) {

                                            System.out.println(tokens[0] + " Thank you!");
                                            Thread.sleep(1000);

                                            //Give required item amount
                                            for (int j=0; j<amount; j++) {
                                                player.getInventory().get(i).remove(item);
                                            }

                                            //Remove quest from village and give reward
                                            quests.remove(questSelect);
                                            questReward();
                                            break;

                                        }

                                    }

                                }

                                //Not enough items
                                if (!enoughItems) {
                                    System.out.println("You don't have enough of that item.");
                                    Thread.sleep(1000);
                                }

                            }

                        } while (questSelect != 0);
                        break;

                }

            } while (!prompt.equalsIgnoreCase("5"));

            //Call music method
            clip.stop();
            music("overworld.wav");

        }

    }//end of village

    /*************************
     * Method Name: quests
     * Method Description: Generates a random quest.
     * @return occupation + ": I need " + amount + " " + material + " to " + reason - generated quest
     **************************/
    public String quest() {

        //Variables in quest
        String occupation = null, material = "", reason = "";
        int villager, amount;

        //Determine random villager
        villager = random.nextInt(6) + 1;

        switch (villager) {

            case 1:
                occupation = "Farmer";
                material = "\uD83C\uDF44 Mushrooms";
                reason = "feed the cows.";
                break;
            case 2:
                occupation = "Miner";
                material = "\uD83E\uDD4C Stone";
                reason = "make a stone pickaxe.";
                break;
            case 3:
                occupation = "Carpenter";
                material = "\uD83E\uDD62 Wood";
                reason = "build a house.";
                break;
            case 4:
                occupation = "Blacksmith";
                material = "\uD83D\uDC8E Ore";
                reason = "craft a sword.";
                break;
            case 5:
                occupation = "Alchemist";
                material = "\uD83D\uDC1B Critters";
                reason = "brew elixirs.";
                break;
            case 6:
                occupation = "Cook";
                material = "\uD83C\uDF52 Berries";
                material = "\uD83C\uDF4E Apples";
                reason = "make fruit salad.";
                break;

        }

        //Determine random amount of item required
        amount = random.nextInt(5) + 1;

        //Return quest
        return occupation + ": I need " + amount + " " + material + " to " + reason;

    }

    /*************************
     * Method Name: questReward
     * Method Description: Generates a quest reward.
     **************************/
    public void questReward() {

    }

    /*************************
     * Method Name: clearScreen
     * Method Description: Clear screen for world.
     **************************/
    public void clearScreen()
    {
        for (int i = 0; i < 25; i++) 
        {
            System.out.println("");    
        }
    }//end of clearScreen

    /*************************
     * Method Name: music
     * Method Description: Plays epic music.
     **************************/
    public void music(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //Create file object for background music
        File musicFile = new File(filename);

        //Check if file exists
        if (!musicFile.exists()) {
            System.out.println("\u001B[31mError music file not found. Game cannot be runned.\u001B[0m");
            System.exit(0);
        }

        //Create audioInputStream object to get audio from file
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);

        //Create clip object to allow music to be played from file
        clip = AudioSystem.getClip();

        //Open clip
        clip.open(audioInput);

        //Create floatControl object to set volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-20.0f);

        //Play on loop until program ends or stopped
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }//end of music

}//end of class