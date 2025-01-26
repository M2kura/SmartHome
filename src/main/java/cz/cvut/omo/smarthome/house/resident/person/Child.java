package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.resident.person.Person;
import cz.cvut.omo.smarthome.house.resident.animal.Animal;
import cz.cvut.omo.smarthome.house.Room;

public class Child extends Person{
    private Animal pet;

    public Child(Room room, String name, String type) {
        super(room, name, type);
    }
    public void askForFood() {}

    public void doHomework() {}

    public void playVideoGames() {}

    public void playWithPet() {}
}
