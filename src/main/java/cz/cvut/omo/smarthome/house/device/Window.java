package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class Window extends Device{
    private String blindState;

    public Window(Room room, String type) {
        super(room, type, new Consumption("Window"), 0.1);
        setState(new TurnedOn(this));
        this.blindState = "Closed";
    }
}
