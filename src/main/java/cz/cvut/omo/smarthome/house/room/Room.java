package cz.cvut.omo.smarthome.house.room;

import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.RobotVacuum;

import java.awt.*;
import java.util.List;

public abstract class Room {
    private List<Device> devices;
    private List<Resident> residents;
    private Floor floor;
    private String name;
    private int dirtLevel;

    public String getName() {
        return name;
    }
    public void addDevice() {}

    public void removeDevice() {}

    public void addResident(Resident resident) {}

    public void removeResident(Resident resident) {}

    public void isEmpty() {}

    public void isDirty() {
        if( dirtLevel > 60 ) {
            RobotVacuum.use();
        }
    }

}
