package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import java.util.Optional;
import java.util.List;

public class Fridge extends Device {
    private List<Food> groceries;

    public Fridge(Consumption consumption, Optional<String> manual,
        double breakChance, Room room, String type) {
        super(consumption, manual, breakChance, room, type);
    }

    public void haveIngredients() {}

    public void haveHealthyFood() {}

    public void addFood() {}

    public void removeFood() {}
}
