package cz.cvut.omo.smarthome.utils;

public class Clock {
    private static Clock instance;
    private int daysPassed;
    private int hoursPassed;
    private int minutesPassed;
    private int ticks;

    private Clock() {
        this.daysPassed = 0;
        this.hoursPassed = 0;
        this.minutesPassed = 0;
        this.ticks = 0;
    }

    public static Clock getClock() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public void moveClock() {
        ticks++;
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

    public int getTicks() {
        return ticks;
    }

    public String ticksToString(int tick) {
        int min = tick%4;
        int hrs = tick / 4;
        int ds = hrs / 24;
        String minutes = "";
        if (min == 1)
            minutes = "15 minutes";
        else if (min == 2)
            minutes = "30 minutes";
        else if (min == 3)
            minutes = "45 minutes";
        String hours = "";
        if (hrs%24 == 1)
            hours = "1 hour ";
        else if (hrs%24 > 1)
            hours = hrs%24+" hours ";
        String days = "";
        if (ds == 1)
            days = "1 day ";
        else if (ds > 1) 
            days = ds+" days ";
        return days+hours+minutes;
    }

    public String getCurrentTime() {
        int hours = hoursPassed >= 16 ? 8 - (24 % hoursPassed) : hoursPassed + 8;
        return String.format("Current time - %02d:%02d", hours, minutesPassed);
    }

    public String getTimePassed(boolean longer) {
        String prefix = longer ? "Time passed - " : "";
        String days = "";
        if (daysPassed == 1)
            days = "1 day ";
        else if (daysPassed > 1)
            days = daysPassed+" days ";
        String hours = "";
        if (hoursPassed == 1)
            hours = "1 hour ";
        else if (hoursPassed > 1)
            hours = hoursPassed+" hours ";
        String minutes = minutesPassed != 0 ? minutesPassed + " minutes " : "";
        return prefix + days + hours + minutes;
    }
}
