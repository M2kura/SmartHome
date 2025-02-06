package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.manuals.Recipe;
import cz.cvut.omo.smarthome.house.resident.person.Adult;
import cz.cvut.omo.smarthome.house.Room;
import java.util.List;

public class Mom extends Adult {
    private List<Recipe> recipes;

    public Mom(Room room, String name, String type) {
        super(room, name, type);
    }

    public void callFriends(Device phone) {
        changeRoom(phone.getRoom());
        useDevice(phone);
        System.out.println(this.name + " is on the phone with her friends");
    }

    public void shuffleRecipes() {}
}
