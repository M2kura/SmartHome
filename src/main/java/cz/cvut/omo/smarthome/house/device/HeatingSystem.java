package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class HeatingSystem extends Device{
    private double temperature;

    public HeatingSystem(Room room, String type) {
        super(room, type, new Consumption("electro", 2250), 0.0001);
        setState(new TurnedOn(this));
        this.temperature = 20;
    }

    @Override
    public void fix() {
        setState(new TurnedOn(this));
    }
}
