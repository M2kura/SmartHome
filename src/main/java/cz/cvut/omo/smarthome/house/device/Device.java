package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.resident.person.Person;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.utils.Clock;
import java.util.Optional;

import java.util.Map;
import java.util.Optional;

public abstract class Device {
    protected boolean isBroken;
    protected Consumption consumption;
    protected Optional<String> manual;
    protected Room currentRoom;
    protected double breakChance;
    protected Optional<Person> usedBy;
    protected Clock clock;
    protected Report report;

    public Device(Consumption consumption, Optional<String> manual,
        double breakChance, Room room) {
        this.isBroken = false;
        this.consumption = consumption;
        this.manual = manual;
        this.currentRoom = room;
        this.breakChance = breakChance;
        this.usedBy = Optional.empty();
        this.clock = Clock.getClock();
        this.report = Report.getReport();
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public static void use() {}

    public Optional<String> getManual() {
        return manual;
    }

    public void setManual() {
        this.manual = manual;
    }
}
