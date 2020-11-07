package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    private final Scanner keyInput = new Scanner(System.in);
    //private final ArrayList<ArrayList<String>> world = new ArrayList<>();
    private final ArrayList<ArrayList<String>> inventory = new ArrayList<>();

    private int row, column, xPos, yPos, money;
    private boolean tutorial;

    //Constructor
    public Player(String noName) {
        super(noName);
        tutorial = true;
        xPos = 0;
        yPos = 0;
        //initWorld();
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
     * Method Name: navigate
     * Method Description: Allows the player to explore the world using the asdw keys.
     **************************/
 
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

    }//end of fight
    
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
    

    
}//end of class
