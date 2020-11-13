package culminating;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/***********************************************
 * Project: The Legend of Genshin: Beginning of the World V3
 * Class: Main
 * Programmers: Jose Jesus II Abejo and Gavin Eugenio
 * Date: November 3, 2020
 * Description: An open world rpg game, inspired by: Minecraft, Dicey Dungeons, Xenoblade Chronicles, Genshin Impact,
 * and Breath of the Wild
 **********************************************/

public class Main {

    //Global objects in main class
    public static File file = new File("saveData.txt");
    public static Scanner fileRead;

    static {
        try {
            fileRead = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[31mError save file not found. File cannot be read.\u001B[0m");
        }
    }

    public static World world;
    public static Clip clip;
    public static boolean finishGame;

    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedAudioFileException, 
            LineUnavailableException {

        //Check if file exists
        if (!file.exists()) {
            System.out.println("\u001B[31mError save file not found. Game cannot be runned.\u001B[0m");
            return;
        }

        //Play title screen music
        titleMusic();

        //Scanner and variables in main method
        Scanner keyInput = new Scanner(System.in);
        String prompt, confirm = null;
        boolean newGame = false;

        //Check if user has completed the game once
        if (fileRead.hasNext()) {
            finishGame = Boolean.parseBoolean(fileRead.nextLine());
        }

        do {

            //Print title screen
            System.out.println("" +
                    " ___     _       _  __  _       _     _   _    __  _       __     ___        \n" +
                    "  | |_| |_   |  |_ /__ |_ |\\ | | \\   / \\ |_   /__ |_ |\\ | (_  |_|  |  |\\ | o \n" +
                    "  | | | |_   |_ |_ \\_| |_ | \\| |_/   \\_/ |    \\_| |_ | \\| __) | | _|_ | \\| o");
            System.out.println("" +
                    "  _   _  __ ___           ___       __    _   _   ___     _           _   _      _  \n" +
                    " |_) |_ /__  |  |\\ | |\\ |  |  |\\ | /__   / \\ |_    | |_| |_   \\    / / \\ |_) |  | \\ \n" +
                    " |_) |_ \\_| _|_ | \\| | \\| _|_ | \\| \\_|   \\_/ |     | | | |_    \\/\\/  \\_/ | \\ |_ |_/ \n" +
                    "");
            if (finishGame) {
                System.out.println("" +
                        "                     /   ))     |\\         )               ).           \n" +
                        "               c--. (\\  ( `.    / )  (\\   ( `.     ).     ( (           \n" +
                        "               | |   ))  ) )   ( (   `.`.  ) )    ( (      ) )          \n" +
                        "               | |  ( ( / _..----.._  ) | ( ( _..----.._  ( (           \n" +
                        " ,-.           | |---) V.'-------.. `-. )-/.-' ..------ `--) \\._        \n" +
                        " | /===========| |  (   |      ) ( ``-.`\\/'.-''           (   ) ``-._   \n" +
                        " | | / / / / / | |--------------------->  <-------------------------_>=-\n" +
                        " | \\===========| |                 ..-'./\\.`-..                _,,-'    \n" +
                        " `-'           | |-------._------''_.-'----`-._``------_.-----'         \n" +
                        "               | |         ``----''            ``----''                  \n" +
                        "               | |                                                       \n" +
                        "               c--`          \n");
            }

            //Print selection menu
            System.out.println("1) New Game");
            if (fileRead.hasNext()) {
                System.out.println("2) Continue");
            }
            prompt = keyInput.nextLine();

            if (prompt.equals("1") && fileRead.hasNext()) {

                do {

                    //Confirm overwriting previous save
                    System.out.println("Existing save data found. Are you sure you want to overwrite? (y/n)");
                    confirm = keyInput.nextLine();

                    //Error trap
                    if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                        System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                        Thread.sleep(500);
                        clearScreen();
                    }

                } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));

                if (confirm.equalsIgnoreCase("n")) {
                    clearScreen();
                }

            } else if (!prompt.equals("1") && (!prompt.equals("2") || !fileRead.hasNext())) {
                //Error trap
                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                Thread.sleep(500);
                clearScreen();
            }

        } while ((!prompt.equals("1") || fileRead.hasNext()) && (!prompt.equals("1") || !confirm.equalsIgnoreCase("y")) &&
                (!prompt.equals("2") || !fileRead.hasNext()));

        //Stop title screen music
        clip.stop();

        //Go to user selection
        switch (prompt) {

            case "1":
                //delete existing save data
                PrintWriter delete = new PrintWriter(file);
                delete.close();
                //create new save data
                world = new World();
                world.start();
                newGame = true;
                //after tutorial, go to case 2
            case "2":
                //load existing save data
                if (!newGame) {
                    loadSave();
                }
                //Play overworld music
                music("overworld.wav");
                //the entire game
                world.navigate();
                //end of program

        }

    }//end of main method

    /*************************
     * Method Name: loadSave
     * Method Description: Loads data from the save file.
     **************************/
    public static void loadSave() {

        //Objects in loadSave
        ArrayList<Character> loadParty = new ArrayList<>();
        ArrayList<Character> loadCurrentParty = new ArrayList<>();
        ArrayList<ArrayList<String>> loadInventory = new ArrayList<>();
        ArrayList<ArrayList<String>> loadWorld = new ArrayList<>();
        ArrayList<ArrayList<String>> loadMap = new ArrayList<>();
        ArrayList<ArrayList<String>> loadVillages = new ArrayList<>();

        //Variables in loadSave
        String[] data;
        String dataLine;
        int row = 0, column = 0, xPos = 0, yPos = 0, dragonX = 0, dragonY = 0, partyRow = 0, invRow = 0, worldRow = 0,
                mapRow = 0, villageRow = 0;
        boolean finishTutorial = false, finishVillage = false, finishDungeon = false, towerSpawn = false, player = true;

        //Read Player and Party Data
        fileRead.nextLine();
        while (true) {

            dataLine = fileRead.nextLine();
            if (dataLine.equals("Inventory")) {
                break;
            }

            //Store each row/line of party members in string and tokenize
            data = dataLine.split(" ");

            String name = data[0] + " " + data[1];
            String weapon = data[13] + " " + data[14] + " " + data[15] + " " + data[16];
            String armor = data[17] + " " + data[18] + " " + data[19] + " " + data[20];

            //Load player data first, then party members afterwards
            if (player) {
                loadParty.add(new Player(name, Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]),
                        Integer.parseInt(data[10]), Integer.parseInt(data[11]), Integer.parseInt(data[12]),
                        weapon, armor, Boolean.parseBoolean(data[21])));
                player = false;
            } else {
                loadParty.add(new Party(name, Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]),
                        Integer.parseInt(data[10]), Integer.parseInt(data[11]), Integer.parseInt(data[12]),
                        weapon, armor, Boolean.parseBoolean(data[21])));
            }

            //Add party members to current party members
            if (Boolean.parseBoolean(data[21])) {
                loadCurrentParty.add(loadParty.get(partyRow));
            }

            //Next party member
            partyRow++;

        }

        //Read Inventory Data
        while (true) {

            dataLine = fileRead.nextLine();
            if (dataLine.equals("World Data")) {
                break;
            }

            //Store each row/line of inventory in string and tokenize
            data = dataLine.split(" ");

            //Add respective item by their amount
            loadInventory.add(new ArrayList<>());
            String name = data[0] + " " + data[1];
            int amount = Integer.parseInt(data[2]);
            for (int i = 0; i < amount; i++) {
                loadInventory.get(invRow).add(name);
            }

            //Next item
            invRow++;

        }

        //Read World Fields Data
        while (true) {

            dataLine = fileRead.nextLine();
            if (dataLine.equals("World")) {
                break;
            }

            //Store single line in string and tokenize
            data = dataLine.split(" ");

            //Load respective fields
            row = Integer.parseInt(data[0]);
            column = Integer.parseInt(data[1]);
            xPos = Integer.parseInt(data[2]);
            yPos = Integer.parseInt(data[3]);
            dragonX = Integer.parseInt(data[4]);
            dragonY = Integer.parseInt(data[5]);
            finishTutorial = Boolean.parseBoolean(data[6]);
            finishVillage = Boolean.parseBoolean(data[7]);
            finishDungeon = Boolean.parseBoolean(data[8]);
            towerSpawn = Boolean.parseBoolean(data[9]);

        }

        //Read World Data
        while (true) {

            dataLine = fileRead.nextLine();
            if (dataLine.equals("Map")) {
                break;
            }

            //Store each row/line of world in string and tokenize
            data = dataLine.split(" ");

            //Add each tile to current row of loaded world
            loadWorld.add(new ArrayList<>());
            for (int i = 0; i < data.length; i++) {
                loadWorld.get(worldRow).add(data[i]);
            }

            //Next row/line
            worldRow++;

        }

        //Read Map Data
        while (true) {

            dataLine = fileRead.nextLine();
            if (dataLine.equals("Villages")) {
                break;
            }

            //Store each row/line of map in string and tokenize
            data = dataLine.split(" ");

            //Add each tile to current row of loaded map
            loadMap.add(new ArrayList<>());
            for (int i = 0; i < data.length; i++) {
                loadMap.get(mapRow).add(data[i]);
            }

            //Next row/line
            mapRow++;

        }

        //Read Villages Data
        if (fileRead.hasNext()) {

            while (fileRead.hasNext()) {

                //Store each village and their quests in string and tokenize
                dataLine = fileRead.nextLine();
                data = dataLine.split("[|]");

                //Load each village and their respective quests
                loadVillages.add(new ArrayList<>());
                for (int i = 0; i < data.length; i++) {
                    loadVillages.get(villageRow).add(data[i]);
                }

                //Next village
                villageRow++;

            }

        }

        //Close scanner object
        fileRead.close();

        //Load save data
        world = new World(finishGame, loadParty, loadCurrentParty, loadInventory, loadWorld, loadMap, loadVillages,
                row, column, xPos, yPos, dragonX, dragonY, finishTutorial, finishVillage, finishDungeon, towerSpawn);

    }//end of loadSave

    /*************************
     * Method Name: titleMusic
     * Method Description: Plays epic title screen music.
     **************************/
    public static void titleMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //Create file object for background music
        File musicFile = new File("title.wav");

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
        gainControl.setValue(-20.0f);

        //Play on loop until program ends or stopped
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }//end of titleMusic

    /*************************
     * Method Name: music
     * Method Description: Plays epic music in-game.
     * @param filename - Name of the wav file.
     **************************/
    public static void music(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        //Create file object for background music
        File musicFile = new File(filename);

        //Check if file exists
        if (!musicFile.exists()) {
            System.out.println("\u001B[31mError music file not found. Game cannot be runned.\u001B[0m");
            System.exit(0);
        }

        //Stop previous music
        clip.stop();

        //Create audioInputStream object to get audio from file
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);

        //Create clip object to allow music to be played from file
        clip = AudioSystem.getClip();

        //Open clip
        clip.open(audioInput);

        //Create floatControl object to set volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30.0f);

        //Play on loop until program ends or stopped
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }//end of music

    /*************************
     * Method Name: clearScreen
     * Method Description: Clears the console screen.
     **************************/
    public static void clearScreen() {

        for (int i = 0; i < 40; i++) {
            System.out.println("");
        }

    }//end of clearScreen

}//end of main class