package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class HeatingSystem extends Device{
    private double temperature;
    private double targetTemperature;
    private boolean isHeating;

    public HeatingSystem(Room room, String type) {
        super(room, type, new Consumption("electro", 1250), 0.0001);
        setState(new TurnedOn(this));
        this.temperature = 20;
        this.targetTemperature = 22;
        this.isHeating = false;
    }

    public void setTargetTemperature(double temp) {
        if (temp >= 15 && temp <= 30) {
            this.targetTemperature = temp;
            if (state instanceof TurnedOn) {
                adjustHeating();
            }
        }
    }

    public void increaseTemperature() {
        if (targetTemperature < 30) {
            targetTemperature += 0.5;
            if (state instanceof TurnedOn) {
                adjustHeating();
            }
        }
    }

    public void decreaseTemperature() {
        if (targetTemperature > 15) {
            targetTemperature -= 0.5;
            if (state instanceof TurnedOn) {
                adjustHeating();
            }
        }
    }

    private void adjustHeating() {
        if (temperature < targetTemperature) {
            isHeating = true;
            heat();
        } else if (temperature > targetTemperature) {
            isHeating = false;
        }
    }

    public void heat() {
        if (state instanceof TurnedOn && isHeating && temperature < targetTemperature) {
            temperature += 0.5;
            consume(1.0);
        }
    }

    public void updateTemperature(double ambientTemp) {
        if (state instanceof TurnedOn) {
            if (isHeating && temperature < targetTemperature) {
                heat();
            } else if (!isHeating && temperature > ambientTemp) {
                temperature -= 0.1;
            }
        } else {
            temperature = Math.max(ambientTemp, temperature - 0.2);
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    public boolean isHeating() {
        return isHeating;
    }

    @Override
    public void resetState() {
        setState(new TurnedOn(this));
    }
}
