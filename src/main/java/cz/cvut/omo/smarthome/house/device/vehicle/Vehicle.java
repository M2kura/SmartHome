package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import java.util.Optional;

public abstract class Vehicle extends Device {
    private int maxSpeed;
    private String size;

    public Vehicle(Consumption consumption, Optional<String> manual,
        double breakChance, Room room, int maxSpeed, String size) {
        super(consumption, manual, breakChance, room);
        this.maxSpeed = maxSpeed;
        this.size = size;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
