package cz.cvut.omo.smarthome.house.room;

import cz.cvut.omo.smarthome.house.creature.Person;

import java.util.List;

public class Bedroom extends Room {
    private List<Person> owners;


    //------Methods------


    public List<Person> getOwner() {
        return owners;
    }
}
