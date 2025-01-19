package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import java.util.Optional;

public class Ski extends Vehicle {
    public Ski(Consumption consumption, Optional<String> manual,
        double breakChance, Room room, int maxSpeed, String size) {
        super(consumption, manual, breakChance, room, maxSpeed, size);
    }
}
