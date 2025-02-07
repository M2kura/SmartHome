package cz.cvut.omo.smarthome.house.resident;

import cz.cvut.omo.smarthome.house.resident.state.*;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.house.Room;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public abstract class Resident implements ChangableObj {
    protected String type;
    protected ResidentState state;
    protected String name;
    protected Room room;
    protected Room personalRoom;
    protected String using = "";
    protected double energyLevel;
    protected int hungerLevel;
    protected int entertainmentLevel;
    protected Map<String, Integer> used;
    protected Clock clock;

    public Resident(Room room, String name, String type) {
        this.state = new Idle(this);
        this.room = room;
        this.personalRoom = room;
        this.name = name;
        this.type = type;
        this.energyLevel = 100;
        this.hungerLevel = 100;
        this.entertainmentLevel = 100;
        this.used = new HashMap<>();
        this.clock = Clock.getClock();
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
        if (!newRoom.equals(room)) {
            System.out.println(name + " is moving to " + newRoom.getName());
            room.removeResident(this);
            newRoom.addResident(this);
            this.room = newRoom;
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

    public Room getRoom() {
        return room;
    }

    public boolean isTired() {
        return energyLevel <= 10;
    }

    public void doAction(boolean common) {
        if (common)
            this.energyLevel -= 1.4286;
        else
            this.energyLevel -= 1.43;
    }

    public void useDevice(Device device) {
        if (!device.getType().equals(using))
            used.merge(device.getType(), 1, Integer::sum); 
        using = device.getType();
    }

    public String getUsage() {
        StringBuilder output = new StringBuilder();
        output.append(getName()+":\n");
        if (!used.isEmpty()) {
            for (Map.Entry<String, Integer> entry : used.entrySet()) {
                output.append("  "+entry.getKey()+" - "+entry.getValue()+"\n");
            }
        }
        return output.toString();
    }

    public void emptyDevice() {
        using = "";
    }

    public void sleep() {
        emptyDevice();
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
