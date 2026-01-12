package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Window extends Device{
    private String blindState;
    private boolean isOpen;
    private int blindPosition;

    public Window(Room room, String type) {
        super(room, type, new Consumption("electro", 2), 0.001);
        setState(new TurnedOn(this));
        this.blindState = "Closed";
        this.isOpen = false;
        this.blindPosition = 0;
    }

    public void openBlinds() {
        if (state instanceof TurnedOn) {
            blindState = "Open";
            blindPosition = 100;
            consume(0.1);
        }
    }

    public void closeBlinds() {
        if (state instanceof TurnedOn) {
            blindState = "Closed";
            blindPosition = 0;
            consume(0.1);
        }
    }

    public void setBlindsPosition(int position) {
        if (state instanceof TurnedOn && position >= 0 && position <= 100) {
            this.blindPosition = position;
            if (position == 0) {
                blindState = "Closed";
            } else if (position == 100) {
                blindState = "Open";
            } else {
                blindState = "Partially Open";
            }
            consume(0.05);
        }
    }

    public void openWindow() {
        if (!isOpen) {
            isOpen = true;
        }
    }

    public void closeWindow() {
        if (isOpen) {
            isOpen = false;
        }
    }

    public String getBlindsState() {
        return blindState;
    }

    public int getBlindsPosition() {
        return blindPosition;
    }

    public boolean isWindowOpen() {
        return isOpen;
    }

    @Override
    public void resetState() {
        setState(new TurnedOn(this));
    }

    @Override
    public String getConsumption() {
        return "Window in "+room.getName()+": "+consumption.getReport()+"\n";
    }
}
