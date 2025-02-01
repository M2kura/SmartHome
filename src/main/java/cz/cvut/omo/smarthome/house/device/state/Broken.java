package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;

public class Broken extends DeviceState {
    public Broken(Device device) {
        super(device);
        System.out.println(device.getType() + " got broken. Somebody need to fix it");
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
