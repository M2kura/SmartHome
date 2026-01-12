package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Sensor extends Device{
    private String sensorType;
    private double currentValue;
    private double threshold;
    private boolean alertTriggered;

    public Sensor(Room room, String type) {
        super(room, type, new Consumption("electro", 2), 0.0001);
        setState(new TurnedOn(this));
        this.sensorType = type;
        this.currentValue = 0.0;
        this.threshold = 100.0;
        this.alertTriggered = false;
    }

    public void setSensorType(String type) {
        this.sensorType = type;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double readValue() {
        if (state instanceof TurnedOn) {
            currentValue = Math.random() * 100;
            consume(0.05);
            checkThreshold();
            return currentValue;
        }
        return -1;
    }

    public double sense() {
        return readValue();
    }

    private void checkThreshold() {
        if (currentValue > threshold) {
            alertTriggered = true;
        } else {
            alertTriggered = false;
        }
    }

    public boolean detectMotion() {
        if (state instanceof TurnedOn && sensorType.toLowerCase().contains("motion")) {
            return Math.random() > 0.7;
        }
        return false;
    }

    public boolean detectHumidity() {
        if (state instanceof TurnedOn && sensorType.toLowerCase().contains("humidity")) {
            currentValue = Math.random() * 100;
            return currentValue > 80;
        }
        return false;
    }

    public boolean detectWind() {
        if (state instanceof TurnedOn && sensorType.toLowerCase().contains("wind")) {
            currentValue = Math.random() * 50;
            return currentValue > 30;
        }
        return false;
    }

    public boolean detectSmoke() {
        if (state instanceof TurnedOn && sensorType.toLowerCase().contains("smoke")) {
            return Math.random() > 0.95;
        }
        return false;
    }

    public String getSensorType() {
        return sensorType;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double getThreshold() {
        return threshold;
    }

    public boolean isAlertTriggered() {
        return alertTriggered;
    }

    public void resetAlert() {
        alertTriggered = false;
    }

    @Override
    public void resetState() {
        setState(new TurnedOn(this));
    }
}
