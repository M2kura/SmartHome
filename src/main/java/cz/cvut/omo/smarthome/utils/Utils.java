package cz.cvut.omo.smarthome;

import java.io.IOException;

public class Utils {
    public static void switchRawMode(boolean on) {
        String command;
        if (on) command = "stty raw -echo </dev/tty";
        else command = "stty -raw echo </dev/tty";
        try {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", command}).waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
