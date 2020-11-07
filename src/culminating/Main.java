package culminating;

import java.util.ArrayList;
import java.util.Scanner;

/***********************************************
 * Project: The Legend of Genshin: Beginning of the World
 * Class: Main
 * Programmers: Jose Jesus II Abejo and Gavin Eugenio
 * Date: November 3, 2020
 * Description: An open world rpg game, inspired by: The Legend of Zelda:
 * Breath of the Wild, Genshin Impact, Xenoblade Chronicles, and Minecraft.
 **********************************************/

public class Main {

    private Character ogre = new Enemy("Ogre");
    
    
    public static void main(String[] args) throws InterruptedException {
        
        World world = new World();
        Character ogre = new Enemy("Ogre");

        //Start of the game - waking up and saving person
        System.out.println("You wake up on a grassy field to the sound of someone screaming.");
        Thread.sleep(1000);
        System.out.println("???: Someone please help me!");
        Thread.sleep(1000);
        System.out.println("(Press the asdw keys to move)");
        Thread.sleep(1000);
        world.navigate();
        //***insert fight method here***
        world.getPlayer().setTutorial(false);

        //First battle - no equipment only fists
        System.out.println("???: Wait you know how to fight?");
        Thread.sleep(1000);
        System.out.println("???: Ok! I'm a healer, so I can help you when you're injured.");
        Thread.sleep(1000);

        //After first battle - meeting the waifu and setting the name
        System.out.println("???: Thanks for saving me! My name is Robin. What is yours?");
        Thread.sleep(1000);
        System.out.println("???: What?! You don't remember your name? Then I should call you...");

        System.out.println("! I shall call you " + "!");
        Thread.sleep(1000);
        System.out.println("Here is a map...");
        world.getPlayer().addInventory("\uD83D\uDDFA Map");
        //Robin introduces the player to the world, and the goal of the game

        while (true) 
        {
            world.navigate();
        }

    }//end of main
        
}//end of class


