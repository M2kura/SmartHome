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

    public boolean isHealthy() {
        return healthyFood;
    }

    public boolean isExpired() {
        return expireIn <= 0;
    }

    public void updateED() {
        expireIn--;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setHealthyFood(boolean healthy) {
        this.healthyFood = healthy;
    }
}
