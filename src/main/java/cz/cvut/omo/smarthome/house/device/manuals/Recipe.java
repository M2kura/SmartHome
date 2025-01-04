package cz.cvut.omo.smarthome.house.device.manuals;

import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private int portions;

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getPortions() {
        return portions;
    }
}
