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
import cz.cvut.omo.smarthome.utils.DeviceIterator;
import cz.cvut.omo.smarthome.utils.ResidentIterator;

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

    /**
     * Returns an iterator for all devices in the smart home.
     * @return DeviceIterator for iterating through all devices
     */
    public DeviceIterator getDeviceIterator() {
        return new DeviceIterator(devices);
    }

    /**
     * Returns a filtered iterator for devices of a specific type.
     * @param deviceType the type of device to filter by
     * @return DeviceIterator that only returns devices of the specified type
     */
    public DeviceIterator getDeviceIteratorByType(String deviceType) {
        return DeviceIterator.filterByType(devices, deviceType);
    }

    /**
     * Returns an iterator for all residents in the smart home.
     * @return ResidentIterator for iterating through all residents
     */
    public ResidentIterator getResidentIterator() {
        return new ResidentIterator(residents);
    }

    /**
     * Returns a filtered iterator for only Person residents.
     * @return ResidentIterator that only returns Person residents
     */
    public ResidentIterator getPersonIterator() {
        return ResidentIterator.filterPersons(residents);
    }

    /**
     * Returns a filtered iterator for only Animal residents.
     * @return ResidentIterator that only returns Animal residents
     */
    public ResidentIterator getAnimalIterator() {
        return ResidentIterator.filterAnimals(residents);
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
        // Using iterator pattern to iterate through residents
        ResidentIterator residentIterator = getResidentIterator();
        while (residentIterator.hasNext()) {
            Resident resident = residentIterator.next();
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
        // Using iterator pattern to iterate through devices
        DeviceIterator deviceIterator = getDeviceIterator();
        while (deviceIterator.hasNext()) {
            Device device = deviceIterator.next();
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
        // Using iterator pattern to iterate through residents
        ResidentIterator residentIterator = getResidentIterator();
        while (residentIterator.hasNext()) {
            Resident resident = residentIterator.next();
            resident.getAction();
        }
        // Using iterator pattern to iterate through devices
        DeviceIterator deviceIterator = getDeviceIterator();
        while (deviceIterator.hasNext()) {
            Device device = deviceIterator.next();
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
