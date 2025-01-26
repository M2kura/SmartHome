package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.ChangableObj;

import java.util.List;
import java.util.Optional;

public class Event {
    private List<ChangableObj> asignedTo;
    private Optional<Room> room;
    private String task;
    private String status;

    public Event() {
    }

    public Optional<Room> getRoom() {
        return room;
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus() {
        if (status == "waiting") {
            status = "solving";
        } else if (status == "solving") {
            status = "done";
        }
    }

    public boolean isFor(ChangableObj obj) {
        return asignedTo.contains(obj);
    }
}
