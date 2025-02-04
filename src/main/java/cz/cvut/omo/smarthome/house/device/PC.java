package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class PC extends Device {

    public PC(Room room, String type) {
        super(room, type, new Consumption("PC"), 0);
        setState(new TurnedOff(this));
        setManual(Optional.of("manual"));
    }

    public boolean searchManual(Device device) {
        if (Math.random() > 0.5) {
            device.setManual(Optional.of("manual"));
            return true;
        }
        return false;
    }
}
