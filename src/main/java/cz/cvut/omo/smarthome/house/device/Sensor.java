package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Sensor extends Device{
    private String sensorType;

    public Sensor(Room room, String type) {
        super(room, type, new Consumption("Sensor"), 0.0001);
        setState(new TurnedOn(this));
    }

    @Override
    public void fix() {
        setState(new TurnedOn(this));
    }
}
