package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.EventManager;

public abstract class DeviceState {
    protected Device device;
    protected EventManager em;

    public DeviceState(Device device) {
        this.device = device;
        this.em = EventManager.getEM();
    }

    public abstract void getAction();
}
