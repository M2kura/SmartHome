package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.ChangableObj;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class EventManager {
    private static EventManager instance;
    private List<Event> events;

    private EventManager() {
        this.events = new ArrayList<>();
    }

    public static EventManager getEM() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void receiveUpdate() {}

    public Optional<Event> getEvent(ChangableObj obj) {
        return events.stream()
                .filter(event -> event.getStatus().equals("waiting") &&
                        event.isFor(obj))
                .findFirst();
    }
}
