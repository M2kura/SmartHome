package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class LightSystem extends Device{
    private int brightness;

    public LightSystem(Room room, String type) {
        super(room, type, new Consumption("electro", 300), 0.0001);
        setState(new TurnedOn(this));
    }

    @Override
    public void fix() {
        setState(new TurnedOn(this));
    }
}
