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

    public static Scanner keyInput = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<ArrayList<String>> world = new ArrayList<>();

        for (int i=0; i<5; i++) {
            world.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                world.get(i).add("Joey");
            }
        }

        int row = 2, column = 2;
        String currentPosition;

        while (true) {

            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "Player");

            for (int i=row-2; i<row+3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    System.out.print(world.get(i).get(j) + "\t");
                }
                System.out.println();
            }

            System.out.print("Movement: ");
            String movement = keyInput.nextLine();

            world.get(row).set(column, currentPosition);

            switch (movement) {
                case "a":
                    column -= 1;
                    break;
                case "s":
                    row += 1;
                    break;
                case "d":
                    column += 1;
                    break;
                case "w":
                    row -= 1;
                    break;
            }

            if (column-1 == 0) {
                column++;
                for (int j=0; j<world.size(); j++) {
                    world.get(j).add(0,"Grass");
                }
            } else if (row+2 == world.size()) {
                world.add(new ArrayList<>());
                for (int j=0; j<world.get(row).size(); j++) {
                    world.get(row+2).add("Grass");
                }
            } else if (column+2 == world.get(row).size()) {
                for (int j=0; j<world.size(); j++) {
                    world.get(j).add("Grass");
                }
            } else if (row-1 == 0){
                row++;
                world.add(0, new ArrayList<>());
                for (int j=0; j<world.get(row).size(); j++) {
                    world.get(row-2).add("Grass");
                }
            }

        }
        
    }
    
}
