package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.*;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.*;
import cz.cvut.omo.smarthome.utils.Event;
import cz.cvut.omo.smarthome.utils.EventManager;

import java.util.Optional;

public class Idle extends ResidentState {

    public Idle(Resident resident) {
        super(resident);
    }

    @Override
    public void getAction() {
        Optional<Event> event = em.getEvent(resident);
        if (event.isPresent()) {
            Event nextTask = event.get();
            ResidentState newState = new Involved(resident, nextTask);
            resident.setState(newState);
        } else {
            commonAction();
        }
    }

    @Override
    public void getUpdate() {
        if (resident.isTired()) {
            resident.setState(new Sleeping(resident));
        }
    }

    private void commonAction() {
        if (resident instanceof Teen) {
            Teen teen = (Teen) resident;
            teen.study();
        } else if (resident instanceof Dad) {
            Dad dad = (Dad) resident;
            dad.work();
        }
    }
}
