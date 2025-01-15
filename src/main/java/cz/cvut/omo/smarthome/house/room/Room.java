package cz.cvut.omo.smarthome.house.room;

import cz.cvut.omo.smarthome.house.resident.animal.Animal;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.resident.person.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Room {
    private List<Device> devices;
    private List<Person> people;
    private List<Animal> animals;

    public Room() {
        this.people = new ArrayList<>();
    }

    //------Methods------


    public List<Device> getDevices() {
        return devices;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Animal> getAnimals() {
        return animals;
    }


    public void addPersonToRoom(Person person) {
        if (people.contains(person)) {
            people.add(person);
            System.out.println(person.getName() + "has entered the" + this.getClass().getSimpleName());
        }
    }

    public void removePersonFromRoom(Person person) {
        if (people.remove(person)) {
            System.out.println(person.getName() + "has left the" + this.getClass().getSimpleName());
        }
    }


}
