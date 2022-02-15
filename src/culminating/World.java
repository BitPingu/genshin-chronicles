package culminating;
//V3
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class World {

    //Field objects in World
    private final Scanner keyInput = new Scanner(System.in);
    private final Random random = new Random();

    private ArrayList<ArrayList<String>> world = new ArrayList<>();
    private ArrayList<ArrayList<String>> map = new ArrayList<>();
    private ArrayList<ArrayList<String>> villagesVisited = new ArrayList<>();

    private ArrayList<Character> partyMembers = new ArrayList<>();
    private ArrayList<Character> currentPartyMembers = new ArrayList<>();

    //Field variables in World
    private int row, column, xPos, yPos, dragonX, dragonY;
    private boolean finishGame, finishTutorial, finishVillage, finishDungeon, towerSpawn;
    private String[][] weapons = {
            {"\uD83D\uDDE1 Wooden Sword 14", "\uD83D\uDDE1 Iron Sword 21", "\uD83D\uDDE1 Diamond Sword 28",
                    "\uD83D\uDDE1 Master Sword 35", "\uD83D\uDDE1 True Monado 42"},
            {"\uD83E\uDD4D Wooden Staff 4", "\uD83E\uDD4D Amethyst Staff 8", "\uD83E\uDD4D Ruby Staff 12",
                    "\uD83E\uDD4D Caduceus Staff 16", "\uD83E\uDD4D True Thyrsus 20"},
            {"\uD83C\uDFF9 Wooden Bow 5", "\uD83C\uDFF9 Silver Bow 10", "\uD83C\uDFF9 Golden Bow 15",
                    "\uD83C\uDFF9 Light Bow 20", "\uD83C\uDFF9 True Failnaught 25"}
    };
    private String[][] armor = {
            {"\uD83D\uDC55 Worn Shirt 7", "\uD83D\uDC55 Leather Shirt 14", "\uD83C\uDFBD Iron Armor 21",
                    "\uD83C\uDFBD Diamond Armor 28", "\uD83D\uDC55 Champion's Tunic 35"},
            {"\uD83D\uDC57 Worn Dress 4", "\uD83D\uDC57 Leather Dress 8", "\uD83D\uDC58 Monk Robes 12",
                    "\uD83D\uDC58 Mystic Robes 16", "\uD83D\uDC58 Goddess Cloak 20"}
    };


    //Constructors
    public World()
    {
        initWorld();
    }

    public World(boolean fg, ArrayList<Character> p, ArrayList<Character> cp, ArrayList<ArrayList<String>> in,
                 ArrayList<ArrayList<String>> w, ArrayList<ArrayList<String>> m, ArrayList<ArrayList<String>> v, int r,
                 int c, int x, int y, int dx, int dy, boolean f, boolean fv, boolean fd, boolean t) {
        finishGame = fg;
        partyMembers = p;
        currentPartyMembers = cp;
        partyMembers.get(0).setInventory(in);
        world = w;
        map = m;
        villagesVisited = v;
        row = r;
        column = c;
        xPos = x;
        yPos = y;
        dragonX = dx;
        dragonY = dy;
        finishTutorial = f;
        finishVillage = fv;
        finishDungeon = fd;
        towerSpawn = t;
    }

    //Accessors
    public ArrayList<ArrayList<String>> getWorld() {
        return world;
    }

    public ArrayList<ArrayList<String>> getMap() {
        return map;
    }

    public ArrayList<ArrayList<String>> getVillagesVisited() {
        return villagesVisited;
    }

    public ArrayList<Character> getPartyMembers() {
        return partyMembers;
    }

    public ArrayList<Character> getCurrentPartyMembers() {
        return currentPartyMembers;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getDragonX() {
        return dragonX;
    }

    public int getDragonY() {
        return dragonY;
    }

    public boolean getFinishGame() {
        return finishGame;
    }

    public boolean getFinishTutorial() {
        return finishTutorial;
    }

    public boolean getFinishVillage() {
        return finishVillage;
    }

    public boolean getFinishDungeon() {
        return finishDungeon;
    }

    public boolean getTowerSpawn() {
        return towerSpawn;
    }

    public String[][] getWeapons() {
        return weapons;
    }

    public String[][] getArmor() {
        return armor;
    }

    //Mutators
    public void setWorld(ArrayList<ArrayList<String>> w) {
        world = w;
    }

    public void setMap(ArrayList<ArrayList<String>> m) {
        map = m;
    }

    public void setVillagesVisited(ArrayList<ArrayList<String>> v) {
        villagesVisited = v;
    }

    public void setPartyMembers(ArrayList<Character> p) {
        partyMembers = p;
    }

    public void setCurrentPartyMembers(ArrayList<Character> c) {
        currentPartyMembers = c;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setColumn(int c) {
        column = c;
    }

    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
    }

    public void setDragonX(int x) {
        dragonX = x;
    }

    public void setDragonY(int y) {
        dragonY = y;
    }

    public void setFinishGame(boolean f) {
        finishGame = f;
    }

    public void setFinishTutorial(boolean f) {
        finishTutorial = f;
    }

    public void setFinishVillage(boolean f) {
        finishVillage = f;
    }

    public void setFinishDungeon(boolean f) {
        finishDungeon = f;
    }

    public void setTowerSpawn(boolean t) {
        towerSpawn = t;
    }

    public void setWeapons(String[][] w) {
        weapons = w;
    }

    public void setArmor(String[][] a) {
        armor = a;
    }

    /*************************
     * Method Name: initWorld
     * Method Description: Initializes the world through a 5x5 grid and sets player and dragon starting position.
     **************************/
    public void initWorld() {

        //Starting area
        for (int i=0; i<5; i++) {
            world.add(new ArrayList<>());
            map.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                world.get(i).add("\uD83D\uDFE9");//Grass
                map.get(i).add("\uD83D\uDFE9");//Grass
            }
        }

        //Player starting position
        row = 2;
        column = 2;

        //Dragon spawning position
        dragonX = random.nextInt(101) - 45;
        dragonY = random.nextInt(101) - 45;

    }//end of initWorld

    /*************************
     * Method Name: start
     * Method Description: Prints the text dialogue at the start of the game.
     **************************/
    public void start() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        //String variables in start
        String name, connectedName;

        //Player joins
        partyMembers.add(new Player("\uD83E\uDDDD Traveller", 1, 128, 15, 42, 12, 5, 0, 4,
                0, 128, 15, "\uD83E\uDD1B Mighty Fists 3", armor[0][0], true));
        currentPartyMembers.add(partyMembers.get(partyMembers.size()-1));

        //Robin joins (eventually)
        partyMembers.add(new Party("\uD83E\uDDDA Girl", 1, 85, 25, 30, 12, 8, 0, 3,
                0, 70, 25, weapons[1][0], armor[1][0], true));
        currentPartyMembers.add(partyMembers.get(partyMembers.size()-1));

        //Robin and Ogre starting position
        world.get(0).set(2, "\uD83E\uDDDA");//Fairy
        world.get(0).set(3, "\uD83D\uDC79");//Ogre

        Main.clearScreen();

        //Start of the game - waking up and saving person
        System.out.println("\nYou wake up on a grassy field to the sound of a girl screaming.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Someone please help me!");
        Thread.sleep(2000);
        Main.music("overworld.wav");
        navigate();

        //After first battle - meeting the waifu and setting the name
        Main.clearScreen();
        System.out.println(partyMembers.get(1).getName() + ": Thanks for saving me! My name is \uD83E\uDDDA "
                + "Robin What is yours?");
        Thread.sleep(1500);
        partyMembers.get(1).setName("\uD83E\uDDDA Robin");
        System.out.println(partyMembers.get(1).getName() + ": ...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": What?! You don't know your own name? Then I should call " +
                "you...");
        Thread.sleep(1500);

        do {
            //Player sets their name
            System.out.print("My name is: ");
            name = keyInput.nextLine();
            if (name.length() > 8) {
                System.out.println("Name is too long. Please use a different name.");
            }
        } while (name.length() > 8);

        //Prevent spaces
        String[] tokens = name.split(" ");
        connectedName = tokens[0];

        if (connectedName.equals("")) {
            partyMembers.get(0).setName("\uD83E\uDDDD ...");
        } else {
            partyMembers.get(0).setName("\uD83E\uDDDD " + connectedName);
        }

        System.out.println(partyMembers.get(1).getName() + ": I shall call you " + partyMembers.get(0).getName() + "!");
        Thread.sleep(1500);

        //Robin introduces the player to the world, and the goal of the game
        System.out.println(partyMembers.get(1).getName() + ": So what are you doing out here all alone?");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(0).getName() + ": ...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": So you are saying that you just woke "
                + "up and found yourself here...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": with no recollection of anything? That's strange...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Tell you what, I'll show you around the world of Genshin!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Here is a map...");
        System.out.println(partyMembers.get(0).name + " received a \uD83D\uDDFA Map!");
        Thread.sleep(1500);
        partyMembers.get(0).addInventory("\uD83D\uDDFA Map");
        System.out.println(partyMembers.get(1).getName() + ": You will probably need a weapon too...");
        System.out.println(partyMembers.get(0).name + " received a \uD83D\uDDE1 Wooden Sword!");
        partyMembers.get(0).setWeapon(weapons[0][0]);
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Let's look for a village where we can stay for the night.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Onwards!");
        Thread.sleep(2000);
        finishTutorial = true;

        //Replace with grass
        world.get(row).set(column-1, "\uD83D\uDFE9");
        world.get(row).set(column, "\uD83D\uDFE9");

        //Tutorial checkpoint
        save(finishGame, partyMembers, currentPartyMembers, partyMembers.get(0).getInventory(), world,
                map, villagesVisited, row, column, xPos, yPos, dragonX, dragonY, finishTutorial,
                finishVillage, finishDungeon, towerSpawn);
        System.out.println("Game auto saved successfully.");
        Thread.sleep(2000);

        //Return to Main

    }//end of start

    /*************************
     * Method Name: navigate
     * Method Description: Allows the player to explore the world using the wasd keys.
     **************************/
    public void navigate() throws InterruptedException, UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        //Variables in navigate
        String currentPosition;
        String movement, confirm;
        int spawn;
        boolean safe;

        while (true) {

            Main.clearScreen();

            //Get current tile of player location, replace it with player
            currentPosition = world.get(row).get(column);
            world.get(row).set(column, "\uD83E\uDDDD");//Elf

            //Display player info
            System.out.println("Player: " + partyMembers.get(0).getName());
            System.out.println("Lvl: " + partyMembers.get(0).getLevel());
            System.out.println("Exp: " + partyMembers.get(0).getExp());
            System.out.println("\uD83D\uDCB0 Money: $" + partyMembers.get(0).getMoney());
            System.out.println("[wasd]: Move");
            if (finishTutorial) {
                System.out.println("[e]: Party");
                System.out.println("[i]: Inventory");
                System.out.println("[m]: Map");
                System.out.println("[l]: Save");
            }

            //Display player's field of vision
            System.out.println();
            for (int i=row-2; i<row + 3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    System.out.print(world.get(i).get(j) + "\t");
                }
                System.out.println();
            }

            //Movement user input
            System.out.println("x: " + xPos + ", y: " + yPos);
            System.out.print("Movement: ");
            movement = keyInput.nextLine().toLowerCase();

            //Replace player position with original tile
            world.get(row).set(column, currentPosition);

            //Determine user input
            switch (movement) {

                case "a":
                    column--;
                    xPos--;
                    break;
                case "s":
                    row++;
                    yPos--;
                    break;
                case "d":
                    column++;
                    xPos++;
                    break;
                case "w":
                    row--;
                    yPos++;
                    break;
                case "e":
                    if (finishTutorial) {
                        //Check party members
                        Main.clearScreen();
                        party();
                    }
                    break;
                case "i":
                    if (finishTutorial) {
                        //Use inventory
                        Main.clearScreen();
                        partyMembers.get(0).printInventory();
                    }
                    break;
                case "m":
                    if (finishTutorial) {
                        //Use map
                        Main.clearScreen();
                        world.get(row).set(column, "\uD83E\uDDDD");
                        map();
                        world.get(row).set(column, currentPosition);
                    }
                    break;
                case "l":
                    if (finishTutorial) {
                        //Save progress
                        save(finishGame, partyMembers, currentPartyMembers, partyMembers.get(0).getInventory(), world,
                                map, villagesVisited, row, column, xPos, yPos, dragonX, dragonY, finishTutorial,
                                finishVillage, finishDungeon, towerSpawn);

                        do {
                            System.out.println("Game saved successfully. You are filled with determination.");
                            System.out.println("Continue? (y/n)");
                            confirm = keyInput.nextLine();
                            if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                                //Error trap
                                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                                Thread.sleep(500);
                                Main.clearScreen();
                            }
                        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));

                        if (confirm.equalsIgnoreCase("n")) {
                            //Return to Main
                            return;
                        }
                    }
                default:

            }

            //Generate world as player moves
            if (column - 1 == 0) {
                column++;
                for (int j=0; j<world.size(); j++) {
                    world.get(j).add(0, chunk());
                    map.get(j).add(0, "?");
                }
            } else if (row + 1 == world.size()-1) {
                world.add(new ArrayList<>());
                map.add(new ArrayList<>());
                for (int j=0; j<world.get(row).size(); j++) {
                    world.get(row + 2).add(chunk());
                    map.get(row + 2).add("?");
                }
            } else if (column + 1 == world.get(row).size()-1) {
                for (int j=0; j<world.size(); j++) {
                    world.get(j).add(chunk());
                    map.get(j).add("?");
                }
            } else if (row - 1 == 0) {
                row++;
                world.add(0, new ArrayList<>());
                map.add(0, new ArrayList<>());
                for (int j=0; j<world.get(row).size(); j++) {
                    world.get(row - 2).add(chunk());
                    map.get(row - 2).add("?");
                }
            }

            //Add explored area to map
            for (int i=row-2; i<row + 3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    if (map.get(i).get(j).equals("?")) {
                        map.get(i).set(j, world.get(i).get(j));
                    }
                }
            }

            //Prevent player from escaping during tutorial or exceeding map limit
            if ((!finishTutorial && (xPos < -2 || yPos < -2 || xPos > 2 || yPos > 2)) ||
                    xPos < -50 || yPos < -50 || xPos > 50 || yPos > 50) {

                if ("a".equals(movement)) {
                    column++;
                    xPos++;
                } else if ("s".equals(movement)) {
                    row--;
                    yPos++;
                } else if ("d".equals(movement)) {
                    column--;
                    xPos--;
                } else if ("w".equals(movement)) {
                    row++;
                    yPos--;
                }

                if (!finishTutorial) {
                    System.out.println("Where are you going? You can't leave the girl!");
                    Thread.sleep(1500);
                } else {
                    System.out.println("You can't go any further!");
                    Thread.sleep(1500);
                }

            }

            //If player is within radius of the tower
            if (!towerSpawn) {
                for (int i = xPos - 2; i < xPos + 6; i++) {
                    for (int j = yPos - 2; j < yPos + 6; j++) {
                        if (dragonX == i && dragonY == j) {
                            world.get(row + 1).set(column + 1, "\uD83D\uDDFC");
                            towerSpawn = true;
                        }
                    }
                }
            }

            //Initiate event depending on player position
            switch (world.get(row).get(column)) {

                case "\uD83E\uDDDA":
                    //Save Girl
                    System.out.println("\uD83E\uDDDA Girl: Please help me!");
                    Thread.sleep(1500);
                    break;

                case "\uD83C\uDFDB":
                    //Go to dungeon
                    if (!dungeon()) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83D\uDEA8":
                    //Completed dungeon
                    System.out.println("You have already explored this dungeon.");
                    Thread.sleep(1500);
                    break;

                case "\uD83C\uDFD8":
                    //Go to village
                    village();
                    break;

                case "\uD83D\uDDFC":
                    //Final Boss
                    if (!finishDungeon) {
                        System.out.println("You feel an ominous aura surrounding the tower, best not to approach it.");
                        Thread.sleep(1500);
                    } else {
                        System.out.println("You have arrived at the \uD83D\uDC32 Dragon's tower.");
                        Thread.sleep(1500);
                        System.out.println("Once you enter, you will be facing the \uD83D\uDC32 Dragon.");
                        Thread.sleep(1500);
                        System.out.println("It is recommended that you and your party must be well equipped and at " +
                                "least Level 12.");
                        Thread.sleep(1500);
                        do {
                            System.out.println("Are you ready? (y/n)");
                            confirm = keyInput.nextLine();
                            if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                                //Error trap
                                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                                Thread.sleep(500);
                                Main.clearScreen();
                            }
                        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
                        if (confirm.equalsIgnoreCase("y")) {
                            if (!end()) {
                                //If player loses
                                return;
                            }
                            //Return to Main
                            return;
                        }
                    }
                    break;

                case "\uD83E\uDDDF":
                    //Fight Zombie
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83E\uDDDF Zombie!");
                    Thread.sleep(1500);
                    if (!battle("\uD83E\uDDDF Zombie")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83D\uDC7A":
                    //Fight Goblin
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83D\uDC7A Goblin!");
                    Thread.sleep(1500);
                    if (!battle("\uD83D\uDC7A Goblin")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83D\uDC79":
                    //Fight Ogre
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83D\uDC79 Ogre!");
                    Thread.sleep(1500);
                    if (!battle("\uD83D\uDC79 Ogre")) {
                        //If player loses
                        return;
                    }
                    if (!finishTutorial) {
                        //Return to start
                        return;
                    }
                    break;

                case "\uD83D\uDC7B":
                    //Fight Ghost
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83D\uDC7B Ghost!");
                    Thread.sleep(1500);
                    if (!battle("\uD83D\uDC7B Ghost")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83D\uDC7D":
                    //Fight Alien
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83D\uDC7D Alien!");
                    Thread.sleep(1500);
                    if (!battle("\uD83D\uDC7D Alien")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83D\uDC19":
                    //Fight Octopus
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83D\uDC19 Octopus!");
                    Thread.sleep(1500);
                    if (!battle("\uD83D\uDC19 Octopus")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83D\uDC80":
                    //Fight Skeleton
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83D\uDC80 Skeleton!");
                    Thread.sleep(1500);
                    if (!battle("\uD83D\uDC80 Skeleton")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83E\uDD16":
                    //Fight Golem
                    System.out.println(partyMembers.get(0).getName() + " encountered a \uD83E\uDD16 Golem!");
                    Thread.sleep(1500);
                    if (!battle("\uD83E\uDD16 Golem")) {
                        //If player loses
                        return;
                    }
                    break;

                case "\uD83E\uDD62":
                    //Collect wood
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83E\uDD62 Wood");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83E\uDD62 Wood");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83E\uDD4C":
                    //Collect stone
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83E\uDD4C Stone");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83E\uDD4C Stone");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF4E":
                    //Collect apples
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83C\uDF4E Apples");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83C\uDF4E Apples");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83D\uDC8E":
                    //Collect ore
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83D\uDC8E Ore");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83D\uDC8E Ore");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF44":
                    //Collect mushrooms
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83C\uDF44 Mushrooms");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83C\uDF44 Mushrooms");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83D\uDC1B":
                    //Collect critters
                    System.out.println(partyMembers.get(0).getName() + " caught some \uD83D\uDC1B Critters");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83D\uDC1B Critters");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF52":
                    //Collect berries
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83C\uDF52 Berries");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83C\uDF52 Berries");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83C\uDF3F":
                    //Collect herbs
                    System.out.println(partyMembers.get(0).getName() + " collected some \uD83C\uDF3F Herbs");
                    Thread.sleep(1500);
                    partyMembers.get(0).addInventory("\uD83C\uDF3F Herbs");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

                case "\uD83E\uDDF0":
                    //Open chest
                    System.out.println(partyMembers.get(0).getName() + " found a chest!");
                    Thread.sleep(1500);
                    reward("chest");
                    world.get(row).set(column, "\uD83C\uDF33");
                    break;

            }

            //Check starting area, village, dungeon, completed dungeon, and tower within FOV
            safe = false;
            for (int i=row-2; i<row + 3; i++) {
                for (int j=column-2; j<column+3; j++) {
                    if (world.get(i).get(j).equals("\uD83D\uDFE9") || world.get(i).get(j).equals("\uD83C\uDFD8") ||
                            world.get(i).get(j).equals("\uD83C\uDFDB") || world.get(i).get(j).equals("\uD83D\uDEA8") ||
                            world.get(i).get(j).equals("\uD83D\uDDFC")) {
                        safe = true;
                        break;
                    }
                }
            }

            //Spawn enemies and/or items when there are no significant areas in FOV
            if (finishTutorial && ("a".equals(movement) || "s".equals(movement) || "d".equals(movement) ||
                    "w".equals(movement)) && !safe) {

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

    }//end of navigate

    /*************************
     * Method Name: chunk
     * Method Description: Generates a random chunk in the world.
     * @return chunk - chunk to be generated
     **************************/
    public String chunk() {

        //Variables in chunk
        int generation;
        String chunk;

        //Determine random generation
        generation = random.nextInt(100)+1;

        if (generation <= 67) {
            //Tree
            chunk = "\uD83C\uDF33";
        } else if (generation <= 97) {
            //Alt Tree
            chunk = "\uD83C\uDF32";
        } else if (generation <= 99) {
            //Dungeon
            chunk = "\uD83C\uDFDB";
        } else {
            //Village
            chunk = "\uD83C\uDFD8";
        }

        return chunk;

    }//end of chunk

    /*************************
     * Method Name: spawnEnemy
     * Method Description: Spawns a random enemy within the player's field of vision.
     **************************/
    public void spawnEnemy() {

        //Variables in spawnEnemy
        int spawn, enemyRow = 0, enemyColumn = 0;
        String enemy, enemyIcon;

        //Determine enemy to be spawned from genEnemy method
        enemy = genEnemy();

        //Tokenize to get enemy type
        String[] tokens = enemy.split(" ");
        enemyIcon = tokens[0];

        //Determine random spawn coordinates
        spawn = random.nextInt(24)+1;

        switch (spawn) {

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

        //Spawn enemy in world
        world.get(enemyRow).set(enemyColumn, enemyIcon);

    }//end of spawnEnemy

    /*************************
     * Method Name: spawnItem
     * Method Description: Spawns a random item within the player's field of vision (and if lucky a chest).
     **************************/
    public void spawnItem() {

        //Variables in spawnItem
        int spawn, itemRow = 0, itemColumn = 0;
        String item;

        //Determine random enemy
        spawn = random.nextInt(100)+1;

        if (spawn <= 12) {
            //Wood
            item = "\uD83E\uDD62";
        } else if (spawn <= 24) {
            //Stone
            item = "\uD83E\uDD4C";
        } else if (spawn <= 36) {
            //Apples
            item = "\uD83C\uDF4E";
        } else if (spawn <= 48) {
            //Ore
            item = "\uD83D\uDC8E";
        } else if (spawn <= 60) {
            //Mushrooms
            item = "\uD83C\uDF44";
        } else if (spawn <= 72) {
            //Critters
            item = "\uD83D\uDC1B";
        } else if (spawn <= 84) {
            //Berries
            item = "\uD83C\uDF52";
        } else if (spawn <= 96) {
            //Herbs
            item = "\uD83C\uDF3F";
        } else {
            //Chest
            item = "\uD83E\uDDF0";
        }

        //Determine random spawn coordinates
        spawn = random.nextInt(24)+1;

        switch (spawn) {

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

        //Spawn item in world
        world.get(itemRow).set(itemColumn, item);

    }//end of spawnItem

    /*************************
     * Method Name: map
     * Method Description: Displays the entire world that has been explored.
     **************************/
    public void map() {

        //String variable to hold tile of player position
        String currentPosition;

        //Get current tile of player location, replace it with player
        currentPosition = map.get(row).get(column);
        map.get(row).set(column, "\uD83E\uDDDD");//Elf

        //Print map
        for (int i=0; i<map.get(0).size()*1.2; i++) {
            System.out.print(" ");
        }
        System.out.println("~The World of Genshin~\n");
        for (int i=0; i<map.size(); i++) {
            for (int j=0; j<map.get(i).size(); j++) {
                System.out.print(map.get(i).get(j) + "\t");
            }
            System.out.println();
        }

        System.out.println("\nType anything to exit.");
        keyInput.nextLine();

        //Replace player position with original tile
        map.get(row).set(column, currentPosition);

    }//end of map

    /*************************
     * Method Name: party
     * Method Description: Displays your party members and allow swapping.
     **************************/
    public void party() throws InterruptedException {

        //Variables in party
        String prompt;
        int memberAdd, memberReplace;

        do {

            //Prints the party members and what they have
            System.out.println("In Your Party\n");
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", currentPartyMembers.get(i).getName());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Lvl: " + currentPartyMembers.get(i).getLevel());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "HP: " + currentPartyMembers.get(i).getCurrentHealth() + "/" +
                        currentPartyMembers.get(i).getHealth());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "HP: " + currentPartyMembers.get(i).getCurrentMp() + "/" +
                        currentPartyMembers.get(i).getMp());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Atk: " + currentPartyMembers.get(i).getStrength());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Def: " + currentPartyMembers.get(i).getDefence());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Spd: " + currentPartyMembers.get(i).getSpeed());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Exp: " + currentPartyMembers.get(i).getExp() + "/" +
                        (currentPartyMembers.get(i).getLevel() * 20));
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Dices: " + currentPartyMembers.get(i).getDices());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Weapon: ");
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", currentPartyMembers.get(i).getWeapon());
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", "Armour: ");
            }
            System.out.println();
            for (int i=0; i<currentPartyMembers.size(); i++) {
                System.out.format("%-20s", currentPartyMembers.get(i).getArmor());
            }
            System.out.println("\n");

            //User prompt to swap or return
            System.out.println("\n1) Swap Party Members");
            System.out.println("Type 0 to return.");
            prompt = keyInput.nextLine();

            if (prompt.equals("1")) {

                if (partyMembers.size() < 4) {
                    System.out.println("You don't have any other party members.");
                    Thread.sleep(1000);
                } else {
                    //Adding a party member
                    System.out.println("Select a party member to add.");
                    do {
                        for (int i = 1; i < partyMembers.size(); i++) {
                            if (!partyMembers.get(i).getInCurrentParty()) {
                                System.out.println(i + ") " + partyMembers.get(i).getName());
                            }
                        }
                        memberAdd = Integer.parseInt(keyInput.nextLine());
                    } while (memberAdd <= 0 || memberAdd >= partyMembers.size() ||
                            partyMembers.get(memberAdd).inCurrentParty);

                    //Replacing a party member
                    System.out.println("Select a party member to replace.");
                    do {
                        for (int i = 1; i < currentPartyMembers.size(); i++) {
                            System.out.println(i + ") " + currentPartyMembers.get(i).getName());
                        }
                        memberReplace = Integer.parseInt(keyInput.nextLine());
                    } while (memberReplace <= 0 || memberReplace >= currentPartyMembers.size());

                    System.out.println(currentPartyMembers.get(memberReplace).getName() + " switched with "
                            + partyMembers.get(memberAdd).getName());
                    Thread.sleep(1000);

                    //Swap party members, set respective boolean values
                    currentPartyMembers.get(memberReplace).setInCurrentParty(false);
                    currentPartyMembers.set(memberReplace, partyMembers.get(memberAdd));
                    currentPartyMembers.get(memberReplace).setInCurrentParty(true);

                }

            }

        } while (!prompt.equals("0"));

    }

    /*************************
     * Method Name: battle
     * Method Description: Displays the battle system.
     * @param enemyType - enemy to be fought
     **************************/
    public boolean battle(String enemyType) throws InterruptedException, UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        //Boolean variable to determine outcome of battle
        boolean win = false;

        //Create enemy object based on type
        Character enemy = new Enemy(enemyType, currentPartyMembers.get(0).getLevel(), weapons[0][0], armor[0][0]);

        //if user is way lower than the average level we gave it, we make it a bit harder
        if (enemyType.equals("\uD83D\uDC32 Dragon") && currentPartyMembers.get(0).getLevel() < 10)
        {
            enemy.setStrength(enemy.getStrength() + 50);
            enemy.setDefence(enemy.getDefence() + 15);
        }

        if (!enemyType.equals("\uD83D\uDC32 Dragon")) {
            //Play battle music
            Main.music("battle.wav");
        }

        if (!finishTutorial) {
            //Prints during tutorial
            System.out.println(partyMembers.get(1).getName() + ": Wait, you know how to fight?");
            Thread.sleep(1500);
            System.out.println(partyMembers.get(1).getName() + ": Ok! I'm a Cleric, so I can heal you if you get " +
                    "injured.");
            Thread.sleep(1500);
        } else if (enemyType.equals("\uD83D\uDC32 Dragon") && !finishDungeon) {
            //Prints during events at the village
            System.out.println("\uD83D\uDC32 Dragon: Raaaugghhrrr!!!!");
            Thread.sleep(1500);
            System.out.println(partyMembers.get(1).getName() + ": Actually, I'm not feeling too " +
                    "confident about this...");
            Thread.sleep(1500);
            System.out.println(partyMembers.get(1).getName() + ": But with the two of us, I believe " +
                    "we can defeat it!");
            Thread.sleep(1500);
            enemy.setDefence(99999999);
        }

        //Increase stats of current party members based on equipment
        for (int i=0; i<currentPartyMembers.size(); i++) {
            currentPartyMembers.get(i).calcWeapon(currentPartyMembers.get(i).getWeapon(), true);
            currentPartyMembers.get(i).calcArmor(currentPartyMembers.get(i).getWeapon(), true);
        }

        //Loops through Party Members' and Enemy turns
        while (true) {

            //Loops through each Party Members' turn
            for (int j = 0; j < currentPartyMembers.size(); j++) {
                if (currentPartyMembers.get(j).fight(currentPartyMembers, enemy)) {
                    //If one of the Party Members defeats the enemy
                    win = true;
                    break;
                }
            }

            if (win) {

                //Player (and Team Members) win
                System.out.println(enemy.name + " dies!");
                Thread.sleep(2000);

                //Returns normal stats to all party members if a buff is still active
                if (currentPartyMembers.get(0).getSpecial()) {
                    currentPartyMembers.get(0).setSpecial(false);
                    currentPartyMembers.get(0).setCounter(0);
                    
                    //Neutralizes the stats for all party members
                    for (int j = 0; j < currentPartyMembers.size(); j++) {
                        currentPartyMembers.get(j).strength -= currentPartyMembers.get(0).getSpecialAtk();
                        currentPartyMembers.get(j).defence -= currentPartyMembers.get(0).getSpecialDef();
                    }
                }

                //Print EXP and money
                System.out.println("You gained " + enemy.getExp() + " EXP");
                System.out.println("You gained \uD83D\uDCB0 $" + enemy.getMoney());
                Thread.sleep(2000);

                //Distribute EXP to all party members and reset their MP and unequips weapon and armor stat bonus
                System.out.println();
                for (int j = 0; j < currentPartyMembers.size(); j++)
                {
                    currentPartyMembers.get(j).calcWeapon(currentPartyMembers.get(j).getWeapon(), false);
                    currentPartyMembers.get(j).calcArmor(currentPartyMembers.get(j).getWeapon(), false);
                    currentPartyMembers.get(j).setCurrentMp(currentPartyMembers.get(j).getMp());
                    currentPartyMembers.get(j).gainExpMoney(enemy.getExp(), enemy.getMoney());
                    currentPartyMembers.get(j).checkLvl();
                }

                if (!enemyType.equals("\uD83D\uDC32 Dragon")) {
                    //Remove enemy from map (replace it with a tree)
                    world.get(row).set(column, "\uD83C\uDF33");
                    //Play overworld music
                    Main.music("overworld.wav");
                }

                //Return to method invoked from
                return true;

            }
            
            //If player loses
            if (enemy.fight(currentPartyMembers, currentPartyMembers.get(0))) {

                if (enemyType.equals("\uD83D\uDC32 Dragon") && !finishDungeon) {

                    Main.clearScreen();
                    System.out.println(partyMembers.get(1).getName() + ": Huff... this is too much for us to handle...");
                    Thread.sleep(1500);
                    System.out.println("???: Hey! You guys over there! Need a hand?");
                    Thread.sleep(1500);
                    System.out.println("You look to see a man holding a bow run towards you.");
                    Thread.sleep(1500);
                    System.out.println("???: Take this! Thundah Bullet!");
                    Thread.sleep(1500);
                    System.out.println("The man fires a volley of arrows at the \uD83D\uDC32 Dragon.");
                    Thread.sleep(1500);
                    System.out.println("\uD83D\uDC32 Dragon: Raaaugghhrrr!!!!");
                    Thread.sleep(1500);
                    System.out.println("You watch the \uD83D\uDC32 Dragon fly away.");
                    Thread.sleep(1500);

                    //Play village music
                    Main.music("village.wav");

                    //Return to village
                    return true;

                } else {
                    System.out.println("GAME OVER");
                    System.out.println(partyMembers.get(0).getName() + "! You cannot give up just yet...");
                    //Return to navigation (then afterwards Main to end program)
                    return false;
                }

            }

        }

    }

    /*************************
     * Method Name: dungeon
     * Method Description: Displays the dungeon.
     * @return - boolean value when player dies
     **************************/
    public boolean dungeon() throws InterruptedException, UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        //String variable for user prompt
        String prompt = "n";

        //User prompt to enter dungeon
            if (!finishVillage) {
                System.out.println("You cannot enter dungeons yet.");
                Thread.sleep(1500);
            } else {
                do {
                    System.out.println("You arrived at a dungeon. Will you enter? (y/n)");
                    prompt = keyInput.nextLine();
                    if (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n")) {
                        //Error trap
                        System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                        Thread.sleep(500);
                        Main.clearScreen();
                    }
                } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));
            }

        if (prompt.equalsIgnoreCase("y")) {

            //Play dungeon music
            Main.music("dungeon.wav");

            if (!finishDungeon) {

                Main.clearScreen();

                //Train with Claude
                System.out.println("As you enter the dungeon, you start to hear creatures lurking behind the walls.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": This underground chamber is what ya call a " +
                        "dungeon.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": You can find these all over the world of " +
                        "Genshin.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Inside these corridors, you will be facing " +
                        "waves of enemies non-stop.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": As dangerous as it is, it is a great place to " +
                        "train.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": The legends say that once you reach the end...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": you will be blessed with a gift from the Goddess.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": But I've heard many people who attempted " +
                        "have never returned...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Are you guys ready?");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": I was born ready!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(0).getName() + ": ...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": ...I guess I'll take that as a yes.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Ok gang! Lets move!");
                Thread.sleep(2000);

            }

            //Fight three random enemies
            for (int i=0; i<3; i++) {
                if (!battle(genEnemy())) {
                    //If player loses
                    return false;
                }
            }

            //Obtain reward at the end
            System.out.println(partyMembers.get(0).getName() + " has completed the dungeon!");
            Thread.sleep(1500);
            reward("quest");

            //Play dungeon music
            Main.music("dungeon.wav");

            if (!finishDungeon) {
                //Story continued
                System.out.println(partyMembers.get(1).getName() + ": Huff... puff... I can't believe we got through " +
                        "all of that alive...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Wow, great job guys!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": I have to admit I was a little doubtful that you " +
                        "guys would've been able to make it out...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": But here we are!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": And now that I have sufficient data on your " +
                        "combat abilities...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": I think you two are worthy of having me in your " +
                        "team!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": Woah really?! Its an honor!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(0).getName() + ": ...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Alright, so our next objective should be finding " +
                        "and taking down that dragon once and for all.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Using advanced intel, I've managed to roughly " +
                        "locate the area where the \uD83D\uDC32 Dragon is!");
                System.out.println(partyMembers.get(0).name + " received the \uD83D\uDC32 Dragon's Location!");
                partyMembers.get(0).addInventory("\uD83D\uDC32 x:" + dragonX + ",y:" + dragonY);
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": That dragon has caused enough malice in this " +
                        "world, so we shall banish it!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(2).getName() + ": Lets go!");
                Thread.sleep(2000);
                finishDungeon = true;

                //Dungeon checkpoint
                save(finishGame, partyMembers, currentPartyMembers, partyMembers.get(0).getInventory(), world,
                        map, villagesVisited, row, column, xPos, yPos, dragonX, dragonY, finishTutorial,
                        finishVillage, finishDungeon, towerSpawn);
                System.out.println("Game auto saved successfully.");
                Thread.sleep(2000);

            }

            //Set player position as completed dungeon
            world.get(row).set(column, "\uD83D\uDEA8");

            //Play overworld music
            Main.music("overworld.wav");

        }

        return true;

    }//end of dungeon

    /*************************
     * Method Name: genEnemy
     * Method Description: Generates a random enemy.
     * @return enemy - generated enemy
     **************************/
    public String genEnemy() {

        //Variables in genEnemy
        int generation;
        String enemy;

        //Determine random enemy
        generation = random.nextInt(100)+1;

        if (generation <= 30) {
            //Zombie
            enemy = "\uD83E\uDDDF Zombie";
        } else if (generation <= 40) {
            //Goblin
            enemy = "\uD83D\uDC7A Goblin";
        } else if (generation <= 50) {
            //Ogre
            enemy = "\uD83D\uDC79 Ogre";
        } else if (generation <= 60) {
            //Ghost
            enemy = "\uD83D\uDC7B Ghost";
        } else if (generation <= 70) {
            //Alien
            enemy = "\uD83D\uDC7D Alien";
        } else if (generation <= 80) {
            //Octopus
            enemy = "\uD83D\uDC19 Octopus";
        } else if (generation <= 90) {
            //Skeleton
            enemy = "\uD83D\uDC80 Skeleton";
        } else {
            //Golem
            enemy = "\uD83E\uDD16 Golem";
        }

        return enemy;

    }//end of genEnemy

    /*************************
     * Method Name: village
     * Method Description: Displays the village.
     **************************/
    public void village() throws UnsupportedAudioFileException, IOException, LineUnavailableException,
            InterruptedException {

        //ArrayList for available quests in current village
        ArrayList<String> quests = null;

        //Variables in village
        String prompt;
        int newQuests, questSelect;
        boolean visitedVillage = false, done;

        //User prompt to enter village
        do {
            System.out.println("You arrived at a village. Will you enter? (y/n)");
            prompt = keyInput.nextLine();
            if (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n")) {
                //Error trap
                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                Thread.sleep(500);
                Main.clearScreen();
            }
        } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));

        if (prompt.equalsIgnoreCase("y")) {

            Main.clearScreen();

            //Play village music
            Main.music("village.wav");

            //Check if village is already visited
            for (int i=0; i<villagesVisited.size(); i++) {

                if (villagesVisited.get(i).contains(xPos + "," + yPos)) {
                    //Retrieve available quests for specific village
                    quests = villagesVisited.get(i);
                    visitedVillage = true;
                    break;
                }

            }

            //If new village
            if (!visitedVillage) {

                //Add to villages visited
                villagesVisited.add(new ArrayList<>());
                villagesVisited.get(villagesVisited.size()-1).add(xPos + "," + yPos);

                //Assign random number of quests to village
                newQuests = random.nextInt(3) + 1;
                for (int j=0; j<newQuests; j++) {
                    villagesVisited.get(villagesVisited.size()-1).add(quest());
                }
                quests = villagesVisited.get(villagesVisited.size()-1);

            }

            if (!finishVillage) {

                //If first village (continue story)
                System.out.println("As you enter the village, you start to hear a community of people.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": This is a village!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": Villages are located all over the world of " +
                        "Genshin.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": It's a safe place to stop by during your journey.");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": You can rest at Inns to restore health, purchase " +
                        "equipment at the local store...");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": And talk to villagers whenever they need help!");
                Thread.sleep(1500);
                System.out.println(partyMembers.get(1).getName() + ": But first we should probably rest at the Inn " +
                        "since it's getting late.");
                Thread.sleep(2000);

            }

            do {

                Main.clearScreen();

                //Display player info
                System.out.println("Player: " + partyMembers.get(0).getName());
                System.out.println("Lvl: " + partyMembers.get(0).getLevel());
                System.out.println("Exp: " + partyMembers.get(0).getExp());
                System.out.println("\uD83D\uDCB0 Money: $" + partyMembers.get(0).getMoney());
                System.out.println("[e]: Party");
                System.out.println("[i]: Inventory");

                //User prompt inside village
                System.out.println("\nWelcome to Village.");
                System.out.println("1) Rest at Inn");
                if (finishVillage) {
                    System.out.println("2) Visit the Store");
                    System.out.println("3) Talk to Villagers");
                    System.out.println("4) Exit");
                }
                prompt = keyInput.nextLine();

                //Go to user selection
                switch (prompt) {

                    case "1":
                        //Rest at Inn (recover health of all party members)
                        if (!finishVillage) {
                            //During story (free)
                            System.out.println("You and \uD83E\uDDDA Robin went to the local Inn.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": Here is an Inn!");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": Usually you would pay to stay for " +
                                    "one night...");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": but since I'm feeling generous, I'll " +
                                    "pay for the both of us!");
                            Thread.sleep(1500);
                            prompt = "y";
                        } else {
                            do {
                                System.out.println("Pay \uD83D\uDCB0 $100 to rest at Inn? (y/n)");
                                prompt = keyInput.nextLine();
                                if (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n")) {
                                    //Error trap
                                    System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                                    Thread.sleep(500);
                                    Main.clearScreen();
                                }
                            } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));
                        }

                        if (prompt.equalsIgnoreCase("y")) {
                            if (!finishVillage) {
                                System.out.println("You and \uD83E\uDDDA Robin rested at the Inn. ");
                                for (int j = 0; j < partyMembers.size(); j++) {
                                    partyMembers.get(j).setCurrentHealth(partyMembers.get(j).getHealth());
                                }
                            } else {
                                if ((partyMembers.get(0).getMoney() - 100) < 0) {
                                    System.out.println("You don't have enough \uD83D\uDCB0 Money");
                                } else {
                                    //Pay $100
                                    partyMembers.get(0).setMoney(partyMembers.get(0).getMoney() - 100);
                                    //Restore health of all party members
                                    for (int j = 0; j < partyMembers.size(); j++) {
                                        partyMembers.get(j).setCurrentHealth(partyMembers.get(j).getHealth());
                                    }
                                    System.out.println("You and your team's health are fully restored!");
                                }
                            }
                            Thread.sleep(2000);
                        }

                        if (!finishVillage) {

                            Main.clearScreen();

                            //Continue story and play boss music
                            Main.music("boss.wav");
                            System.out.println("The next day, you and \uD83E\uDDDA Robin wake up to the sound of " +
                                    "screaming.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": What's going on?!");
                            Thread.sleep(1500);
                            System.out.println("Villager: We're being under attacked by a \uD83D\uDC32 Dragon!");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": Oh no! We must save the villagers!");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": " + partyMembers.get(0).getName() +
                                    "! Lets go!");
                            Thread.sleep(1500);
                            System.out.println("You and \uD83E\uDDDA Robin ran towards the giant creature engulfing the " +
                                    "village into flames.");
                            Thread.sleep(1500);

                            //Fight Dragon (scripted loss)
                            battle("\uD83D\uDC32 Dragon");

                            //Claude joins
                            partyMembers.add(new Party("\uD83D\uDC68 Claude", 1, 105, 20, 35, 15, 5, 0, 4, 0, 105, 20,
                                    weapons[2][0], armor[0][1], true));
                            currentPartyMembers.add(partyMembers.get(partyMembers.size()-1));

                            //Restore party members' health after the scripted loss
                            for (int j = 0; j < currentPartyMembers.size(); j++) {
                                currentPartyMembers.get(j).setCurrentHealth(currentPartyMembers.get(j).getHealth());
                            }

                            System.out.println("???: You guys ok?");
                            Thread.sleep(1500);
                            System.out.println("Villager Girl: " + partyMembers.get(2).getName() + " our Hero!");
                            Thread.sleep(1500);
                            System.out.println("Villager Man: Thank you " + partyMembers.get(2).getName() + ". You have "
                                    + "aided us " +
                                    "once again.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": " + partyMembers.get(2).getName() + "?"
                                    + " the Hero of " +
                                    "the Village? Is that you?");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": It is indeed! I must commend you guys "
                                    + "for fighting " +
                                    "off against the \uD83D\uDC32 Dragon");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": Although a bit foolish, you managed to"
                                    + " buy enough " +
                                    "time for me to arrive.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": Thanks I guess... oh and pardon our " +
                                    "manners, my name is " + partyMembers.get(1).getName() + "! And this is " +
                                    partyMembers.get(0).getName() + ".");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": Nice to meet you guys. Say, I have a "
                                    + "fond liking " +
                                    "of your courageousness and teamwork.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": How about I join you guys and help " +
                                    "muster your combat skills...");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": and together we take down the " +
                                    "\uD83D\uDC32 Dragon!");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(1).getName() + ": Sure! Its great to have more " +
                                    "companions. Right " + partyMembers.get(0).getName() + "?");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(0).getName() + ": ...");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": Ah I see, so he is the generic silent "
                                    + "protagonist " +
                                    "type, well no matter.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": Anyways, the best way to train is to "
                                    + "tackle on a " +
                                    "dungeon located somewhere.");
                            Thread.sleep(1500);
                            System.out.println(partyMembers.get(2).getName() + ": We can make preparations for now, so " +
                                    "let me know when you are ready to go out.");
                            Thread.sleep(2000);
                            finishVillage = true;

                            //Restore party members' health after the scripted loss
                            for (int j = 0; j < currentPartyMembers.size(); j++) {
                                currentPartyMembers.get(j).setCurrentHealth(currentPartyMembers.get(j).getHealth());
                            }

                            //Village checkpoint
                            save(finishGame, partyMembers, currentPartyMembers, partyMembers.get(0).getInventory(), world,
                                    map, villagesVisited, row, column, xPos, yPos, dragonX, dragonY, finishTutorial,
                                    finishVillage, finishDungeon, towerSpawn);
                            System.out.println("Game auto saved successfully.");
                            Thread.sleep(2000);

                        }
                        break;

                    case "2":
                        if (finishVillage) {

                            do {
                                //Visit the Store
                                Main.clearScreen();
                                System.out.println("Store Clerk: Hey there! How may I help ya?");
                                System.out.println("1) Purchase Loot Box");
                                System.out.println("2) Hire Mercenaries");
                                System.out.println("3) See ya!");
                                prompt = keyInput.nextLine();

                                switch (prompt) {
                                    case "1":
                                        //Buy loot box (random equipment/gacha)
                                        do {
                                            System.out.println("Purchase Loot Bot for \uD83D\uDCB0 $200? (y/n)");
                                            prompt = keyInput.nextLine();
                                            if (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n")) {
                                                //Error trap
                                                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                                                Thread.sleep(500);
                                                Main.clearScreen();
                                            }
                                        } while (!prompt.equalsIgnoreCase("y") && !prompt.equalsIgnoreCase("n"));

                                        if (prompt.equalsIgnoreCase("y")) {
                                            if ((partyMembers.get(0).getMoney() - 200) < 0) {
                                                System.out.println("You don't have enough \uD83D\uDCB0 Money");
                                                Thread.sleep(1500);
                                            } else {
                                                partyMembers.get(0).setMoney(partyMembers.get(0).getMoney() - 200);
                                                reward("lootbox");
                                            }
                                        }
                                        prompt = "1";
                                        break;
                                    case "2":
                                        //Hire mercenaries (only Keqing)
                                        do {
                                            Main.clearScreen();
                                            if (partyMembers.size() < 4) {
                                                System.out.println("Choose a mercenary to hire.");
                                                System.out.println("1) \uD83D\uDC69 Keqing - $1000");
                                            } else {
                                                System.out.println("There are no available mercenaries.");
                                            }
                                            System.out.println("Type 0 to return.");
                                            prompt = keyInput.nextLine();

                                            if (prompt.equals("1") && partyMembers.size() < 4) {
                                                if ((partyMembers.get(0).getMoney() - 1000) < 0) {
                                                    System.out.println("You don't have enough \uD83D\uDCB0 Money");
                                                } else {
                                                    //Keqing joins
                                                    partyMembers.get(0).setMoney(partyMembers.get(0).getMoney() - 1000);
                                                    partyMembers.add(new Party("\uD83D\uDC69 Keqing", partyMembers.get(0).getLevel(), 120, 25, 40,
                                                            18, 20, 0, 5, 0, 120, 25, weapons[0][2], armor[1][2], false));
                                                    partyMembers.get(3).updateStats();
                                                    System.out.println(partyMembers.get(3).getName() + " joined your "
                                                            + "party!");
                                                    System.out.println(partyMembers.get(3).getName() + ": I'll be at "
                                                            + "your service.");
                                                }
                                                Thread.sleep(1500);
                                            }
                                            if (!prompt.equals("0") && (!prompt.equals("1") && partyMembers.size() < 4)) {
                                                //Error trap
                                                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                                                Thread.sleep(500);
                                                Main.clearScreen();
                                            }
                                        } while (!prompt.equals("0"));
                                        prompt = "2";
                                        break;
                                }

                                if (!prompt.equals("1") && !prompt.equals("2") && !prompt.equals("3")) {
                                    //Error trap
                                    System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                                    Thread.sleep(500);
                                    Main.clearScreen();
                                }

                            } while (!prompt.equals("3"));

                            System.out.println("Store Clerk: Come back soon!");
                            Thread.sleep(2000);

                        }
                        break;

                    case "3":
                        //Talk to Villagers (view available quests)
                        if (finishVillage) {

                            do {

                                done = false;

                                do {
                                    Main.clearScreen();
                                    //Print available quests for specific village
                                    if (quests.size() > 1) {
                                        for (int i = 1; i < quests.size(); i++) {
                                            System.out.println(i + ") " + quests.get(i));
                                        }
                                    } else {
                                        System.out.println("There are no available quests.");
                                    }
                                    System.out.println("Type 0 to return.");

                                    if (keyInput.hasNextInt()) {
                                        done = true;
                                    } else {
                                        keyInput.nextLine();
                                    }

                                } while (!done);

                                questSelect = Integer.parseInt(keyInput.nextLine());

                                //User prompt to select available quest
                                if (questSelect > 0 && questSelect < quests.size()) {

                                    //Tokenize quest selected to get individual values
                                    String[] tokens = quests.get(questSelect).split(" ");
                                    String item = tokens[4] + " " + tokens[5];
                                    int amount = Integer.parseInt(tokens[3]);

                                    //Check if player has enough of the item required
                                    if (partyMembers.get(0).checkInventory(partyMembers.get(0).getInventory(), item,
                                            amount)) {

                                        //User prompt to give item required
                                        do {
                                            System.out.println("Give " + amount + " " + item + " to " + tokens[0] +
                                                    "? (y/n)");
                                            prompt = keyInput.nextLine();
                                        } while (!prompt.equalsIgnoreCase("y") &&
                                                !prompt.equalsIgnoreCase("n"));

                                        if (prompt.equalsIgnoreCase("y")) {

                                            System.out.println(tokens[0] + " Thank you!");
                                            Thread.sleep(1500);

                                            //Give required item amount
                                            partyMembers.get(0).removeInventory(item, amount);

                                            //Remove quest from village and give reward
                                            quests.remove(questSelect);
                                            reward("quest");

                                        }

                                    }

                                }

                            } while (questSelect != 0);

                        }
                        break;
                    case "e":
                        //Check party members
                        Main.clearScreen();
                        party();
                        break;
                    case "i":
                        //Use inventory
                        Main.clearScreen();
                        partyMembers.get(0).printInventory();
                        break;

                }

            } while (!prompt.equals("4") || !finishVillage);

            //Play overworld music
            Main.music("overworld.wav");

        }

    }//end of village

    /*************************
     * Method Name: quests
     * Method Description: Generates a random quest.
     * @return - generated quest
     **************************/
    public String quest() {

        //Variables in quest
        String occupation = null, material = "", reason = "";
        int villager, chooseReason, amount;

        //Determine random villager and reason
        villager = random.nextInt(6) + 1;
        chooseReason = random.nextInt(2);

        switch (villager) {

            case 1:
                occupation = "Farmer";
                if (chooseReason == 0) {
                    material = "\uD83C\uDF4E Apples";
                    reason = "feed the cows.";
                } else {
                    material = "\uD83C\uDF44 Mushrooms";
                    reason = "make fertilizers.";
                }
                break;
            case 2:
                occupation = "Miner";
                if (chooseReason == 0) {
                    material = "\uD83E\uDD4C Stone";
                    reason = "extract ore.";
                } else {
                    material = "\uD83D\uDC8E Ore";
                    reason = "craft a pickaxe.";
                }
                break;
            case 3:
                occupation = "Architect";
                if (chooseReason == 0) {
                    material = "\uD83E\uDD62 Wood";
                    reason = "build a house.";
                } else {
                    material = "\uD83E\uDD4C Stone";
                    reason = "build a well.";
                }
                break;
            case 4:
                occupation = "Blacksmith";
                if (chooseReason == 0) {
                    material = "\uD83D\uDC8E Ore";
                    reason = "forge a sword.";
                } else {
                    material = "\uD83C\uDF52 Berries";
                    reason = "eat.";
                }
                break;
            case 5:
                occupation = "Alchemist";
                if (chooseReason == 0) {
                    material = "\uD83C\uDF44 Mushrooms";
                    reason = "brew elixirs.";
                } else {
                    material = "\uD83C\uDF3F Herbs";
                    reason = "make medicine.";
                }
                break;
            case 6:
                occupation = "Cook";
                if (chooseReason == 0) {
                    material = "\uD83C\uDF52 Berries";
                    reason = "make simmered fruit.";
                } else {
                    material = "\uD83C\uDF4E Apples";
                    reason = "make apple pie.";
                }
                break;
            case 7:
                occupation = "Environmentalist";
                if (chooseReason == 0) {
                    material = "\uD83C\uDF3F Herbs";
                    reason = "plant.";
                } else {
                    material = "\uD83D\uDC1B Critters";
                    reason = "save.";
                }
                break;
            case 8:
                occupation = "Student";
                if (chooseReason == 0) {
                    material = "\uD83D\uDC1B Critters";
                    reason = "study entomology.";
                } else {
                    material = "\uD83E\uDD62 Wood";
                    reason = "learn woodworking.";
                }
                break;

        }

        //Determine random amount of item required
        amount = random.nextInt(5) + 1;

        //Return quest
        return occupation + ": I need " + amount + " " + material + " to " + reason;

    }//end of quest

    /*************************
     * Method Name: reward
     * Method Description: Generates a random reward from opening chests or completing quests.
     * @param method - how the player obtained the reward
     **************************/
    public void reward(String method) throws InterruptedException {

        //Variables in reward
        int earnedMoney, earnedExp = 0, itemChance;
        String itemGet = null, obtain;

        //Determine method of obtaining
        if (method.equals("chest")) {
            obtain = "found";
        } else {
            obtain = "received";
        }

        //Determine money, exp, and item
        earnedMoney = random.nextInt(500) + 100;
        if (method.equals("quest")) {
            earnedExp = random.nextInt(250) + 100;
        }
        itemChance = random.nextInt(100) + 1;

        //Choose item
        if (itemChance <= 50) {
            //Common Items
            itemChance = random.nextInt(5) + 1;
            switch (itemChance) {
                case 1:
                    itemGet = weapons[0][0];
                    break;
                case 2:
                    itemGet = weapons[1][0];
                    break;
                case 3:
                    itemGet = weapons[2][0];
                    break;
                case 4:
                    itemGet = armor[0][0];
                    break;
                case 5:
                    itemGet = armor[1][0];
                    break;
            }
        } else if (itemChance <= 75) {
            //Uncommon Items
            itemChance = random.nextInt(5) + 1;
            switch (itemChance) {
                case 1:
                    itemGet = weapons[0][1];
                    break;
                case 2:
                    itemGet = weapons[1][1];
                    break;
                case 3:
                    itemGet = weapons[2][1];
                    break;
                case 4:
                    itemGet = armor[0][1];
                    break;
                case 5:
                    itemGet = armor[1][1];
                    break;
            }
        } else if (itemChance <= 90) {
            //Rare Items
            itemChance = random.nextInt(5) + 1;
            switch (itemChance) {
                case 1:
                    itemGet = weapons[0][2];
                    break;
                case 2:
                    itemGet = weapons[1][2];
                    break;
                case 3:
                    itemGet = weapons[2][2];
                    break;
                case 4:
                    itemGet = armor[0][2];
                    break;
                case 5:
                    itemGet = armor[1][2];
                    break;
            }
        } else if (itemChance <= 97) {
            //Legendary Items
            itemChance = random.nextInt(5) + 1;
            switch (itemChance) {
                case 1:
                    itemGet = weapons[0][3];
                    break;
                case 2:
                    itemGet = weapons[1][3];
                    break;
                case 3:
                    itemGet = weapons[2][3];
                    break;
                case 4:
                    itemGet = armor[0][3];
                    break;
                case 5:
                    itemGet = armor[1][3];
                    break;
            }
        } else {
            //Mythical Items
            itemChance = random.nextInt(5) + 1;
            switch (itemChance) {
                case 1:
                    itemGet = weapons[0][4];
                    break;
                case 2:
                    itemGet = weapons[1][4];
                    break;
                case 3:
                    itemGet = weapons[2][4];
                    break;
                case 4:
                    itemGet = armor[0][4];
                    break;
                case 5:
                    itemGet = armor[1][4];
                    break;
            }
        }

        //Earn money and exp
        if (!method.equals("lootbox")) {
            System.out.println(partyMembers.get(0).getName() + " " + obtain + " \uD83D\uDCB0 $" + earnedMoney + "!");
            Thread.sleep(1500);
            if (method.equals("quest")) {
                for (int j = 0; j < currentPartyMembers.size(); j++) {
                    System.out.println(partyMembers.get(j).getName() + " gained " + earnedExp + " Exp!");
                    Thread.sleep(1500);
                    currentPartyMembers.get(j).gainExpMoney(earnedExp, earnedMoney);
                    currentPartyMembers.get(j).checkLvl();
                    System.out.println("\n");
                }
            }
        }

        //Earn item
        System.out.println(partyMembers.get(0).getName() + " " + obtain + " " + itemGet + "!");
        Thread.sleep(1500);

        //Call equip method
        equip(itemGet);

    }//end of reward

    /*************************
     * Method Name: equip
     * Method Description: Sets equipment for party members.
     * @param item - type of item
     **************************/
    public void equip(String item) throws InterruptedException {

        //Variables in equip
        String gender = null, confirm;
        int character = 0;
        boolean armor = false;

        //Tokenize parameter to get name
        String[] tokens = item.split(" ");

        //Determine weapon/armor type
        switch (tokens[2]) {
            case "Sword":
            case "Monado":
                character = 0;
                break;
            case "Staff":
            case "Thyrsus":
                character = 1;
                break;
            case "Bow":
            case "Failnaught":
                character = 2;
                break;
            case "Shirt":
            case "Armor":
            case "Tunic":
                gender = "male";
                armor = true;
                break;
            case "Dress":
            case "Robes":
            case "Cloak":
                gender = "female";
                armor = true;
                break;
        }

        //Determine weapon or armor
        if (armor) {
            if (gender.equals("male")) {
                do {
                    System.out.println("Who should you give " + item + " to?");
                    System.out.println("1) " + partyMembers.get(0).getName());
                    System.out.println("2) " + partyMembers.get(2).getName());
                    character = Integer.parseInt(keyInput.nextLine());
                } while (character < 1 || character > 2);
                if (character == 1) {
                    character = 0;
                }
            } else if (partyMembers.size() > 3) {
                do {
                    System.out.println("Who should you give " + item + " to?");
                    System.out.println("1) " + partyMembers.get(1).getName());
                    System.out.println("2) " + partyMembers.get(3).getName());
                    character = Integer.parseInt(keyInput.nextLine());
                } while (character < 1 || character > 2);
                if (character == 2) {
                    character = 3;
                }
            } else {
                character = 1;
            }
            System.out.println(partyMembers.get(character).getName() + " currently has " +
                    partyMembers.get(character).getArmor() +
                    " equipped.");
        } else {
            System.out.println(partyMembers.get(character).getName() + " currently has " +
                    partyMembers.get(character).getWeapon() +
                    " equipped.");
        }

        //Confirm replacement of current weapon/armor
        do {
            System.out.println("Would you like to replace it with " + item + "? (y/n)");
            confirm = keyInput.nextLine();
            if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                //Error trap
                System.out.println("\u001B[31mInvalid Selection\u001B[0m");
                Thread.sleep(500);
                Main.clearScreen();
            }
        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));

        //Replace weapon/armor
        if (confirm.equalsIgnoreCase("y")) {
            System.out.println(partyMembers.get(character).getName() + " equipped the " + item);
            Thread.sleep(2000);

            //Unequip old item and equip new item
            if (!armor) {
                partyMembers.get(character).setWeapon(item);
            } else {
                partyMembers.get(character).setArmor(item);
            }
        }

        Main.clearScreen();

    }//end of equip


    /*************************
     * Method Name: end
     * Method Description: Displays the final boss and ending to the story.
     * @return - boolean value when player dies
     **************************/
    public boolean end() throws InterruptedException, UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        //Play ending music
        Main.music("theEnd.wav");

        Main.clearScreen();

        //End of the story
        System.out.println("You and your team members climb to the top of the tower.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": Well, this is it. The final battle.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": The \uD83D\uDC32 Dragon resides up on the top of this " +
                "tower.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Woah, look down there! We are so high up! I've never " +
                "even realized we made it to the top!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": So... where exactly is the \uD83D\uDC32 Dragon?");
        Thread.sleep(1500);
        System.out.println("You suddenly feel an ominous presence quickly approaching.");
        Thread.sleep(1500);
        System.out.println("\uD83D\uDC32 Dragon: Raaaugghhrrr!!!!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": There you are!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": This is where we will finish you once and for all!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": Lets go team!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": Yeah!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(0).getName() + ": ...");
        Thread.sleep(1500);
        System.out.println("\uD83D\uDC32 Dragon: Raaaugghhrrr!!!!");
        Thread.sleep(2000);

        //Play final boss music
        Main.music("finalBoss.wav");
        if (!battle("\uD83D\uDC32 Dragon")) {
            //If Player loses
            return false;
        }

        //Play credits music
        Main.music("credits.wav");

        System.out.println(partyMembers.get(1).getName() + ": Huff... we actually did it... we won!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": Finally... the curse of 1000 years has been lifted...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": Great job everyone... I'm proud of you all.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(1).getName() + ": We definitely wouldn't have done it without the help of " +
                        partyMembers.get(0).getName() + "!");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": Yes... " + partyMembers.get(0).getName());
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": I must owe you my sincerest and deepest gratitude...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": That creature has haunted us for many years...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": We wouldn't have gotten this far if it weren't for your " +
                "relentless courage...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": and leadership.");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(2).getName() + ": And now, we can finally live our lives in peace...");
        Thread.sleep(1500);
        System.out.println(partyMembers.get(0).getName() + ": ...!");
        Thread.sleep(2000);

        System.out.println("And so, " + partyMembers.get(0).getName() + " and his team went on as heroes of Genshin.");
        Thread.sleep(1500);
        System.out.println("As they continued to restore and rebuild peace and prosperity to the world...");
        Thread.sleep(1500);
        System.out.println("and preventing evil from lurking ever again.");
        Thread.sleep(1500);
        System.out.println("The End.");
        Thread.sleep(5000);

        Main.clearScreen();

        //Display the credits
        System.out.println("Credits");
        Thread.sleep(1500);
        System.out.println("\nDirectors:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nProgramming:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nBattle System:");
        System.out.println("Jose Jesus II Abejo");
        Thread.sleep(1500);
        System.out.println("\nOpen World Generation:");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nCharacter Manipulation:");
        System.out.println("Jose Jesus II Abejo");
        Thread.sleep(1500);
        System.out.println("\nQuest System and Inventory:");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nGame Balancing:");
        System.out.println("Jose Jesus II Abejo");
        Thread.sleep(1500);
        System.out.println("\nStory Writing:");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nAttempted Controller:");
        System.out.println("Jose Jesus II Abejo");
        Thread.sleep(1500);
        System.out.println("\nSaving and Loading System:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nMusic and Graphics:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nError Handling:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nDebugging:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nTechnical Support:");
        System.out.println("Jose Jesus II Abejo");
        System.out.println("Gavin Eugenio");
        Thread.sleep(1500);
        System.out.println("\nSpecial Thanks:");
        System.out.println("Mr. Janicas");
        System.out.println("ArrayLists");
        System.out.println("GitHub");
        System.out.println("Discord");
        Thread.sleep(1500);
        System.out.println("\nThank you for playing our game!");
        Thread.sleep(1500);

        System.out.println("\nYou have beaten the game and unlocked a special title screen!");
        Thread.sleep(1500);
        System.out.println("You can continue playing on your save file as much as you'd like until you start a new game.");
        System.out.println("(Type Anything to Continue)");
        keyInput.nextLine();
        finishGame = true;

        //Game complete checkpoint
        save(finishGame, partyMembers, currentPartyMembers, partyMembers.get(0).getInventory(), world,
                map, villagesVisited, row, column, xPos, yPos, dragonX, dragonY, finishTutorial,
                finishVillage, finishDungeon, towerSpawn);
        System.out.println("Game auto saved successfully.");

        //Return to navigation (then afterwards Main to end program)
        return true;

    }//end of end method

    /*************************
     * Method Name: save
     * Method Description: Saves the progress of the game.
     * @param - every field to be saved
     **************************/
    public void save(boolean finishGame, ArrayList<Character> p, ArrayList<Character> c,
                            ArrayList<ArrayList<String>> in, ArrayList<ArrayList<String>> w,
                            ArrayList<ArrayList<String>> m, ArrayList<ArrayList<String>> v, int row, int column,
                            int xPos, int yPos, int dx, int dy, boolean finishTutorial, boolean finishVillage,
                            boolean finishDungeon, boolean towerSpawn)
            throws FileNotFoundException {

        //PrintWriter
        File file = new File("saveData.txt");
        PrintWriter fileWrite = new PrintWriter(file);

        //Save finishGame
        fileWrite.println(finishGame);

        //Save Party Data
        fileWrite.println("Party Data");
        for (int i=0; i<p.size(); i++) {
            fileWrite.println(p.get(i));
        }

        //Save Inventory
        fileWrite.println("Inventory");
        for (int i=0; i<in.size(); i++) {
            fileWrite.println(in.get(i).get(0) + " " + in.get(i).size());
        }

        //Save World Data
        fileWrite.println("World Data");
        String mapFields = row + " " + column + " " + xPos + " " + yPos + " " + dx + " " + dy + " " + finishTutorial +
                " " + finishVillage + " " + finishDungeon + " " + towerSpawn;
        fileWrite.println(mapFields);

        //Save World
        fileWrite.println("World");
        for (int i=0; i<w.size(); i++) {
            for (int j=0; j<w.get(i).size(); j++) {
                fileWrite.print(w.get(i).get(j) + " ");
            }
            fileWrite.println();
        }

        //Save Map
        fileWrite.println("Map");
        for (int i=0; i<m.size(); i++) {
            for (int j=0; j<m.get(i).size(); j++) {
                fileWrite.print(m.get(i).get(j) + " ");
            }
            fileWrite.println();
        }

        //Save Villages Visited
        fileWrite.println("Villages");
        for (int i=0; i<v.size(); i++) {
            for (int j=0; j<v.get(i).size(); j++) {
                fileWrite.print(v.get(i).get(j) + "|");
            }
            fileWrite.println();
        }

        //Close printWriter object
        fileWrite.close();

    }//end of save

}//end of class