package culminating;

import java.util.ArrayList;

public class World {

    protected ArrayList<ArrayList<String>> worldGen = new ArrayList<>();

    //Constructor
    public World() {

    }

    //Accessors

    //Mutators

    public void load() {
        for (int i=0; i<5; i++) {
            worldGen.add(new ArrayList<>());
            for (int j=0; j<5; j++) {
                worldGen.get(i).add("Joey");
            }
        }
    }

}