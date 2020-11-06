package Entities;

import Entities.Character;
import culminating.Controller;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character{

    private Controller controller;
    private Input input;
    private final Scanner keyInput = new Scanner(System.in);
    private final ArrayList<ArrayList<String>> world = new ArrayList<>();
    private ArrayList<ArrayList<String>> inventory;

    private int row, column, xPos, yPos, money;
    private boolean tutorial;

    //Constructor
    public Player(Controller controller) {
        super();
        this.controller = controller;
        //checkpoints/stateholders
        tutorial = true;
        
        //realtime user input
        
        //position oh map
        xPos = 0;
        yPos = 0;
   
       // initWorld();
    }
    
    public void update()
    {
        int deltaX = 0;
        int deltaY = 0;
        
        if (controller.isRequestingUp())
        {
            System.out.println("up");
            deltaY -=2;
        }
        if (controller.isRequestingDown())
        {
            System.out.println("Down");
            deltaY+=2;
        }
        if (controller.isRequestingLeft())
        {
            System.out.println("Left");
            deltaX-=2;
        }
        if (controller.isRequestingRight())
        {
            System.out.println("Right");
            deltaX+=2;
        }
        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }
    
    
}
