package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.databind.JsonNode;
import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.RobotVacuum;
import cz.cvut.omo.smarthome.utils.ResidentFactory;
import cz.cvut.omo.smarthome.utils.DeviceFactory;
import cz.cvut.omo.smarthome.utils.UpdatableContainer;
import cz.cvut.omo.smarthome.utils.EventManager;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Room extends UpdatableContainer {
    private Floor floor;
    private String name;
    private int dirtLevel;

    public Room(JsonNode roomNode, Floor floor) {
        this.em = EventManager.getEM();
        this.floor = floor;
        this.name = roomNode.get("name").asText();
        this.childObjs = new ArrayList<>();
        for (JsonNode residentNode : roomNode.get("residents")) {
            Resident resident = ResidentFactory.createResident(residentNode, this);
            em.addObj(resident);
            this.childObjs.add(resident);
        }
        for (JsonNode deviceNode : roomNode.get("devices")) {
            Device device = DeviceFactory.createDevice(deviceNode, this);
            em.addObj(device);
            this.childObjs.add(device);
        }
    }

    public String getName() {
        return name;
    }

    public void addDevice(Device device) {
        this.childObjs.add(device);
    }

    public void removeDevice(Device device) {
        this.childObjs.remove(device);
    }

    public void addResident(Resident resident) {
        this.childObjs.add(resident);
    }

    public void removeResident(Resident resident) {
        this.childObjs.remove(resident);
    }

    public boolean isEmpty() {
        return childObjs.stream()
            .filter(obj -> obj instanceof Resident)
            .count() == 0 ? true : false;
    }

    public boolean isDirty() {
        return dirtLevel >= 60 ? true : false;
    }

    @Override
    public String getConfig() {
        StringBuilder configBuilder = new StringBuilder();
        configBuilder.append("    "+name+"\n      Devices:\n");
        this.childObjs.stream()
            .filter(obj -> obj instanceof Device)
            .forEach(device -> configBuilder.append(device.getConfig()));
        configBuilder.append("      Residents:\n");
        this.childObjs.stream()
            .filter(obj -> obj instanceof Resident)
            .forEach(resident -> configBuilder.append(resident.getConfig()));
        return configBuilder.toString();
    }
}
