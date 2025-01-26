package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.State;
import cz.cvut.omo.smarthome.house.resident.Resident;

public class SleepingState implements State{
    Resident resident;

    public SleepingState(Resident resident) {
        this.resident = resident;
        System.out.println(resident.getName() + " is tired and going to sleep now");
        resident.changeRoom(resident.getPersonalRoom());
    }

    @Override
    public void performAction() {
        resident.sleep();
    }
}
