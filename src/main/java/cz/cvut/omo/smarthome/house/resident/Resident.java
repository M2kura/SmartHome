package cz.cvut.omo.smarthome.house.resident;

import cz.cvut.omo.smarthome.house.resident.state.State;

import cz.cvut.omo.smarthome.house.room.Room;

public class Resident {
    private State state;
    private String name;
    private Room currentRoom;
    private int energyLevel;
    private int hungerLevel;
    private int entertainmentLevel;
    private Room personalRoom;


    public void changeRoom(Room newRoom) {
        if (!newRoom.equals(currentRoom)) {
            System.out.println(name + "moving to" + newRoom.getName());
            this.currentRoom = newRoom;
        }
    }

    public String getName() {
        return name;
    }

    public void sleep() {}

    public void setState(State newState) {
        this.state = newState;
    }

    public void performAction() {}

    public void eat() {}

}
