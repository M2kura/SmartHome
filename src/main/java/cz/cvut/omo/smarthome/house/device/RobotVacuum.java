package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import java.util.Optional;

public class RobotVacuum extends Device{
    public RobotVacuum(Consumption consumption, Optional<String> manual,
        double breakChance, Room room) {
        super(consumption, manual, breakChance, room);
    }
}
