package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.vehicle.Vehicle;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Bicycle extends Vehicle {
    public Bicycle(Room room, String type) {
        super(room, type, new Consumption("Bicycle"), 0.1, 80, "Big");
        setState(new TurnedOff(this));
    }
}
