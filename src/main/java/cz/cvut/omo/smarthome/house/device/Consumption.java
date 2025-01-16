package cz.cvut.omo.smarthome.house.device;

public class Consumption {
    private String unitName;
    private int unitRate;
    private double unitPrice;
    private int unitAmount;

    public int getRate() {
        return unitRate;
    }

    public double getPrice() {
        return unitPrice;
    }

    public int getAmount() {
        return unitAmount;
    }

    public void updateAmount() {}
}
