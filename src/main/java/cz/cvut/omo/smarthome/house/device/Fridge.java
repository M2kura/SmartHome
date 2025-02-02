package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;
import java.util.List;

public class Fridge extends Device {
    private List<Food> groceries;

    public Fridge(Room room, String type) {
        super(room, type, new Consumption("Fridge"), 0.005);
        setState(new TurnedOn(this));
    }

    public void haveIngredients() {}

    public void haveHealthyFood() {}

    public void addFood() {}

    public void removeFood() {}

    @Override
    public void fix() {
        setState(new TurnedOn(this));
    }
}
