package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.SmartHome;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.EventManager;
import cz.cvut.omo.smarthome.utils.Utils;

import java.util.HashMap;
import java.io.IOException;

public class Report {
    private static Report instance;
    private SmartHome house;
    private Clock clock;
    private EventManager em;

    private Report(SmartHome house) {
        this.house = house;
        this.clock = Clock.getClock();
        this.em = EventManager.getEM();
    }

    public static Report getReport(SmartHome house) {
        if (instance == null) {
            instance = new Report(house);
        }
        return instance;
    }

    public static Report getReport() {
        return instance;
    }

    public void getHouseConfigurationReport() {
        String content = "House Configuration Report\n"
        +clock.getTimePassed(true)+"\n"+clock.getCurrentTime()+"\n"
        +house.getConfig();
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
}
