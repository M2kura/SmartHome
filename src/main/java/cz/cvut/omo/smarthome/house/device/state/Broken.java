package cz.cvut.omo.smarthome.house.device.state;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import cz.cvut.omo.smarthome.utils.EventManager;

public class Broken extends DeviceState {
    public Broken(Device device) {
        super(device);
        em.update("broken", device);
        System.out.println(device.getType() + " got broken. Somebody need to fix it");
    }

    @Override
    public void getAction() {
        return;
    }
}
