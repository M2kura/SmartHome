package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;

public class TurnedOn extends DeviceState {
    public TurnedOn(Device device) {
        super(device);
    }

    @Override
    public void getAction() {
        if (!device.use())
            device.setState(new Broken(device));
        else
            device.consume(0.5);
    }
}
