package cz.cvut.omo.smarthome.utils;

import com.fasterxml.jackson.databind.JsonNode;
import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.*;
import cz.cvut.omo.smarthome.house.resident.animal.*;
import cz.cvut.omo.smarthome.house.Room;

public class ResidentFactory {

    public static Resident createResident(JsonNode residentNode, Room room) {
        String type = residentNode.get("type").asText();
        String name = residentNode.get("name").asText();
        Resident resident = null;
        switch (type) {
            case "Dad":
                resident = new Dad(room, name);
                break;
            case "Mom":
                resident = new Mom(room, name);
                break;
            case "Teen":
                resident = new Teen(room, name);
                break;
            case "Child":
                resident = new Child(room, name);
                break;
            case "Cat":
                resident = new Cat(room, name);
                break;
            case "Dog":
                resident = new Dog(room, name);
                break;
            default:
                throw new IllegalArgumentException("Unknown resident type: " + type);
        }
        return resident;
    }
}
