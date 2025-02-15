package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.resident.person.Person;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.state.*;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.ChangableObj;

import java.util.Optional;
import java.util.Map;

public abstract class Device implements ChangableObj {
    protected String type;
    protected Consumption consumption;
    protected Optional<String> manual;
    protected Room room;
    protected double breakChance;
    protected Optional<Person> usedBy;
    protected Clock clock;
    protected DeviceState state;

    public Device(Room room, String type, Consumption consumption, double breakChance) {
        this.room = room;
        this.type = type;
        this.consumption = consumption;
        this.breakChance = breakChance;
        this.manual = Optional.empty();
        this.usedBy = Optional.empty();
        this.clock = Clock.getClock();
    }

    @Override
    public void getAction() {
        state.getAction();
    }

    @Override
    public String getConfig() {
        return "        (Type: "+type+")\n";
    }

    public String getType() {
        return type;
    }

    public Room getRoom() {
        return room;
    }

    public String getConsumption() {
        return type+": "+consumption.getReport()+"\n";
    }

    public double getCost() {
        return consumption.getCost();
    }

    public boolean isBroken() {
        return state instanceof Broken;
    }

    public boolean use() {
        return Math.random() > breakChance;
    }

    public void consume(double fraction) {
        consumption.update(fraction);
    }

    public void resetState() {
        setState(new TurnedOff(this));
    }

    public Optional<String> getManual() {
        return manual;
    }

    public void setManual(Optional<String> manual) {
        this.manual = manual;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }
}
