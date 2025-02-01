package cz.cvut.omo.smarthome.house.resident;

import cz.cvut.omo.smarthome.house.resident.state.*;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.house.Room;

public abstract class Resident implements ChangableObj {
    protected String type;
    protected ResidentState state;
    protected String name;
    protected Room currentRoom;
    protected double energyLevel;
    protected int hungerLevel;
    protected int entertainmentLevel;
    protected Room personalRoom;
    protected Clock clock;
    protected Report report;

    public Resident(Room room, String name, String type) {
        this.state = new Idle(this);
        this.currentRoom = room;
        this.personalRoom = room;
        this.name = name;
        this.type = type;
        this.energyLevel = 100;
        this.hungerLevel = 100;
        this.entertainmentLevel = 100;
        this.clock = Clock.getClock();
        this.report = Report.getReport();
    }

    @Override
    public void getAction() {
        state.getAction();
    }

    @Override
    public String getConfig() {
        return "        (Type: "+type+", Name: "+name+")\n";
    }

    public void changeRoom(Room newRoom) {
        if (!newRoom.equals(currentRoom)) {
            System.out.println(name + " is moving to" + newRoom.getName());
            this.currentRoom = newRoom;
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Room getPersonalRoom() {
        return personalRoom;
    }

    public boolean isTired() {
        return energyLevel <= 10;
    }

    public void doAction(boolean common) {
        if (common) {
            this.energyLevel -= 1.4286;
        } else {
            this.energyLevel -= 1.43;
        }
    }

    public void sleep() {
        energyLevel += 2.9;
        if (energyLevel >= 100) {
            if (energyLevel > 100) energyLevel = 100;
            System.out.println(name + " woke up");
            setState(new Idle(this));
        } else {
            System.out.println(name + " is sleeping");
        }
    }

    public void setState(ResidentState newState) {
        this.state = newState;
    }

    private void eat() {}
}
