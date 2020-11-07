package culminating;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {

    private final Scanner keyInput = new Scanner(System.in);
    private final World world = new World();
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

//    /*************************
//     * Method Name: initWorld
//     * Method Description: Initializes the world through a 5x5 grid and sets player starting position.
//     **************************/
//    public void initWorld() {
//        for (int i=0; i<5; i++) {
//            world.add(new ArrayList<>());
//            for (int j=0; j<5; j++) {
//                world.get(i).add("\uD83D\uDFE9");//Grass
//            }
//        }
//        world.get(0).set(2, "\uD83E\uDDDA");//Fairy
//        world.get(0).set(3, "\uD83D\uDC79");//Ogre
//        row = 2;
//        column = 2;
//    }

    /*************************
     * Method Name: navigate
     * Method Description: Allows the player to explore the world using the asdw keys.
     **************************/
    public void navigate() throws InterruptedException {
        world.navigate();
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

    }//end of fight
}//end of class
