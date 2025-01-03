package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Time;
import cz.cvut.omo.smarthome.house.room.Room;

import java.util.Map;

public abstract class Device {
    protected boolean state;
    protected Map<String, Double> consumption;
    protected String deviceManual;
    protected Room currentRoom;
    protected Sensor sensor;
    private Time clock;


    //------Methods------


    public void getData() {
        //TO DO
    }
}
