package cz.cvut.omo.smarthome.house.room;

import cz.cvut.omo.smarthome.house.device.vehicle.Vehicle;

import java.util.List;

public class Garage extends Room {
    private List<Vehicle> vehiclesParked;


    //------Methods------

    public List<Vehicle> getParkedVehicles() {
        return vehiclesParked;
    }
}
