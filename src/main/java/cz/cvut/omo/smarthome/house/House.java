package cz.cvut.omo.smarthome.house;

import cz.cvut.omo.smarthome.house.animals.Animal;
import cz.cvut.omo.smarthome.house.person.Person;
import cz.cvut.omo.smarthome.house.utils.Event;

import java.util.List;

public class House {
    private List<Floor> floors;
    private List<Person> residents;
    private List<Event> events;
    private List<Animal> animals;

    //------Methods------

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent (Event event) {
        events.remove(event);
    }

}
