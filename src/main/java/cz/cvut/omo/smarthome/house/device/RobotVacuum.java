package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class RobotVacuum extends Device{
    private int batteryLevel;
    private int dustbinLevel;
    private boolean isCleaning;
    private int cleanedArea;

    public RobotVacuum(Room room, String type) {
        super(room, type, new Consumption("electro", 20), 0.1);
        setState(new TurnedOff(this));
        this.batteryLevel = 100;
        this.dustbinLevel = 0;
        this.isCleaning = false;
        this.cleanedArea = 0;
    }

    public boolean startCleaning() {
        if (state instanceof TurnedOn && batteryLevel > 20 && dustbinLevel < 100) {
            isCleaning = true;
            return true;
        }
        return false;
    }

    public void stopCleaning() {
        isCleaning = false;
    }

    public void clean(int area) {
        if (isCleaning && batteryLevel > 0) {
            cleanedArea += area;
            batteryLevel -= 5;
            dustbinLevel += 10;
            consume(0.5);

            if (batteryLevel <= 20 || dustbinLevel >= 100) {
                stopCleaning();
            }
        }
    }

    public void charge() {
        if (batteryLevel < 100) {
            batteryLevel = Math.min(100, batteryLevel + 20);
            consume(0.2);
        }
    }

    public void emptyDustbin() {
        dustbinLevel = 0;
    }

    public boolean needsCharging() {
        return batteryLevel <= 20;
    }

    public boolean needsEmptying() {
        return dustbinLevel >= 80;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public int getDustbinLevel() {
        return dustbinLevel;
    }

    public int getCleanedArea() {
        return cleanedArea;
    }

    public boolean isCleaning() {
        return isCleaning;
    }
}
