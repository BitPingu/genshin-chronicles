package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class World {

//    protected ArrayList<ArrayList<String>> worldGen = new ArrayList<>();
    
    private final Scanner keyInput = new Scanner(System.in);
    private final ArrayList<ArrayList<String>> world = new ArrayList<>();
    private final ArrayList<ArrayList<String>> inventory = new ArrayList<>();

    private int row, column, xPos, yPos, money;

    public World()
    {
        initWorld();
    }
    private boolean tutorial;
    
    /*************************
     * Method Name: initWorld
     * Method Description: Initializes the world through a 5x5 grid and sets player starting position.
     **************************/
    public void initWorld() {
        //crreasts the starting world
        for (int i=0; i<5; i++) {
            world.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                world.get(i).add("\uD83D\uDFE9");//Grass
            }
        }
        //sets premade icons for game tutorial
        world.get(0).set(2, "\uD83E\uDDDA");//Fairy
        world.get(0).set(3, "\uD83D\uDC79");//Ogre
        row = 2;
        column = 2;
    }//end of initWorld
    
    public String navigate() throws InterruptedException {

        Random random = new Random();

        //Variables in navigate
        String currentPosition;
        String movement;
        int spawn;

        while (true) {

            //Get current tile of player location, replace it with player
            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "\uD83E\uDDDD");//Elf

            //Print player's field of vision
            System.out.println();
            for (int i=row-2; i<row + 3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    System.out.print(world.get(i).get(j) + "\t");
                }
                System.out.println();
            }

            //Movement user input
            System.out.println(xPos + " " + yPos);
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
                    if (!tutorial) {
                        //Use inventory
                        checkInventory();
                    }
                    break;
                case "m":
                    if (!tutorial) {
                        //Use map
                        world.get(row).set(column, "\uD83E\uDDDD");
                        map();
                        world.get(row).set(column, currentPosition);
                    }
                    break;
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
            if (tutorial && (xPos < -2 || yPos < -2 || xPos > 2 || yPos > 2)) {
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
                    dungeon();
                    break;

                case "\uD83C\uDFD8":
                    village();
                    break;

                case "\uD83D\uDC79":
                    return "Ogre";

                case "\uD83E\uDD62":
                    System.out.println("You collected some wood.");
                    Thread.sleep(1000);
                    addInventory("\uD83E\uDD62 Wood");
                    break;

                case "\uD83E\uDD4C":
                    System.out.println("You collected some stone.");
                    Thread.sleep(1000);
                    addInventory("\uD83E\uDD4C Stone");
                    break;

                case "\uD83C\uDF4E":
                    System.out.println("You collected some apples.");
                    Thread.sleep(1000);
                    addInventory("\uD83C\uDF4E Apple");
                    break;

                case "\uD83D\uDC8E":
                    System.out.println("You collected some ore.");
                    Thread.sleep(1000);
                    addInventory("\uD83D\uDC8E Ore");
                    break;

                case "\uD83C\uDF44":
                    System.out.println("You collected some mushrooms.");
                    Thread.sleep(1000);
                    addInventory("\uD83C\uDF44 Mushroom");
                    break;

                case "\uD83D\uDC1B":
                    System.out.println("You caught some critters.");
                    Thread.sleep(1000);
                    addInventory("\uD83D\uDC1B Critter");
                    break;

                case "\uD83C\uDF52":
                    System.out.println("You collected some berries.");
                    Thread.sleep(1000);
                    addInventory("\uD83C\uDF52 Berries");
                    break;

                case "\uD83E\uDDF0":
                    System.out.println("You opened a chest.");
                    Thread.sleep(1000);
                    break;

            }

            //Spawn enemies and/or items when moving
            if (!tutorial && ("a".equals(movement) || "s".equals(movement) 
                    || "d".equals(movement) || "w".equals(movement))) {
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
     * Method Description: Generates a random chunk in the world
     * @return 
     **************************/
    public String chunk() {

        Random generation = new Random();

        //Variables in chunk
        int randGen;
        String chunk;

        //Determine random generation
        randGen = generation.nextInt(100)+1;

        if (randGen <= 60) {
            //Tree
            chunk = "\uD83C\uDF33";
        } else if (randGen <= 90) {
            //Alt Tree
            chunk = "\uD83C\uDF32";
        } else if (randGen <= 99) {
            //Dungeon
            chunk = "\uD83C\uDFDB";
        } else {
            //Village
            chunk = "\uD83C\uDFD8";
        }

        return chunk;

    }

    /*************************
     * Method Name: spawnEnemy
     * Method Description: Spawns a random enemy in the player's field of vision
     **************************/
    public void spawnEnemy() {

        Random entity = new Random();

        int random, enemyRow = 0, enemyColumn = 0;
        String enemy;

        //Determine random enemy
        random = entity.nextInt(100)+1;

        if (random <= 30) {
            //Zombie
            enemy = "\uD83E\uDDDF";
        } else if (random <= 40) {
            //Goblin
            enemy = "\uD83D\uDC7A";
        } else if (random <= 50) {
            //Ogre
            enemy = "\uD83D\uDC79";
        } else if (random <= 60) {
            //Ghost
            enemy = "\uD83D\uDC7B";
        } else if (random <= 70) {
            //Alien
            enemy = "\uD83D\uDC7D";
        } else if (random <= 80) {
            //Octopus
            enemy = "\uD83D\uDC19";
        } else if (random <= 90) {
            //Skeleton
            enemy = "\uD83D\uDC80";
        } else {
            //Golem
            enemy = "\uD83E\uDD16";
        }

        //Determine random spawn coordinates
        random = entity.nextInt(24)+1;

        switch (random) {
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


        //Get current tile of enemy location, replace it with enemy
        //currentPosition = world.get(row).get(column);
        world.get(enemyRow).set(enemyColumn, enemy);

        /*
        //Replace enemy position with original tile
        world.get(row).set(column, currentPosition);
         */

    }

    /*************************
     * Method Name: spawnItem
     * Method Description: Spawns a random item in the player's field of vision (and if lucky a chest)
     **************************/
    public void spawnItem() {

        Random entity = new Random();

        int random, itemRow = 0, itemColumn = 0;
        String item;

        //Determine random enemy
        random = entity.nextInt(100)+1;

        if (random <= 30) {
            //Wood
            item = "\uD83E\uDD62";
        } else if (random <= 40) {
            //Stone
            item = "\uD83E\uDD4C";
        } else if (random <= 50) {
            //Apple
            item = "\uD83C\uDF4E";
        } else if (random <= 60) {
            //Ore
            item = "\uD83D\uDC8E";
        } else if (random <= 70) {
            //Mushroom
            item = "\uD83C\uDF44";
        } else if (random <= 80) {
            //Critter
            item = "\uD83D\uDC1B";
        } else if (random <= 90) {
            //Berries
            item = "\uD83C\uDF52";
        } else {
            //Chest
            item = "\uD83E\uDDF0";
        }

        //Determine random spawn coordinates
        random = entity.nextInt(24)+1;

        switch (random) {
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

        //Get current tile of enemy location, replace it with enemy
        //currentPosition = world.get(row).get(column);
        world.get(itemRow).set(itemColumn, item);

        /*
        //Replace enemy position with original tile
        world.get(row).set(column, currentPosition);
         */

    }//end of spawnItem
    
    /*************************
     * Method Name: map
     * Method Description: Display the entire world that has been explored
     **************************/
    public void map() {

        char prompt;

        //Print map
        for (int i=0; i<world.size(); i++) {
            for (int j=0; j<world.get(i).size(); j++) {
                System.out.print(world.get(i).get(j) + "\t");
            }
            System.out.println();
        }

        System.out.println("Type 'm' to exit.");
        prompt = keyInput.nextLine().charAt(0);

    }//end of map

    /*************************
     * Method Name: checkInventory
     * Method Description: Display the player's inventory
     **************************/
    public void checkInventory() {

        char prompt;

        //Print inventory
        for (int i=0; i<inventory.size(); i++) {
            System.out.println(inventory.get(i).get(0) + ": " + inventory.get(i).size());
        }

        System.out.println("Type 'i' to exit.");
        prompt = keyInput.nextLine().charAt(0);

    }//end of checkInventory

    /*************************
     * Method Name: inventory
     * Method Description: Adds a new item to the player's inventory
     **************************/
    public void addInventory(String item) {

        boolean newItem = true;

        for (int i=0; i<inventory.size(); i++) {
            if (inventory.get(i).contains(item)) {
                inventory.get(i).add(item);
                newItem = false;
            }
        }

        if (newItem) {
            inventory.add(new ArrayList<>());
            inventory.get(inventory.size()-1).add(item);
        }

    }//end of addInventory
    
    public void dungeon() {

        System.out.println("You arrived at a dungeon. Will you enter?");

        System.out.println("You entered the dungeon.");
        System.out.println("Floor 1");
        System.out.println("Floor 2");
        System.out.println("Floor 3");

    }//end of dungeon

    public void village() {

        System.out.println("You arrived at a village. Will you enter?");

        System.out.println("Welcome to village.");
        System.out.println("1) Rest at Inn");//pay 50
        System.out.println("2) Visit the Store");
        System.out.println("3) Talk to Villagers");
        System.out.println("4) Exit");
    }//end of village

}//end of class