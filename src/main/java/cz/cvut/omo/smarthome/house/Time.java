package cz.cvut.omo.smarthome.house;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private int timePassed;


    public void increaseTime() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timePassed += 10;
            }
        },0, 1000);
    }

    public int getTimePassed() {
        return timePassed;
    }
}
