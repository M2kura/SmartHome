package cz.cvut.omo.smarthome.house.device;

public class Consumption {
    private String device;
    private String unitName;
    private int unitRate;
    private double unitPrice;
    private int unitAmount;

    public Consumption(String type) {
        this.device = type;
    }

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
