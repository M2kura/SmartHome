package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.utils.UpdatableContainer;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.utils.Clock;

import java.util.List;
import java.util.ArrayList;

public class SmartHome extends UpdatableContainer {
    private List<Device> devices;
    private List<Resident> residents;

    @JsonCreator
    public SmartHome(@JsonProperty("floors") List<JsonNode> floorNodes) {
        this.childObjs = new ArrayList<>();
        this.report = Report.getReport(this);
        this.clock = Clock.getClock();
        for (JsonNode floor : floorNodes) {
            this.childObjs.add(new Floor(floor));
        }
    }

    public void generateReport(String type) {
        if (type == "HouseConfigurationReport")
            report.getHouseConfigurationReport();
    }

    @Override
    public void getAction() {
        System.out.println(clock.getCurrentTime());
        for (ChangableObj child : childObjs) {
            child.getAction();
        }
        clock.moveClock();
    }

    @Override
    public String getConfig() {
        String config = "House\n";
        for (ChangableObj child : childObjs) {
            config += child.getConfig();
        }
        return config;
    }
}
