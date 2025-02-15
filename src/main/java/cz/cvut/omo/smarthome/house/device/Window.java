package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Window extends Device{
    private String blindState;

    public Window(Room room, String type) {
        super(room, type, new Consumption("electro", 2), 0.001);
        setState(new TurnedOn(this));
        this.blindState = "Closed";
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
