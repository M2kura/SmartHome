package cz.cvut.omo.smarthome.house.room;

import cz.cvut.omo.smarthome.house.room.Room;

public class Outside extends Room {
    private String weatherType;
    private int temperature;


    public String getWeather() {
        return weatherType;
    }

    public void updateWeather() {}
}
