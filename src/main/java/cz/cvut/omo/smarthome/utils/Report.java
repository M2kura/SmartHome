package cz.cvut.omo.smarthome.utils;

import java.util.HashMap;

public class Report {
    private static Report instance;
    private HashMap<String, HashMap<String, Integer>> timePassed;

    private Report() {
    }

    public static Report getReport() {
        if (instance == null) {
            instance = new Report();
        }
        return instance;
    }
}
