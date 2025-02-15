package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;

public class Active extends DeviceState {
    public Active(Device device) {
        super(device);
    }

    @Override
    public void getAction() {
        if (!device.use())
            device.setState(new Broken(device));
        else
            device.consume(1);
    }
}
