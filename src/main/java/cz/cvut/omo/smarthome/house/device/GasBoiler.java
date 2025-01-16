package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.device.Device;

public class GasBoiler extends Device {
    private double temperature;


    //------Methods------


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
