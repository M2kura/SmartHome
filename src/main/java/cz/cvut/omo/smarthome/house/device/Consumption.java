package cz.cvut.omo.smarthome.house.device;

import java.lang.StringBuilder;

public class Consumption {
    private double electroPrice = 0.005;
    private double waterPrice = 0.14488;
    private String type;
    private double rate;
    private double rate2;
    private double amount = 0;
    private double amount2 = 0;

    public Consumption(String type, double rate) {
        this.type = type;
        this.rate = rate;
    }

    public Consumption(String type, double rate, double rate2) {
        this.type = type;
        this.rate = rate;
        this.rate = rate2;
    }

    public void update(boolean full) {
        if (full) {
            amount += rate;
            if (type.equals("both"))
                amount2 += rate2;
        } else {
            amount += rate/2;
            if (type.equals("both"))
                amount2 += rate2/2;
        }
    }

    public String getReport() {
        if (type.equals("electro"))
            return String.format("%.1f kWh, %.2f Kč", amount/1000, electroPrice*amount);
        else if (type.equals("water"))
            return String.format("%.1f m3, %.2f Kč", amount/1000, waterPrice*amount);
        else if (type.equals("both"))
            return String.format("%.1f kWh, %.1f m3, %.2f Kč", amount/1000, amount2/1000,
            electroPrice*amount + waterPrice*amount2);
        return "No consumption";
    }

    public double getCost() {
        if (type.equals("electro"))
            return electroPrice*amount;
        else if (type.equals("water"))
            return waterPrice*amount;
        else if (type.equals("both"))
            return electroPrice*amount + waterPrice*amount2;
        return 0;
    }
}
