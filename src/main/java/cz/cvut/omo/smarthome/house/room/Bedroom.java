package cz.cvut.omo.smarthome.house.room;

import cz.cvut.omo.smarthome.house.resident.person.Person;

import java.util.List;

public class Bedroom extends Room {
    private List<Person> owners;

    public List<Person> getOwner() {
        return owners;
    }
}
