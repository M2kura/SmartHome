package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.resident.Resident;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Person extends Resident {
    private String surname;
    private boolean isAtHome;
    private double age;

    public Person(Room room, String name, String type) {
        super(room, name, type);
        this.surname = "Smith";
        this.isAtHome = true;
    }
}
