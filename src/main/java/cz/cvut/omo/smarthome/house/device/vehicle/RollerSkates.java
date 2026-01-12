package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.state.*;

public class RollerSkates extends Vehicle {
    public RollerSkates(Room room, String type) {
        super(room, type, new Consumption("none", 0), 0.05, 30, "Small", "roller skating");
        setState(new TurnedOff(this));
    }
}
