package culminating;

import java.util.ArrayList;

public class World {

    protected ArrayList<ArrayList<String>> worldGen = new ArrayList<>();

    //Constructor
    public World() {

    }

    //Accessors

    //Mutators

    /*************************
     * Method Name: initWorld
     * Method Description: Initializes the world through a 5x5 grid and sets player starting position.
     **************************/
    public void initWorld() {
        for (int i=0; i<5; i++) {
            world.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                world.get(i).add("Grass");
            }
        }
        world.get(0).set(2, "Fairy");
        world.get(0).set(3, "Ogre");
        row = 2;
        column = 2;
    }

}