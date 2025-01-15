package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.device.Device;

public class RobotVacuum extends Device{
    private int batteryLevel;
    private boolean isCleaning;


    //------Methods------


    public void toggleCleaning() {
        if (isCleaning) {
            isCleaning = false;
            System.out.println("Vacuum has stopped working!");
        } else {
            isCleaning = true;
            System.out.println("Vacuum has started working!");
        }
    }

    public void reduceBattery(int amount) {
        if (isCleaning) {
            batteryLevel -= amount;
            if (batteryLevel < 0) {
                batteryLevel = 0;
                isCleaning = false;
                System.out.println("Vacuum has stopped working! Please charge your vacuum cleaner!");
            }
        }
    }
}
