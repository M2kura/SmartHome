package cz.cvut.omo.smarthome.house;

import cz.cvut.omo.smarthome.house.Floor;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.utils.UpdatableContainer;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.Report;

import java.util.List;

public class SmartHome extends UpdatableContainer {
    private List<Floor> floors;
    private List<Device> devices;

    public SmartHome(String config) {
        this.clock = Clock.getClock();
        this.report = Report.getReport();
        // Set floors, residents and devices according to config
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
