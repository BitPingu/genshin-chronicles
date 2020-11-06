package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    private final Scanner keyInput = new Scanner(System.in);
    private final ArrayList<ArrayList<String>> world = new ArrayList<>();
    private final ArrayList<ArrayList<String>> inventory = new ArrayList<>();

    private int row, column, xPos, yPos, money;
    private boolean tutorial;

    //Constructor
    public Player(String noName) {
        super(noName);
        tutorial = true;
        xPos = 0;
        yPos = 0;
        initWorld();
    }

    //Accessors
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return row;
    }

    public boolean isTutorial() {
        return tutorial;
    }

    //Mutators
    public void setRow(int r) {
        row = r;
    }

    public void setColumn(int c) {
        column = c;
    }

    public void setTutorial(boolean t) {
        tutorial = t;
    }

    /*************************
     * Method Name: initWorld
     * Method Description: Initializes the world through a 5x5 grid and sets player starting position.
     **************************/
    public void initWorld() {
        for (int i=0; i<5; i++) {
            world.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                world.get(i).add("Grass");
            }
        }
        world.get(0).set(2, "Fairy");
        world.get(0).set(3, "Ogre");
        row = 2;
        column = 2;
    }

    /*************************
     * Method Name: navigate
     * Method Description: Allows the player to explore the world using the asdw keys.
     **************************/
    public String navigate() throws InterruptedException {

        Random random = new Random();

        //Variables in navigate
        String currentPosition;
        String movement;
        int spawn;

        while (true) {

            //Get current tile of player location, replace it with player
            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "Player");

            //Print player's field of vision
            System.out.println();
            for (int i=row-2; i<row + 3; i++) {
                for (int j = column - 2; j < column + 3; j++) {
                    System.out.print(world.get(i).get(j) + "\t\t");
                }
                System.out.println("\n");
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
                        world.get(row).set(column, "Player");
                        map();
                        world.get(row).set(column, currentPosition);
                        break;
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
                case "Dungeon":
                    return "Dungeon";

                case "Village":
                    return "Village";

                case "Ogre":
                    return "Ogre";

                case "Wood":
                    System.out.println("You collected some wood.");
                    Thread.sleep(1000);
                    addInventory("Wood");
                    break;

                case "Stone":
                    System.out.println("You collected some stone.");
                    Thread.sleep(1000);
                    addInventory("Stone");
                    break;

                case "Apple":
                    System.out.println("You collected some apples.");
                    Thread.sleep(1000);
                    addInventory("Apple");
                    break;

                case "Ore":
                    System.out.println("You collected some ore.");
                    Thread.sleep(1000);
                    addInventory("Ore");
                    break;

                case "Mushroom":
                    System.out.println("You collected some mushrooms.");
                    Thread.sleep(1000);
                    addInventory("Mushroom");
                    break;

                case "Critter":
                    System.out.println("You caught some critters.");
                    Thread.sleep(1000);
                    addInventory("Critter");
                    break;

                case "Berries":
                    System.out.println("You collected some berries.");
                    Thread.sleep(1000);
                    addInventory("Berries");
                    break;

                case "Chest":
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

    }

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

        if (randGen <= 90) {
            //Tree
            chunk = "Tree";
        } else if (randGen <= 99) {
            //Dungeon
            chunk = "Dungeon";
        } else {
            //Village
            chunk = "Village";
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
            enemy = "Zombie";
        } else if (random <= 40) {
            //Goblin
            enemy = "Goblin";
        } else if (random <= 50) {
            //Ogre
            enemy = "Ogre";
        } else if (random <= 60) {
            //Ghost
            enemy = "Ghost";
        } else if (random <= 70) {
            //Alien
            enemy = "Alien";
        } else if (random <= 80) {
            //Octopus
            enemy = "Octopus";
        } else if (random <= 90) {
            //Skeleton
            enemy = "Skeleton";
        } else {
            //Boss
            enemy = "Boss";
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
            item = "Wood";
        } else if (random <= 40) {
            //Stone
            item = "Stone";
        } else if (random <= 50) {
            //Apple
            item = "Apple";
        } else if (random <= 60) {
            //Ore
            item = "Ore";
        } else if (random <= 70) {
            //Mushroom
            item = "Mushroom";
        } else if (random <= 80) {
            //Critter
            item = "Critter";
        } else if (random <= 90) {
            //Berries
            item = "Berries";
        } else {
            //Chest
            item = "Chest";
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

    }


    /*************************
     * Method Name: fight
     * Method Description: Invoked when player initiates an enemy
     **************************/
    public void fight(Character entity) {

        int prompt;

        System.out.println("You encountered a " + entity.name + "!");
        System.out.println("What will " + name + " do?");
        System.out.println("1) Attack");
        System.out.println("2) Special");
        System.out.println("3) Run");
        prompt = Integer.parseInt(keyInput.nextLine());

        switch (prompt) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

        }

    }

    /*************************
     * Method Name: map
     * Method Description: Display the entire world that has been explored
     **************************/
    public void map() {

        char prompt;

        //Print map
        for (int i=0; i<world.size(); i++) {
            for (int j=0; j<world.get(i).size(); j++) {
                System.out.print(world.get(i).get(j) + "\t\t");
            }
            System.out.println("\n");
        }

        System.out.println("Type 'm' to exit.");
        prompt = keyInput.nextLine().charAt(0);

    }

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

    }

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

    }

}
