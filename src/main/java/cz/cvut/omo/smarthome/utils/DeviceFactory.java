package cz.cvut.omo.smarthome.utils;

import com.fasterxml.jackson.databind.JsonNode;
import cz.cvut.omo.smarthome.house.device.*;
import cz.cvut.omo.smarthome.house.device.state.*;
import cz.cvut.omo.smarthome.house.device.vehicle.*;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.Clock;
import java.util.Optional;

public class DeviceFactory {
    public static Device createDevice(JsonNode deviceNode, Room room) {
        String type = deviceNode.get("type").asText();
        Device device = null;
        switch (type) {
            case "Heating System":
                device = new HeatingSystem(room, type);
                break;
            case "Car":
                device = new Car(room, type);
                break;
            case "Bicycle":
                device = new Bicycle(room, type);
                break;
            case "Ski":
                device = new Ski(room, type);
                break;
            case "Skateboard":
                device = new Skateboard(room, type);
                break;
            case "RollerSkates":
                device = new RollerSkates(room, type);
                break;
            case "Window":
                device = new Window(room, type);
                break;
            case "PC":
                device = new PC(room, type);
                break;
            case "Robot Vacuum":
                device = new RobotVacuum(room, type);
                break;
            case "TV":
                device = new TV(room, type);
                break;
            case "Light System":
                device = new LightSystem(room, type);
                break;
            case "Feeder":
                device = new Feeder(room, type);
                break;
            case "Phone":
                device = new Phone(room, type);
                break;
            case "MultiCooker":
                device = new MultiCooker(room, type);
                break;
            case "Sensor":
                device = new Sensor(room, type);
                break;
            case "Karaoke Shower":
                device = new KaraokeShower(room, type);
                break;
            case "Fridge":
                device = new Fridge(room, type);
                break;
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
        return device;
    }
}
