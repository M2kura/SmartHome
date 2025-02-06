package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Clock;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public abstract class UpdatableContainer implements ChangableObj {
    protected EventManager em;
    protected Clock clock;
    protected List<ChangableObj> childObjs;

    protected List<Resident> getAllResidents() {
        List<Resident> residents = new ArrayList<>();
        for (ChangableObj child : childObjs) {
            if (child instanceof Resident) {
                residents.add((Resident) child);
            } else if (child instanceof UpdatableContainer) {
                residents.addAll(((UpdatableContainer) child).getAllResidents());
            }
        }
        return residents;
    }

    protected List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        for (ChangableObj child : childObjs) {
            if (child instanceof Device) {
                devices.add((Device) child);
            } else if (child instanceof UpdatableContainer) {
                devices.addAll(((UpdatableContainer) child).getAllDevices());
            }
        }
        return devices;
    }

    @Override
    public void getAction() {}

    @Override
    public String getConfig(){
        return "";
    }
}
