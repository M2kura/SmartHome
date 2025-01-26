package cz.cvut.omo.smarthome.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Utils {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static void switchRawMode(boolean on) {
        if (OS.contains("win")) switchRawModeWindows(on);
        else switchRawModeUnix(on);
    }

    private static void switchRawModeUnix(boolean on) {
        String command;
        if (on) command = "stty raw -echo </dev/tty";
        else command = "stty -raw echo </dev/tty";
        try {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", command}).waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void switchRawModeWindows(boolean on) {
        try {
            ProcessBuilder pb;
            if (on) pb = new ProcessBuilder("cmd.exe", "/c", "mode", "con", "raw");
            else pb = new ProcessBuilder("cmd.exe", "/c", "mode", "con", "cooked");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();
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

    public static void writeReportToFile(String content, String fileName) throws IOException {
        File outputFile = new File(System.getProperty("user.dir")+File.separator
            +"resources"+File.separator+"reports"+File.separator+fileName+".txt");
        int count = 1;
        while (outputFile.exists()) {
            String newFileName = fileName+"("+count+").txt";
            outputFile = new File(System.getProperty("user.dir")+File.separator
                +"resources"+File.separator+"reports"+File.separator+newFileName);
            count++;
        }
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(content);
        }
    }

    public static String checkConfig(String fileName) throws IOException {
        File configFile = new File(System.getProperty("user.dir")
        + File.separator + "resources" + File.separator + "configs" + File.separator + fileName);
        if (!configFile.exists()) {
            return "Error: Config file does not exist " + configFile.getAbsolutePath();
        }

        FileReader reader = new FileReader(configFile);
        JSONObject config = new JSONObject(new JSONTokener(reader));
        if (!config.has("floors")) {
            return "Error: Missing 'floors' array in config";
        }

        JSONArray floors = config.getJSONArray("floors");
        List<String> validFloorFields = Arrays.asList("number", "rooms");
        List<String> validRoomFields = Arrays.asList("name", "devices", "residents");
        List<String> validResidentFields = Arrays.asList("type", "name");
        for (int i = 0; i < floors.length(); i++) {
            JSONObject floor = floors.getJSONObject(i);
            for (String key : floor.keySet()) {
                if (!validFloorFields.contains(key)) {
                    return "Error: Invalid field '" + key + "' in floor";
                }
            }
            if (!floor.has("number") || !floor.has("rooms")) {
                return "Error: Floor must have 'number' and 'rooms' fields";
            }

            JSONArray rooms = floor.getJSONArray("rooms");
            for (int j = 0; j < rooms.length(); j++) {
                JSONObject room = rooms.getJSONObject(j);
                for (String key : room.keySet()) {
                    if (!validRoomFields.contains(key)) {
                        return "Invalid field '" + key + "' in room";
                    }
                }
                if (!room.has("name") || !room.has("devices") || !room.has("residents")) {
                    return "Error: Room must have 'name', 'devices' and 'residents' fields";
                }

                JSONArray residents = room.getJSONArray("residents");
                for (int k = 0; k < residents.length(); k++) {
                    JSONObject resident = residents.getJSONObject(k);
                    for (String key : resident.keySet()) {
                        if (!validResidentFields.contains(key)) {
                            return "Error: Invalid field '" + key + "' in resident";
                        }
                    }
                    if (!resident.has("type") || !resident.has("name")) {
                        return "Error: Resident must have 'type' and 'name' fields";
                    }
                }

                JSONArray devices = room.getJSONArray("devices");
                for (int k = 0; k < devices.length(); k++) {
                    JSONObject device = devices.getJSONObject(k);
                    for (String key : device.keySet()) {
                        if (!key.equals("type")) {
                            return "Error: Invalid field '" + key + "' in device";
                        }
                    }
                    if (!device.has("type")) {
                        return "Error: Resident must have 'type' field";
                    }
                }
            }
        }
        return "OK";
    }
}
