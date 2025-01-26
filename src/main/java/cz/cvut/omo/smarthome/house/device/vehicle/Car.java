package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.vehicle.Vehicle;
import java.util.Optional;

public class Car extends Vehicle{
    private int numOfSeats;

    public Car(Consumption consumption, Optional<String> manual,
        double breakChance, Room room, String type, int maxSpeed, String size) {
        super(consumption, manual, breakChance, room, type, maxSpeed, size);
        if (size.equals("Big")) this.numOfSeats = 5;
        else this.numOfSeats = 2;
    }
}
