package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.vehicle.Vehicle;
import java.util.Optional;

public class Bicycle extends Vehicle {
    public Bicycle(Consumption consumption, Optional<String> manual,
        double breakChance, Room room, int maxSpeed, String size) {
        super(consumption, manual, breakChance, room, maxSpeed, size);
    }
}
