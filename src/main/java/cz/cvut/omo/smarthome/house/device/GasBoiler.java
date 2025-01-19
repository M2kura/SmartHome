package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import java.util.Optional;

public class GasBoiler extends Device {
    private double temperature;

    public GasBoiler(Consumption consumption, Optional<String> manual,
        double breakChance, Room room) {
        super(consumption, manual, breakChance, room);
    }

    public boolean isStateOn() {
        return true;
    }

    public void turnOn(){
    }

    public void turnOff() {
    }
    private void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
