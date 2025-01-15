package cz.cvut.omo.smarthome.house.device;

import java.util.Map;
import cz.cvut.omo.smarthome.house.room.Room;

public abstract class Device {
    protected boolean state;
    protected Map<String, Double> consumption;
    protected String deviceManual;
    protected Room currentRoom;

    //------Methods------

    public void getData() {
        //TO DO
    }
}
