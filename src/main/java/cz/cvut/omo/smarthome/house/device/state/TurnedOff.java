package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;

public class TurnedOff extends DeviceState {
    public TurnedOff(Device device) {
        super(device);
    }

    @Override
    public void getAction() {
        return;
    }
}
