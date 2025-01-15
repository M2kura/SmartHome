package cz.cvut.omo.smarthome;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import cz.cvut.omo.smarthome.house.SmartHome;

public class Main {
    public static void main(String[] args) {
        AtomicBoolean finish = new AtomicBoolean(false);
        SmartHome house = new SmartHome("");

        Thread inputThread = new Thread(() -> {
            try {
                while (true) {
                    int input = System.in.read();
                    if (input == 'q') {
                        finish.set(true);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Utils.switchRawMode(false);
            }
        });

        Thread outputThread = new Thread(() -> {
            while (!finish.get()) {
                try {
                    Utils.switchRawMode(false);
                    house.getUpdate();
                    Utils.switchRawMode(true);
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Utils.switchRawMode(true);
        inputThread.start();
        outputThread.start();

        try {
            inputThread.join();
            outputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
