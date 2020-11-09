package culminating;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/***********************************************
 * Project: The Legend of Genshin: Beginning of the World
 * Class: Main
 * Programmers: Jose Jesus II Abejo and Gavin Eugenio
 * Date: November 3, 2020
 * Description: An open world rpg game, inspired by: Minecraft, Dicey Dungeons, Xenoblade Chronicles, Genshin Impact,
 * and Breath of the Wild
 **********************************************/

public class Main {

    //Global objects in main
    public static File file = new File("saveData.txt");
    public static World world;
    public static Clip clip;

    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {

        System.out.println("Test");
        //Check if file exists
        if (!file.exists()) {
            System.out.println("\u001B[31mError save file not found. Game cannot be runned.\u001B[0m");
            System.exit(0);
        }

        //Call music method
        music("title.wav");

        //Print title screen
        System.out.println("\n" +
                " ___     _       _  __  _       _     _   _    __  _       __     ___        \n" +
                "  | |_| |_   |  |_ /__ |_ |\\ | | \\   / \\ |_   /__ |_ |\\ | (_  |_|  |  |\\ | o \n" +
                "  | | | |_   |_ |_ \\_| |_ | \\| |_/   \\_/ |    \\_| |_ | \\| __) | | _|_ | \\| o");
        System.out.println("" +
                "  _   _  __ ___           ___       __    _   _   ___     _           _   _      _  \n" +
                " |_) |_ /__  |  |\\ | |\\ |  |  |\\ | /__   / \\ |_    | |_| |_   \\    / / \\ |_) |  | \\ \n" +
                " |_) |_ \\_| _|_ | \\| | \\| _|_ | \\| \\_|   \\_/ |     | | | |_    \\/\\/  \\_/ | \\ |_ |_/ \n" +
                "                                                                                    \n");

        Scanner keyInput = new Scanner(System.in);
        Scanner checkSave = new Scanner(file);
        String prompt;

        //Print selection menu
        do {

            System.out.println("1) New Game");
            if (checkSave.hasNext()) {
                System.out.println("2) Continue");
            }
            prompt = keyInput.nextLine();

        } while (!prompt.equalsIgnoreCase("1") && (!prompt.equalsIgnoreCase("2") || !checkSave.hasNext()));

        //Go to user selection
        switch (prompt) {

            case "1":
                clip.stop();
                //create new save data
                world = new World();
                world.start();
                break;
            case "2":
                if (checkSave.hasNext()) {
                    clip.stop();
                    //load existing save data
                    loadSave();
                    world.music("overworld.wav");
                    world.navigate();
                    break;
                }

        }

        //save progress (only world)
        save(world.getWorld(), world.getRow(), world.getColumn(), world.getxPos(), world.getyPos(), world.isFinishTutorial());

    }//end of main

    public static void loadSave() throws FileNotFoundException {

        //Objects in loadSave
        ArrayList<ArrayList<String>> newMap = new ArrayList<>();
        Scanner fileRead = new Scanner(file);

        //Variables in loadSave
        String[] data;
        String dataLine;
        int mapRow = 0;

        //Read from first line in the file and tokenize
        dataLine = fileRead.nextLine();
        data = dataLine.split(" ");
        int row = Integer.parseInt(data[0]);
        int column = Integer.parseInt(data[1]);
        int xPos = Integer.parseInt(data[2]);
        int yPos = Integer.parseInt(data[3]);
        boolean finishTutorial = Boolean.parseBoolean(data[4]);

        //Read from following lines in the file that contain the map
        while (fileRead.hasNext()) {

            //Store each row/line of map in string and tokenize
            dataLine = fileRead.nextLine();
            data = dataLine.split(" ");

            //Add each tile to current row of loaded map
            newMap.add(new ArrayList<>());
            for (int i=0; i<data.length; i++) {
                newMap.get(mapRow).add(data[i]);
            }

            //Next row/line
            mapRow++;

        }

        //Close scanner object
        fileRead.close();

        //Load save data
        world = new World(newMap, row, column, xPos, yPos, finishTutorial);

    }

    /*************************
     * Method Name: save
     * Method Description: Saves the progress of the player.
     **************************/
    public static void save(ArrayList<ArrayList<String>> w, int row, int column, int xPos, int yPos, boolean finishTutorial) throws FileNotFoundException {

        //Objects in save
        String newData = row + " " + column + " " + xPos + " " + yPos + " " + finishTutorial;
        PrintWriter fileWrite = new PrintWriter(file);

        //Overwrite current database in the file
        fileWrite.println(newData);
        for (int i=0; i<w.size(); i++) {
            for (int j=0; j<w.get(i).size(); j++) {
                fileWrite.print(w.get(i).get(j) + " ");
            }
            fileWrite.println();
        }

        //Close printWriter object
        fileWrite.close();

    }

    /*************************
     * Method Name: music
     * Method Description: Plays title screen music.
     **************************/
    public static void music(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //Create file object for background music
        File musicFile = new File(filename);

        //Check if file exists
        if (!musicFile.exists()) {
            System.out.println("\u001B[31mError music file not found. Game cannot be runned.\u001B[0m");
            System.exit(0);
        }

        //Create audioInputStream object to get audio from file
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);

        //Create clip object to allow music to be played from file
        clip = AudioSystem.getClip();

        //Open clip
        clip.open(audioInput);

        //Create floatControl object to set volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);

        //Play on loop until program ends or stopped
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }//end of village

}//end of class


