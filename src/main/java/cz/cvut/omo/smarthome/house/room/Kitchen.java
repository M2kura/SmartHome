package cz.cvut.omo.smarthome.house.room;


import cz.cvut.omo.smarthome.house.person.Person;

import java.util.List;

public class Kitchen extends Room{
    private List<Person> peopleEating;


    //------Methods------


    public List<Person> getPeopleEating() {
        return peopleEating;
    }
}
