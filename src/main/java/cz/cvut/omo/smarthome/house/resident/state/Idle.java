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
        // 50/50 split between device usage and sports
        boolean doSports = Math.random() < 0.5;

        if (doSports) {
            // Try to do sports
            Optional<Device> sportsEquipment = em.getSportsEquipment();
            if (sportsEquipment.isPresent()) {
                Device equipment = sportsEquipment.get();
                System.out.println(resident.getName() + " is doing sports with " + equipment.getType());
                resident.useDevice(equipment);
            } else {
                // No sports equipment available, person waits
                System.out.println(resident.getName() + " wants to do sports, but all equipment is in use. Waiting...");
                resident.emptyDevice();
            }
        } else {
            // Use regular devices based on person type
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
                // Children also try sports equipment
                Optional<Device> sportsEquipment = em.getSportsEquipment();
                if (sportsEquipment.isPresent()) {
                    Device equipment = sportsEquipment.get();
                    System.out.println(resident.getName() + " is playing with " + equipment.getType());
                    resident.useDevice(equipment);
                } else {
                    System.out.println(resident.getName() + " wants to play, but all equipment is in use. Waiting...");
                    resident.emptyDevice();
                }
            }
        }
    }
}
