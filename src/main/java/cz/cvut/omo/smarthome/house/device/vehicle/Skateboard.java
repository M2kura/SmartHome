package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.state.*;

public class Skateboard extends Vehicle {
    public Skateboard(Room room, String type) {
        super(room, type, new Consumption("none", 0), 0.05, 40, "Medium", "skateboarding");
        setState(new TurnedOff(this));
    }
}
