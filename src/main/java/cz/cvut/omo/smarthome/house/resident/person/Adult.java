package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.device.GasBoiler;

public class Adult extends Person{

    public void toggleGas(GasBoiler gasBoiler){
        if (gasBoiler != null) {
            if (gasBoiler.isStateOn()) {
                gasBoiler.turnOff();
                System.out.println("Boiler is turned off!");
            } else {
                gasBoiler.turnOn();
                System.out.println("Boiler is turned on!");
            }
        }
        System.out.println("No boiler!");
    }

    public void getFood(Food food) {
        //TO DO
    }

    public void throwFoodAway(Food food) {
        //TO DO
    }
}
