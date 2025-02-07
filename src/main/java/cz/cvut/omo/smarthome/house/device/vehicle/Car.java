package cz.cvut.omo.smarthome.house.device.vehicle;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.vehicle.Vehicle;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Car extends Vehicle{
    private int numOfSeats;

    public Car(Room room, String type) {
        super(room, type, new Consumption("electro", 3145), 0.1, 200, "Big");
        setState(new TurnedOff(this));
        this.numOfSeats = 5;
    }
}
