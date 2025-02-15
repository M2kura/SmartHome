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
            nextTask.updateStatus(resident.getName());
            ResidentState newState = new Involved(resident, nextTask);
            resident.setState(newState);
        } else {
            commonAction();
        }
        resident.doAction(true);
    }

    private void commonAction() {
        if (resident instanceof Teen)
            ((Teen)resident).study();
        else if (resident instanceof Dad) {
            Device pc = em.getDevice("PC").get();
            ((Dad)resident).work(pc);
        } else if (resident instanceof Mom) {
            Optional<Device> phone = em.getDevice("Phone");
            if (!phone.isPresent()) {
				resident.emptyDevice();
                System.out.println(resident.getName() + " wants to call her friends, but the Phone is broken!");
			} else
                ((Mom)resident).callFriends(phone.get());
        } else if (resident instanceof Child) {
        }
    }
}
