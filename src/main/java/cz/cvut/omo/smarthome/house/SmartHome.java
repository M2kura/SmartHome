package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.utils.Utils;
import cz.cvut.omo.smarthome.utils.UpdatableContainer;
import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.EventManager;
import cz.cvut.omo.smarthome.utils.Clock;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.StringBuilder;

public class SmartHome extends UpdatableContainer {
    private List<Device> devices;
    private List<Resident> residents;

    @JsonCreator
    public SmartHome(@JsonProperty("floors") List<JsonNode> floorNodes) {
        this.childObjs = new ArrayList<>();
        this.clock = Clock.getClock();
        this.em = EventManager.getEM();
        for (JsonNode floor : floorNodes) {
            this.childObjs.add(new Floor(floor));
        }
        this.residents = getAllResidents();
        this.devices = getAllDevices();
    }

    public void getHouseConfigurationReport() {
        String content = "House Configuration Report\n"
        +clock.getTimePassed(true)+"\n"+clock.getCurrentTime()+"\n"
        +getConfig();
        try {
            Utils.writeReportToFile(content, "HouseConfigurationReport");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getEventReport() {
        String content = "Event Report\n"
        +clock.getTimePassed(true)+"\n"+clock.getCurrentTime()+"\n"
        +em.getReport();
        try {
            Utils.writeReportToFile(content, "EventReport");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getActivityAndUsageReport() {
        String content = "Activity and Usage Report\n"
        +clock.getTimePassed(true)+"\n"+clock.getCurrentTime()+"\n\n"
        +getUsage();
        try {
            Utils.writeReportToFile(content, "ActivityAndUsageReport");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsage() {
        StringBuilder output = new StringBuilder();
        for (Resident resident : residents) {
            output.append(resident.getName()+":\n");
            output.append(resident.getUsage());
        }
        return output.toString();
    }

    @Override
    public void getAction() {
        System.out.println(clock.getCurrentTime());
        for (Resident resident : residents) {
            resident.getAction();
        }
        for (Device device : devices) {
            device.getAction();
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
