package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.state.ResidentState;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.Dad;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.PC;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Event;

import java.util.Optional;

public class Involved extends ResidentState {
    private Event event;
    private int timeLeft;
    private Optional<ChangableObj> with;
    private Optional<Room> room;

    public Involved(Resident resident, Event event) {
        super(resident);
        this.event = event;
        this.timeLeft = event.getTime()-1;
        this.with = event.getInvolved();
        this.room = event.getRoom();
        if (room.isPresent())
            resident.changeRoom(room.get());
        System.out.println(resident.getName()+" started "+event.getTask());
    }

    @Override
    public void getAction() {
        if (timeLeft > 0)
            doAction();
        else
            finishEvent();
        resident.doAction(false);
    }

    private void doAction() {
        if (resident instanceof Dad && event.getType().equals("broken")
            && ((Device)with.get()).getManual().isEmpty()) {
            Device device = (Device) with.get();
            Device pc = em.getDevice("PC").get();
            resident.changeRoom(pc.getRoom());
            System.out.println(resident.getName()+" is searching for "+device.getType()+" manual on PC");
            if (((PC)pc).searchManual(device))
                System.out.println(resident.getName()+" found a "+device.getType()+" manual");
            return;
        }
        if (room.isPresent())
            resident.changeRoom(room.get());
        System.out.println(resident.getName()+" is "+event.getTask());
        timeLeft--;
    }

    private void finishEvent() {
        System.out.println(resident.getName()+" finished "+event.getTask());
        event.updateStatus();
        if (with.isPresent() && with.get() instanceof Device) {
            Device device = (Device) with.get();
            device.fix();
        }
        resident.setState(new Idle(resident));
    }
}
