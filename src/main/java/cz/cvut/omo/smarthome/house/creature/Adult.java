package cz.cvut.omo.smarthome.house.creature;

import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.device.Fridge;
import cz.cvut.omo.smarthome.house.room.Room;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class Adult extends Person {

    public void getFood(Food food) {
        //TO DO
    }

    public void throwFoodAway(Food food, Fridge fridge, Room Kitchen) {
        if (food == null) {
            System.out.println("No food in fridge!");
            return;
        }

        LocalDate today = LocalDate.now();
        if (food.getExpireDate().isBefore(today)) {
            //add event here
            changeRoom();
            fridge.takeFood(food);
            System.out.println(food.getName() + " has been thrown away!");
        }
    }
}