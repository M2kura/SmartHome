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
import java.lang.StringBuilder;

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
        StringBuilder output = new StringBuilder();
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
        List<String> validRoomNames = Arrays.asList(
            "Outside", "Kitchen", "Living Room", "Garage", "Bathroom", "Bedroom"
        );
        List<String> validResidentFields = Arrays.asList("type", "name");
        List<String> validPersonTypes = Arrays.asList("Dad", "Mom", "Teen", "Child");
        List<String> validAnimalTypes = Arrays.asList("Cat", "Dog");
        List<String> validDeviceTypes = Arrays.asList(
            "Heating System", "Car", "Bicycle", "Ski", "Window", "PC",
            "Robot Vacuum", "TV", "Light System", "Feeder", "Phone",
            "MultiCooker", "Sensor", "Karaoke Shower", "Fridge"
        );
        List<String> uniqueDeviceTypes = new ArrayList<>();
        int deviceCount = 0;
        int personCount = 0;
        int petCount = 0;
        int skiCount = 0;
        int bicycleCount = 0;
        for (int i = 0; i < floors.length(); i++) {
            JSONObject floor = floors.getJSONObject(i);
            for (String key : floor.keySet()) {
                if (!validFloorFields.contains(key))
                    return output.toString()+"Error: Invalid field '" + key + "' in floor\n";
            }
            if (!floor.has("number") || !floor.has("rooms"))
                return output.toString()+"Error: Floor must have 'number' and 'rooms' fields\n";

            JSONArray rooms = floor.getJSONArray("rooms");
            for (int j = 0; j < rooms.length(); j++) {
                JSONObject room = rooms.getJSONObject(j);
                if (!room.has("name") || !room.has("devices") || !room.has("residents")) {
                    return output.toString()
                    +"Error: Room must have 'name', 'devices' and 'residents' fields\n";
                }
                String roomName = "Room";
                if (room.getString("name").trim().isEmpty())
                    output.append("Error: Room name cannot be empty\n");
                else if (!validRoomNames.contains(room.getString("name")))
                    output.append("Error: Invalid room name - "+room.getString("name")+"\n");
                else
                    roomName = room.getString("name");
                for (String key : room.keySet()) {
                    if (!validRoomFields.contains(key))
                        output.append("Invalid field '"+key+"' in "+roomName+"\n");
                }
                JSONArray residents = room.getJSONArray("residents");
                for (int k = 0; k < residents.length(); k++) {
                    JSONObject resident = residents.getJSONObject(k);
                    if (!resident.has("type") || !resident.has("name"))
                        return output.toString()+"Error: Resident must have 'type' and 'name' fields\n";
                    String residentName = resident.getString("name");
                    String residentType = resident.getString("type");
                    if (residentName.trim().isEmpty())
                        output.append("Error: Resident name cannot be empty\n");
                    if (!validPersonTypes.contains(residentType) && !validAnimalTypes.contains(residentType))
                        output.append("Error: Invalid type '" + residentType + "' in resident "+residentName+"\n");
                    for (String key : resident.keySet()) {
                        if (!validResidentFields.contains(key)) {
                            output.append("Error: Invalid field '" + key + "' in resident "+residentName+"\n");
                        }
                    }
                    if (validPersonTypes.contains(residentType))
                        personCount++;
                    else if (validAnimalTypes.contains(residentType))
                        petCount++;
                }

                JSONArray devices = room.getJSONArray("devices");
                for (int k = 0; k < devices.length(); k++) {
                    JSONObject device = devices.getJSONObject(k);
                    if (!device.has("type"))
                        return output.toString()+"Error: Resident must have 'type' field\n";
                    String deviceType = device.getString("type");
                    if (!validDeviceTypes.contains(deviceType))
                        output.append("Error: Invalid device type '"+deviceType+"'\n");
                    else if (!uniqueDeviceTypes.contains(deviceType))
                        uniqueDeviceTypes.add(deviceType);
                    for (String key : device.keySet()) {
                        if (!key.equals("type"))
                            output.append("Error: Invalid field '"+key+"' in device\n");
                    }
                    if (deviceType.equals("Ski"))
                        skiCount++;
                    else if (deviceType.equals("Bicycle"))
                        bicycleCount++;
                    deviceCount++;
                }
            }
        }
        if (personCount < 6)
            output.append("Error: simulation must contain at least 6 people\n");
        if (petCount < 3)
            output.append("Error: simulation must contain at least 3 pets\n");
        if (uniqueDeviceTypes.size() < 8)
            output.append("Error: simulation must contain at least 8 unique device types\n");
        if (deviceCount < 20)
            output.append("Error: simulation must contain at least 20 devices\n");
        if (skiCount < 1)
            output.append("Error: simulation must contain at least 1 ski\n");
        if (bicycleCount < 2)
            output.append("Error: simulation must contain at least 2 bicycles\n");
        return output.length() == 0 ? "OK" : output.toString();
    }
}
