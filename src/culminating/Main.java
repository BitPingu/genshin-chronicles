package culminating;

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

    public static Scanner keyInput = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        Character player = new Player("You", 1);

        //Start of the game - waking up and saving person
        System.out.println("You wake up on a grassy field to the sound of a girl screaming.");
        Thread.sleep(1000);
        System.out.println("???: Someone please help me!");
        Thread.sleep(1000);
        System.out.println("(Press the asdw keys to move)");
        Thread.sleep(1000);
        player.navigate();
        player.setTutorial(false);

        //After first battle - meeting the waifu and setting the name
        System.out.println("???: Thanks for saving me! My name is Robin. What is yours?");
        Thread.sleep(1000);
        System.out.println("Robin: What?! You don't remember your name? Then I should call you...");

        System.out.print("My name is: ");
        player.setName(keyInput.nextLine());

        System.out.println("I shall call you " + player.name + "!");
        Thread.sleep(1000);
        System.out.println("Here is a map...");
        Thread.sleep(1000);
        System.out.println(player.name + " received a map!");
        Thread.sleep(1000);
        player.addInventory("\uD83D\uDDFA Map");
        //Robin introduces the player to the world, and the goal of the game

        while (true) {

            switch (player.navigate()) {

                case "Dungeon":
                    //Go to dungeon
                    dungeon();
                    break;

                case "Village":
                    //Go to village
                    village();
                    break;

            }

        }
        
    }

    public static void dungeon() {

        System.out.println("You arrived at a dungeon. Will you enter?");

        System.out.println("You entered the dungeon.");
        System.out.println("Floor 1");
        System.out.println("Floor 2");
        System.out.println("Floor 3");

    }

    public static void village() {

        System.out.println("You arrived at a village. Will you enter?");

        System.out.println("Welcome to village.");
        System.out.println("1) Rest at Inn");//pay 50
        System.out.println("2) Visit the Store");
        System.out.println("3) Talk to Villagers");
        System.out.println("4) Exit");

    }
    
}
