package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.resident.person.Adult;

public class Dad extends Adult {
    public Dad(Room room, String name, String type) {
        super(room, name, type);
    }

    public void watchTV() {
        //TO DO
    }

    public void work(Device pc) {
        changeRoom(pc.getRoom());
        useDevice(pc);
        System.out.println(this.name + " is working");
    }
}
