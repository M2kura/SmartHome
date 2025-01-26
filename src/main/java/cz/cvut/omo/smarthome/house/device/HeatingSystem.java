package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import java.util.Optional;

public class HeatingSystem extends Device{
    private double temperature;

    public HeatingSystem(Consumption consumption, Optional<String> manual,
        double breakChance, Room room, String type) {
        super(consumption, manual, breakChance, room, type);
        this.temperature = 20;
    }
}
