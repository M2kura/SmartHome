package cz.cvut.omo.smarthome;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> listConfigs() throws IOException {
        List<String> configs = new ArrayList<>();

        File confDir = new File(System.getProperty("user.dir")
            + File.separator + "resources" + File.separator + "configs");

        if (!confDir.exists() || !confDir.isDirectory()) {
            throw new IOException("Cannot find directory: " + confDir.getAbsolutePath());
        }

        File[] files = confDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                configs.add(file.getName());
            }
        }

        return configs;
    }
}
