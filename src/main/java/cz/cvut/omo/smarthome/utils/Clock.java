package cz.cvut.omo.smarthome.utils;

public class Clock {
    private static Clock instance;
    private int timePassed;

    private Clock() {
        this.timePassed = 0;
    }

    public static Clock getClock() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public void increaseTime() {
        timePassed += 10;
    }

    public int getTimePassed() {
        return timePassed;
    }
}
