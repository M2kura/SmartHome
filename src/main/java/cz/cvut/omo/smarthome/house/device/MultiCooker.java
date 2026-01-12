package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import cz.cvut.omo.smarthome.house.device.manuals.Recipe;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class MultiCooker extends Device{
    private List<Recipe> recipes;
    private Optional<Recipe> currentRecipe;
    private int cookingProgress;

    public MultiCooker(Room room, String type) {
        super(room, type, new Consumption("electro", 450), 0.5);
        setState(new TurnedOff(this));
        this.recipes = new ArrayList<>();
        this.currentRecipe = Optional.empty();
        this.cookingProgress = 0;
    }

    public void addRecipe(Recipe recipe) {
        if (recipe != null) {
            recipes.add(recipe);
        }
    }

    public boolean startCooking(Recipe recipe) {
        if (state instanceof TurnedOn && recipe != null) {
            this.currentRecipe = Optional.of(recipe);
            this.cookingProgress = 0;
            return true;
        }
        return false;
    }

    public void updateCooking() {
        if (currentRecipe.isPresent() && state instanceof TurnedOn) {
            cookingProgress++;
        }
    }

    public boolean isCookingComplete() {
        return currentRecipe.isPresent() && cookingProgress >= 100;
    }

    public Optional<Recipe> finishCooking() {
        if (isCookingComplete()) {
            Optional<Recipe> finished = currentRecipe;
            currentRecipe = Optional.empty();
            cookingProgress = 0;
            return finished;
        }
        return Optional.empty();
    }

    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipes);
    }

    public int getCookingProgress() {
        return cookingProgress;
    }
}
