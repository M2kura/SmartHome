package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.UpdatableContainer;
import cz.cvut.omo.smarthome.utils.Report;

import java.util.List;
import java.util.ArrayList;

public class SmartHome extends UpdatableContainer {
    private List<Floor> floors;
    private List<Device> devices;

    @JsonCreator
    public SmartHome(@JsonProperty("floors") List<JsonNode> floorNodes) {
        this.floors = new ArrayList<>();
        this.report = Report.getReport();
        for (JsonNode floor : floorNodes) {
            this.floors.add(new Floor(floor));
        }
    }

    @Override
    public void getUpdate() {
        System.out.println("Simulation running...");
    }

    @Override
    public void getAction() { return; }

    @Override
    public String getConfig() { return ""; }
}
