package cz.cvut.omo.smarthome.house.device;

import java.time.LocalDate;
import java.util.Date;

public class Food {
    private String name;
    private boolean healthyFood;
    private LocalDate expireDate;

    public Food() {
        this.name = name;
        this.expireDate = expireDate;
    }

    public String getName() {
        return name;
    }

    public void isHealthy() {}
    public void isExpired() {}

    public void updateED() {}
}
