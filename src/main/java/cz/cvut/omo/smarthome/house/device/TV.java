package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class TV extends Device {

    public TV(Room room, String type) {
        super(room, type, new Consumption("TV"), 0.1);
        setState(new TurnedOff(this));
    }
}
