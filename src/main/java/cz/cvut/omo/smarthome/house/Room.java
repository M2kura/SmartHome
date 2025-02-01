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

public class Room extends UpdatableContainer {
    private List<Device> devices;
    private List<Resident> residents;
    private Floor floor;
    private String name;
    private int dirtLevel;

    public Room(JsonNode roomNode, Floor floor) {
        this.em = EventManager.getEM();
        this.floor = floor;
        this.name = roomNode.get("name").asText();
        this.childObjs = new ArrayList<>();
        this.residents = new ArrayList<>();
        this.devices = new ArrayList<>();
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
    public void addDevice() {}

    public void removeDevice() {}

    public void addResident(Resident resident) {}

    public void removeResident(Resident resident) {}

    public boolean isEmpty() {
        return residents.size() == 0 ? true : false;
    }

    public boolean isDirty() {
        return dirtLevel >= 60 ? true : false;
    }

    @Override
    public String getConfig() {
        String config = "    "+name+"\n      "+"Devices:\n";
        for (Device device : devices) {
            config += device.getConfig();
        }
        config += "      Residents:\n";
        for (Resident resident : residents) {
            config += resident.getConfig();
        }
        return config;
    }
}
