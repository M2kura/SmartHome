package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.creature.Person;
import cz.cvut.omo.smarthome.house.room.Room;

import java.util.Map;
import java.util.Optional;

public abstract class Device {
    protected boolean isBroken;
    protected Consumption consumption;
    protected Optional<String> deviceManual;
    protected Room currentRoom;
    protected double breakChance;
    protected Optional<Person> usedBy;


    //------Methods------


    public Consumption getConsumption() {
        return consumption;
    }

    public void use() {}

    public Optional<String> getManual() {
        return deviceManual;
    }

    public void setManual() {
        this.deviceManual = deviceManual;
    }
}
