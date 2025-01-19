package cz.cvut.omo.smarthome.house.resident;

import cz.cvut.omo.smarthome.house.resident.state.State;
import cz.cvut.omo.smarthome.house.resident.state.IdleState;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.house.Room;

public abstract class Resident {
    protected State state;
    protected String name;
    protected Room currentRoom;
    protected int energyLevel;
    protected int hungerLevel;
    protected int entertainmentLevel;
    protected Room personalRoom;
    protected Clock clock;
    protected Report report;

    public Resident(Room room, String name) {
        this.state = new IdleState();
        this.currentRoom = room;
        this.personalRoom = room;
        this.name = name;
        this.energyLevel = 100;
        this.hungerLevel = 100;
        this.entertainmentLevel = 100;
        this.clock = Clock.getClock();
        this.report = Report.getReport();
    }

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
