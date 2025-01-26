package cz.cvut.omo.smarthome.utils;

public class Clock {
    private static Clock instance;
    private int daysPassed;
    private int hoursPassed;
    private int minutesPassed;

    private Clock() {
        this.daysPassed = 0;
        this.hoursPassed = 0;
        this.minutesPassed = 0;
    }

    public static Clock getClock() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public void moveClock() {
        minutesPassed += 15;
        if (minutesPassed == 60) {
            minutesPassed = 0;
            hoursPassed++;
            if (hoursPassed == 24) {
                hoursPassed = 0;
                daysPassed++;
            }
        }
    }

    public String getCurrentTime() {
        int hours = hoursPassed >= 16 ? 8 - (24 % hoursPassed) : hoursPassed + 8;
        return String.format("Current time - %02d:%02d", hours, minutesPassed);
    }

    public String getTimePassed() {
        String days = "";
        if (daysPassed != 0) {
            days += daysPassed;
            if (daysPassed == 1) {
                days += " day ";
            } else {
                days += " days ";
            }
        }
        String hours = "";
        if (hoursPassed != 0) {
            hours += hoursPassed;
            if (hoursPassed == 1) {
                hours += " hour ";
            } else {
                hours += " hours ";
            }
        }
        String minutes = minutesPassed != 0 ? minutesPassed + " minutes " : "";
        return "Time passed - " + days + hours + minutes;
    }
}
