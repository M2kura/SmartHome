package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public abstract class Vehicle extends Device implements SportsEquipment {
    private int maxSpeed;
    private String size;
    private String sportType;

    public Vehicle(Room room, String type, Consumption consumption,
        double breakChance, int maxSpeed, String size, String sportType) {
        super(room, type, consumption, breakChance);
        this.maxSpeed = maxSpeed;
        this.size = size;
        this.sportType = sportType;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String getSportType() {
        return sportType;
    }
}
