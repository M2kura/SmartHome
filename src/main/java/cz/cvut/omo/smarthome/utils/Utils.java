package cz.cvut.omo.smarthome.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static void switchRawMode(boolean on) {
        if (isWindows()) {
            switchRawModeWindows(on);
        } else {
            switchRawModeUnix(on);
        }
    }

    private static void switchRawModeUnix(boolean on) {
        String command;
        if (on) {
            command = "stty raw -echo </dev/tty";
        } else {
            command = "stty -raw echo </dev/tty";
        }
        try {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", command}).waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void switchRawModeWindows(boolean on) {
        try {
            ProcessBuilder pb;
            if (on) {
                pb = new ProcessBuilder("cmd.exe", "/c", "mode", "con", "raw");
            } else {
                pb = new ProcessBuilder("cmd.exe", "/c", "mode", "con", "cooked");
            }

            pb.redirectErrorStream(true);

            Process process = pb.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isWindows() {
        return OS.contains("win");
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
