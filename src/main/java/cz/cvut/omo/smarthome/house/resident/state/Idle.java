package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.*;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.*;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.Event;
import cz.cvut.omo.smarthome.utils.EventManager;

import java.util.Optional;

public class Idle extends ResidentState {

    public Idle(Resident resident) {
        super(resident);
    }

    @Override
    public void getAction() {
        if (resident.isTired()) {
            resident.setState(new Sleeping(resident));
            return;
        }
        Optional<Event> event = em.getEvent(resident);
        if (event.isPresent()) {
            Event nextTask = event.get();
            nextTask.updateStatus();
            ResidentState newState = new Involved(resident, nextTask);
            resident.setState(newState);
        } else {
            commonAction();
        }
        resident.doAction(true);
    }

    private void commonAction() {
        if (resident instanceof Teen) {
            Teen teen = (Teen) resident;
            teen.study();
        } else if (resident instanceof Dad) {
            Dad dad = (Dad) resident;
            Device pc = em.getDevice("PC").get();
            dad.work(pc);
        } else if (resident instanceof Mom) {
            Mom mom = (Mom) resident;
            Optional<Device> phone = em.getDevice("Phone");
            mom.callFriends();
        }
    }
}
