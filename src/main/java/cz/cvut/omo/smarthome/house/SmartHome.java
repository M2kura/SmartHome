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
        +clock.getTimePassed(true)+"\n"+clock.getCurrentTime()+"\n\n";
        for (Resident resident : residents) {
            content += resident.getUsage();
        }
        try {
            Utils.writeReportToFile(content, "ActivityAndUsageReport");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getConsumptionReport() {
        String content = "Consumption Report\n"
        +clock.getTimePassed(true)+"\n\n"
        +"Water price - 144,88 Kč/m3\nElectricity price - 5Kč/kWh\n\n";
        int lightCount = 0;
        int heatingCount = 0;
        double totalCost = 0;
        for (Device device : devices) {
            if (device.getType().equals("Heating System")) {
                if (heatingCount == 0)
                    heatingCount++;
                else
                    continue;
            } else if (device.getType().equals("Light System")) {
                if (lightCount == 0)
                    lightCount++;
                else
                    continue;
            }
            content += device.getConsumption();
            totalCost += device.getCost();
        }
        content += String.format("\nTotal cost: %.2f Kč", totalCost);
        try {
            Utils.writeReportToFile(content, "ConsumptionReport");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
