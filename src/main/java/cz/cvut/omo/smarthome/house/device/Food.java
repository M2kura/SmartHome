package cz.cvut.omo.smarthome.house.device;

public class Food {
    private String name;
    private boolean healthyFood;
    private int expireIn;

    public Food(String name, int expireDate) {
        this.name = name;
        this.healthyFood = true;
        this.expireIn = expireDate;
    }

    public String getName() {
        return name;
    }

    public void isHealthy() {}
    public void isExpired() {}

    public void updateED() {}
}
