package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.State;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Event;

public class InvolvedState implements State{
    Resident resident;
    Event event;
    int timeLeft;

    public InvolvedState(Resident resident, Event event) {
        this.resident = resident;
        this.event = event;
        event.updateStatus();
        System.out.println(resident.getName()+" started "+event.getTask());
    }

    @Override
    public void performAction() {
        return;
    }
}
