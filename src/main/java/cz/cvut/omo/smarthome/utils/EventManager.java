package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.*;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.ChangableObj;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class EventManager {
    private static EventManager instance;
    private List<ChangableObj> objs;
    private List<Event> events;

    private EventManager() {
        this.events = new ArrayList<>();
        this.objs = new ArrayList<>();
    }

    public static EventManager getEM() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addObj(ChangableObj obj) {
        this.objs.add(obj);
    }

    public void update(String type, ChangableObj obj) {
        Event event = new Event();
        Device device;
        if (type.equals("broken")) {
            device = (Device) obj;
            event.setRoom(device.getRoom());
            event.setInvolved(device);
            Dad dad = (Dad) objs.stream()
            .filter(res -> res instanceof Dad).findFirst().get();
            event.setFor(dad);
            event.setType(type);
            event.setTime(4);
            event.setTask("fixing " + device.getType());
        }
        events.add(event);
    }

    public Optional<Event> getEvent(ChangableObj obj) {
        return events.stream()
                .filter(event -> event.getStatus().equals("waiting") &&
                        event.isFor(obj))
                .findFirst();
    }
}
