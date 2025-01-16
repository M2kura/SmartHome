package cz.cvut.omo.smarthome.house.creature;

import cz.cvut.omo.smarthome.house.creature.state.State;
import cz.cvut.omo.smarthome.house.room.Room;

public class Resident {
    private State state;
    private String name;
    private Room currentRoom;
    private int energyLevel;
    private int hungerLevel;
    private int enttertainmentLevel;
    private Room personalRoom;


    public void changeRoom() {}

    public String getName() {
        return name;
    }

    public void sleep() {}

    public void setState() {}

    public void performAction() {}

    public void eat() {}

}
