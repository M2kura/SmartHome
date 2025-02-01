package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;

public class Active extends DeviceState {
    public Active(Device device) {
        super(device);
    }

    @Override
    public void getAction() {
        return;
    }

    @Override
    public void getUpdate() {
        return;
    }
}
