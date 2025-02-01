package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public abstract class Vehicle extends Device {
    private int maxSpeed;
    private String size;

    public Vehicle(Room room, String type, Consumption consumption,
        double breakChance, int maxSpeed, String size) {
        super(room, type, consumption, breakChance);
        this.maxSpeed = maxSpeed;
        this.size = size;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
