package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.ResidentState;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Event;

public class Involved extends ResidentState {
    Event event;
    int timeLeft;

    public Involved(Resident resident, Event event) {
        super(resident);
        this.event = event;
        event.updateStatus();
        System.out.println(resident.getName()+" started "+event.getTask());
    }

    @Override
    public void getAction() {
        return;
    }

    @Override
    public void getUpdate() {
        return;
    }
}
