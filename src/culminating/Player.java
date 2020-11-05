package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    private final Scanner keyInput = new Scanner(System.in);
    private final ArrayList<ArrayList<String>> world = new ArrayList<>();
    private ArrayList<ArrayList<String>> inventory;

    private int row, column, xPos, yPos, money;
    private boolean controls, tutorial;

    //Constructor
    public Player() {
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
    public String navigate() {

        Random random = new Random();

        //Variables in navigate
        String currentPosition;
        char movement;
        int spawn;

        while (true) {

            //Get current tile of player location, replace it with player
            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "Player");

            //Print map
            for (int i = row - 2; i < row + 3; i++) {
                for (int j = column - 2; j < column + 3; j++) {
                    System.out.print(world.get(i).get(j) + "\t");
                }
                System.out.println();
            }

            //Print controls (only once)
            if (!controls) {
                System.out.println("(Press the asdw keys to move)");
                controls = true;
            }

            //Movement user input
            System.out.println(xPos + " " + yPos);
            System.out.print("Movement: ");
            movement = keyInput.nextLine().charAt(0);

            //Replace player position with original tile
            world.get(row).set(column, currentPosition);

            switch (movement) {
                case 'a':
                    column--;
                    xPos--;
                    break;
                case 's':
                    row++;
                    yPos--;
                    break;
                case 'd':
                    column++;
                    xPos++;
                    break;
                case 'w':
                    row--;
                    yPos++;
                    break;
            }

            //Generate world as player moves
            if (column - 1 == 0) {
                column++;
                for (int j = 0; j < world.size(); j++) {
                    world.get(j).add(0, chunk());
                }
            } else if (row + 2 == world.size()) {
                world.add(new ArrayList<>());
                for (int j = 0; j < world.get(row).size(); j++) {
                    world.get(row + 2).add(chunk());
                }
            } else if (column + 2 == world.get(row).size()) {
                for (int j = 0; j < world.size(); j++) {
                    world.get(j).add(chunk());
                }
            } else if (row - 1 == 0) {
                row++;
                world.add(0, new ArrayList<>());
                for (int j = 0; j < world.get(row).size(); j++) {
                    world.get(row - 2).add(chunk());
                }
            }

            //Only happens during tutorial
            if (tutorial) {
                //Prevent player from escaping or save the Fairy
                if (xPos < -2 || yPos < -2 || xPos > 2 || yPos > 2) {
                    if (movement == 'a') {
                        column++;
                        xPos++;
                    } else if (movement == 's') {
                        row--;
                        yPos++;
                    } else if (movement == 'd') {
                        column--;
                        xPos--;
                    } else if (movement == 'w') {
                        row++;
                        yPos--;
                    }
                    System.out.println("Where are you going? You can't leave the person!");
                } else if (world.get(row).get(column).contains("Fairy") ||
                        world.get(row).get(column).contains("Ogre")) {
                    break;
                }

            }

            //Initiate event depending on player position
            switch (world.get(row).get(column)) {
                case "Dungeon":
                    return "Dungeon";

                case "Village":
                    return "Village";

                case "Zombie":
                    return "Zombie";
            }

            //Spawn enemies and/or items
            if (!tutorial) {
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

        return null;

    }

    /*************************
     * Method Name: chunk
     * Method Description: Generates a random chunk in the world
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
    public void fight() {

        System.out.println("You encountered a " + "!");
        System.out.println("What will " + " do?");

    }

}
