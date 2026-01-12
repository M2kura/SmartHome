package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class Fridge extends Device {
    private List<Food> groceries;

    public Fridge(Room room, String type) {
        super(room, type, new Consumption("electro", 50), 0.005);
        setState(new TurnedOn(this));
        this.groceries = new ArrayList<>();
    }

    public boolean haveIngredients(String ingredient) {
        return groceries.stream()
            .anyMatch(food -> food.getName().equalsIgnoreCase(ingredient));
    }

    public boolean haveHealthyFood() {
        return groceries.stream().anyMatch(Food::isHealthy);
    }

    public void addFood(Food food) {
        if (food != null) {
            groceries.add(food);
        }
    }

    public Food removeFood(String foodName) {
        Optional<Food> food = groceries.stream()
            .filter(f -> f.getName().equalsIgnoreCase(foodName))
            .findFirst();
        food.ifPresent(groceries::remove);
        return food.orElse(null);
    }

    public int getFoodCount() {
        return groceries.size();
    }

    public boolean isEmpty() {
        return groceries.isEmpty();
    }

    public void updateFoodExpiration() {
        groceries.forEach(Food::updateED);
        groceries.removeIf(Food::isExpired);
    }

    @Override
    public void resetState() {
        setState(new TurnedOn(this));
    }
}
