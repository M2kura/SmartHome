package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.manuals.Recipe;
import cz.cvut.omo.smarthome.house.resident.person.Adult;
import cz.cvut.omo.smarthome.house.Room;
import java.util.List;

public class Teen extends Adult {
    public Teen(Room room, String name, String type) {
        super(room, name, type);
    }

    public void study() {
        System.out.println(this.name + " is studying");
    }
}
