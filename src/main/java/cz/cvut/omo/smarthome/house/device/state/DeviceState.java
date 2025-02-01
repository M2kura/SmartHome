package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.EventManager;

public abstract class DeviceState {
    Device device;

    public DeviceState(Device device) {
        this.device = device;
    }

    public abstract void getAction();
    public abstract void getUpdate();
}
