package cz.cvut.omo.smarthome.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONException;
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
        JSONObject config;
        try {
            config = new JSONObject(new JSONTokener(reader));
        } catch (JSONException e) {
            return "Error: Invalid JSON format - "+e.getMessage()+"\n";
        }
        for (String key : config.keySet()) {
            if (!key.equals("floors"))
                output.append("Error: Invalid field '" + key + "' in floor\n");
        }
        if (!config.has("floors")) {
            return "Error: Missing 'floors' array in config\n";
        }

        JSONArray floors;
        try {
            floors = config.getJSONArray("floors");
        } catch (JSONException e) {
            return "Error: 'floors' must be an array\n";
        }
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
        List<String> requiredDevices = Arrays.asList("PC", "Ski");
        List<String> uniqueDeviceTypes = new ArrayList<>();
        List<String> uniqueDevices = new ArrayList<>();
        int personCount = 0;
        int petCount = 0;
        int bicycleCount = 0;
        for (int i = 0; i < floors.length(); i++) {
            JSONObject floor = floors.getJSONObject(i);
            for (String key : floor.keySet()) {
                if (!validFloorFields.contains(key))
                    output.append("Error: Invalid field '" + key + "' in floor\n");
            }
            if (!floor.has("number") || !floor.has("rooms"))
                return output.toString()+"Error: Floor must have 'number' and 'rooms' fields\n";
            try {
                floor.getInt("number");
            } catch (JSONException e) {
                output.append("Error: Floor number must be an integer\n");
            }
            JSONArray rooms;
            try {
                rooms = floor.getJSONArray("rooms");
            } catch (JSONException e) {
                return output.toString()+"Error: 'rooms' must be an array\n";
            }
            for (int j = 0; j < rooms.length(); j++) {
                JSONObject room = rooms.getJSONObject(j);
                if (!room.has("name") || !room.has("devices") || !room.has("residents")) {
                    return output.toString()
                    +"Error: Room must have 'name', 'devices' and 'residents' fields\n";
                }
                String roomName = "Room";
                try {
                    if (room.getString("name").trim().isEmpty())
                        output.append("Error: Room name cannot be empty\n");
                    else if (!validRoomNames.contains(room.getString("name")))
                        output.append("Error: Invalid room name - "+room.getString("name")+"\n");
                    else
                        roomName = room.getString("name");
                } catch (JSONException e) {
                    output.append("Error: Room name must be a string\n");
                }
                for (String key : room.keySet()) {
                    if (!validRoomFields.contains(key))
                        output.append("Invalid field '"+key+"' in "+roomName+"\n");
                }
                JSONArray devices;
                try {
                    devices = room.getJSONArray("devices");
                } catch (JSONException e) {
                    return output.toString()+"Error: 'devices' must be an array\n";
                }
                for (int k = 0; k < devices.length(); k++) {
                    JSONObject device;
                    try {
                        device = devices.getJSONObject(k);
                    } catch (JSONException e) {
                        return output.toString()+"Error: 'devices' array must contain {} objects\n";
                    }
                    if (!device.has("type"))
                        return output.toString()+"Error: Resident must have 'type' field\n";
                    String deviceType = "";
                    try {
                        deviceType = device.getString("type");
                        if (!validDeviceTypes.contains(deviceType))
                            output.append("Error: Invalid device type '"+deviceType+"'\n");
                        else if (!uniqueDeviceTypes.contains(deviceType))
                            uniqueDeviceTypes.add(deviceType);
                    } catch (JSONException e) {
                        output.append("Error: Device type must be a string\n");
                    }
                    for (String key : device.keySet()) {
                        if (!key.equals("type"))
                            output.append("Error: Invalid field '"+key+"' in device\n");
                    }
                    if (deviceType.equals("Bicycle"))
                        bicycleCount++;
                    uniqueDevices.add(deviceType);
                }
                JSONArray residents;
                try {
                    residents = room.getJSONArray("residents");
                } catch (JSONException e) {
                    return output.toString()+"Error: 'residents' must be an array\n";
                }
                for (int k = 0; k < residents.length(); k++) {
                    JSONObject resident;
                    try {
                        resident = residents.getJSONObject(k);
                    } catch (JSONException e) {
                        return output.toString()+"Error: 'residents' array must contain {} objects\n";
                    }
                    if (!resident.has("type") || !resident.has("name"))
                        return output.toString()+"Error: Resident must have 'type' and 'name' fields\n";
                    String residentName = "";
                    try {
                        residentName = resident.getString("name");
                        if (residentName.trim().isEmpty())
                            output.append("Error: Resident name cannot be empty\n");
                    } catch (JSONException e) {
                        output.append("Error: Resident name must be a string\n");
                    }
                    String residentType = "";
                    try {
                        residentType = resident.getString("type");
                        if (!validPersonTypes.contains(residentType) && !validAnimalTypes.contains(residentType))
                            output.append("Error: Invalid type '"+residentType+"' in resident "+residentName+"\n");
                    } catch (JSONException e) {
                        output.append("Error: Resident type must be a string\n");
                    }
                    for (String key : resident.keySet()) {
                        if (!validResidentFields.contains(key))
                            output.append("Error: Invalid field '"+key+"' in resident "+residentName+"\n");
                    }
                    if (validPersonTypes.contains(residentType)) {
                        if (!roomName.equals("Bedroom"))
                            output.append("Error: Person should be in Bedroom\n");
                        personCount++;
                    } else if (validAnimalTypes.contains(residentType)) {
                        if (!roomName.equals("Living Room"))
                            output.append("Error: Pet should be in Living Room\n");
                        petCount++;
                    }
                }
            }
        }
        if (personCount < 6)
            output.append("Error: simulation must contain at least 6 people\n");
        if (petCount < 3)
            output.append("Error: simulation must contain at least 3 pets\n");
        if (uniqueDeviceTypes.size() < 8)
            output.append("Error: simulation must contain at least 8 unique device types\n");
        if (uniqueDevices.size() < 20)
            output.append("Error: simulation must contain at least 20 devices\n");
        for (String device : requiredDevices) {
            if (!uniqueDevices.contains(device))
                output.append("Error: simulation must contain at least 1 "+device+"\n");
        }
        if (bicycleCount < 2)
            output.append("Error: simulation must contain at least 2 bicycles\n");
        return output.length() == 0 ? "OK" : output.toString();
    }
}
