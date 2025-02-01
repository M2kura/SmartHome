package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.ResidentState;
import cz.cvut.omo.smarthome.house.resident.Resident;

public class Sleeping extends ResidentState {
    public Sleeping(Resident resident) {
        super(resident);
        System.out.println(resident.getName() + " is tired and going to sleep now");
        resident.changeRoom(resident.getPersonalRoom());
    }

    @Override
    public void getAction() {
        resident.sleep();
    }
}
