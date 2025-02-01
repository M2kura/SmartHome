package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.ResidentState;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Event;

import java.util.Optional;

public class Involved extends ResidentState {
    private Event event;
    private int timeLeft;
    private Optional<ChangableObj> with;

    public Involved(Resident resident, Event event) {
        super(resident);
        this.event = event;
        event.updateStatus();
        this.timeLeft = event.getTime()-1;
        this.with = event.getInvolved();
        if (with.isPresent()) {
            if (with.get() instanceof Device) {
                Device device = (Device) with.get();
                resident.changeRoom(device.getRoom());
            } else {
                Resident residentWith = (Resident) with.get();
                resident.changeRoom(residentWith.getRoom());
            }
        }
        System.out.println(resident.getName()+" started "+event.getTask());
    }

    @Override
    public void getAction() {
        if (timeLeft > 0) {
            System.out.println(resident.getName()+" is "+event.getTask());
            timeLeft--;
        } else {
            System.out.println(resident.getName()+" finished "+event.getTask());
            event.updateStatus();
            if (with.isPresent() && with.get() instanceof Device) {
                Device device = (Device) with.get();
                device.fix();
            }
            resident.setState(new Idle(resident));
        }
        resident.doAction(false);
    }
}
