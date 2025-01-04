package cz.cvut.omo.smarthome.house.room;

public class Outside extends Room {
    private String weatherType;
    private int temperature;


    public String getWeather() {
        return weatherType;
    }

    public void updateWeather() {}
}
