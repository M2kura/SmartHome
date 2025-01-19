package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.databind.JsonNode;
import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.utils.ResidentFactory;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.DeviceFactory;
import cz.cvut.omo.smarthome.house.device.RobotVacuum;

import java.util.List;
import java.util.ArrayList;

public class Room {
    private List<Device> devices;
    private List<Resident> residents;
    private Floor floor;
    private String name;
    private int dirtLevel;

    public Room(JsonNode roomNode, Floor floor) {
        this.floor = floor;
        this.name = roomNode.get("name").asText();
        this.residents = new ArrayList<>();
        this.devices = new ArrayList<>();
        for (JsonNode residentNode : roomNode.get("residents")) {
            this.residents.add(ResidentFactory.createResident(residentNode, this));
        }
        for (JsonNode deviceNode : roomNode.get("devices")) {
            this.devices.add(DeviceFactory.createDevice(deviceNode, this));
        }
    }

    public String getName() {
        return name;
    }
    public void addDevice() {}

    public void removeDevice() {}

    public void addResident(Resident resident) {}

    public void removeResident(Resident resident) {}

    public void isEmpty() {}

    public void isDirty() {
        if( dirtLevel > 60 ) {
            RobotVacuum.use();
        }
    }

}
