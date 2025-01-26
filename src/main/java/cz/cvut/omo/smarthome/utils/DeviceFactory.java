package cz.cvut.omo.smarthome.utils;

import com.fasterxml.jackson.databind.JsonNode;
import cz.cvut.omo.smarthome.house.device.*;
import cz.cvut.omo.smarthome.house.device.vehicle.*;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.utils.Clock;
import java.util.Optional;

public class DeviceFactory {
    public static Device createDevice(JsonNode deviceNode, Room room) {
        String type = deviceNode.get("type").asText();
        Device device = null;
        Consumption consumption = new Consumption();
        Optional<String> manual = Optional.of("manual");
        double breakChance = 0.1;
        int maxSpeed = 200;
        String size = "Big";
        switch (type) {
            case "HeatingSystem":
                device = new HeatingSystem(consumption, manual, breakChance, room, type);
                break;
            case "Car":
                device = new Car(consumption, manual, breakChance, room, type, maxSpeed, size);
                break;
            case "Bicycle":
                device = new Bicycle(consumption, manual, breakChance, room, type, maxSpeed, size);
                break;
            case "Ski":
                device = new Ski(consumption, manual, breakChance, room, type, maxSpeed, size);
                break;
            case "Window":
                device = new Window(consumption, manual, breakChance, room, type);
                break;
            case "PC":
                device = new PC(consumption, manual, breakChance, room, type);
                break;
            case "RobotVacuum":
                device = new RobotVacuum(consumption, manual, breakChance, room, type);
                break;
            case "TV":
                device = new TV(consumption, manual, breakChance, room, type);
                break;
            case "LightSystem":
                device = new LightSystem(consumption, manual, breakChance, room, type);
                break;
            case "Feeder":
                device = new Feeder(consumption, manual, breakChance, room, type);
                break;
            case "Phone":
                device = new Phone(consumption, manual, breakChance, room, type);
                break;
            case "MultiCooker":
                device = new MultiCooker(consumption, manual, breakChance, room, type);
                break;
            case "Sensor":
                device = new Sensor(consumption, manual, breakChance, room, type);
                break;
            case "KaraokeShower":
                device = new KaraokeShower(consumption, manual, breakChance, room, type);
                break;
            case "Fridge":
                device = new Fridge(consumption, manual, breakChance, room, type);
                break;
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
        return device;
    }
}
