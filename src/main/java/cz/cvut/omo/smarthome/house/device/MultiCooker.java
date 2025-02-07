package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class MultiCooker extends Device{
    public MultiCooker(Room room, String type) {
        super(room, type, new Consumption("electro", 450), 0.5);
        setState(new TurnedOff(this));
    }
}
