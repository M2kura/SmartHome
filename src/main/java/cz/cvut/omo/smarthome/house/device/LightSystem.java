package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class LightSystem extends Device{
    private int brightness;
    private String colorMode;

    public LightSystem(Room room, String type) {
        super(room, type, new Consumption("electro", 300), 0.0001);
        setState(new TurnedOn(this));
        this.brightness = 100;
        this.colorMode = "White";
    }

    public void setBrightness(int brightness) {
        if (state instanceof TurnedOn && brightness >= 0 && brightness <= 100) {
            this.brightness = brightness;
            consume(0.1);
        }
    }

    public void increaseBrightness() {
        if (state instanceof TurnedOn && brightness < 100) {
            brightness = Math.min(100, brightness + 10);
            consume(0.1);
        }
    }

    public void decreaseBrightness() {
        if (state instanceof TurnedOn && brightness > 0) {
            brightness = Math.max(0, brightness - 10);
            consume(0.1);
        }
    }

    public void setColorMode(String mode) {
        if (state instanceof TurnedOn) {
            this.colorMode = mode;
            consume(0.05);
        }
    }

    public void dim() {
        if (state instanceof TurnedOn) {
            this.brightness = 30;
            consume(0.1);
        }
    }

    public void fullBrightness() {
        if (state instanceof TurnedOn) {
            this.brightness = 100;
            consume(0.1);
        }
    }

    public int getBrightness() {
        return brightness;
    }

    public String getColorMode() {
        return colorMode;
    }

    @Override
    public void resetState() {
        setState(new TurnedOn(this));
    }
}
