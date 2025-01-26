package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.SmartHome;
import cz.cvut.omo.smarthome.utils.Clock;
import cz.cvut.omo.smarthome.utils.Utils;

import java.util.HashMap;
import java.io.IOException;

public class Report {
    private static Report instance;
    private SmartHome house;
    private Clock clock;

    private Report(SmartHome house) {
        this.house = house;
        this.clock = Clock.getClock();
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
        +clock.getTimePassed()+"\n"+clock.getCurrentTime()+"\n"
        +house.getConfig();
        try {
            Utils.writeReportToFile(content, "HouseConfigurationReport");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
