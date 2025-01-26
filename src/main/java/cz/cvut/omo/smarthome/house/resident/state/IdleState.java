package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.*;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.*;
import cz.cvut.omo.smarthome.utils.Event;
import cz.cvut.omo.smarthome.utils.EventManager;

import java.util.Optional;

public class IdleState implements State{
    Resident resident;
    EventManager em;

    public IdleState(Resident resident) {
        this.resident = resident;
        this.em = EventManager.getEM();
    }

    @Override
    public void performAction() {
        if (resident.isTired()) {
            resident.setState(new SleepingState(resident));
            return;
        }
        Optional<Event> event = em.getEvent(resident);
        if (event.isPresent()) {
            Event nextTask = event.get();
            State newState = new InvolvedState(resident, nextTask);
            resident.setState(newState);
        } else {
            if (resident instanceof Teen) {
                Teen teen = (Teen) resident;
                teen.study();
            }
        }
    }
}
