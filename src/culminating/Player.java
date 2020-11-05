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
                world.get(i).add("\uD83D\uDFE9");
            }
        }
        world.get(0).set(2, "\uD83E\uDDDA");
        world.get(0).set(3, "\uD83D\uDC79");
        row = 2;
        column = 2;
    }

    /*************************
     * Method Name: navigate
     * Method Description: Allows the player to explore the world using the asdw keys.
     **************************/
    public String navigate() {

        //Variables in navigate
        String currentPosition;
        char movement;

        while (true) {

            //Get current tile of player location, replace it with player
            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "\uD83E\uDDDD");

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
                } else if (world.get(row).get(column).contains("\uD83E\uDDDA") ||
                        world.get(row).get(column).contains("\uD83D\uDC79")) {
                    break;
                }

            }

            //Initiate event depending on player position
            switch (world.get(row).get(column)) {
                case "\uD83C\uDFDB":
                    return "Dungeon";

                case "\uD83C\uDFD8":
                    return "Village";
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
            chunk = "\uD83C\uDF33";
        } else if (randGen <= 99) {
            //Dungeon
            chunk = "\uD83C\uDFDB";
        } else {
            //Village
            chunk = "\uD83C\uDFD8";
        }

        return chunk;

    }

}
