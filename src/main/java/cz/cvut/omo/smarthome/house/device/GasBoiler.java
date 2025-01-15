package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.device.Device;

public class GasBoiler extends Device {
    private double temperature;


    //------Methods------


    public boolean isStateOn() {
        return state;
    }

    public void turnOn(){
        state = true;
    }

    public void turnOff() {
        state = false;
    }
    private void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
