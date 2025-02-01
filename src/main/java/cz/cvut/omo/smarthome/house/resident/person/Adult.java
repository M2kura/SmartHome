package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.device.Fridge;
import cz.cvut.omo.smarthome.house.Room;

public class Adult extends Person{
    public Adult(Room room, String name, String type) {
        super(room, name, type);
    }

    public void getFood(Food food) {
        //TO DO
    }

    public void throwFoodAway(Food food, Fridge fridge) {
    }
}
