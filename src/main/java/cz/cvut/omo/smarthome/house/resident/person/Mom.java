package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.manuals.Recipe;
import cz.cvut.omo.smarthome.house.resident.person.Adult;
import cz.cvut.omo.smarthome.house.Room;
import java.util.List;

public class Mom extends Adult {
    private List<Recipe> recipes;

    public Mom(Room room, String name, String type) {
        super(room, name, type);
    }

    public void callFriends() {
        //TO DO
    }


    public void cookFood() {
        //TO DO
    }


    public void shuffleRecipes() {}
}
