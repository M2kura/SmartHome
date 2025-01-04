package cz.cvut.omo.smarthome.house;

import cz.cvut.omo.smarthome.house.creature.Person;
import cz.cvut.omo.smarthome.house.utils.Event;

import java.util.List;

public class House extends UpdatableContainer {
    private List<Floor> floors;
    private List<Person> residents;
    private List<Event> events;

    //------Methods------

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent (Event event) {
        events.remove(event);
    }

}
