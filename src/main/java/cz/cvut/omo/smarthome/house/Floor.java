package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.databind.JsonNode;

import cz.cvut.omo.smarthome.house.Room;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Room> rooms;
    private int number;

    public Floor(JsonNode floorNode) {
        this.number = floorNode.get("number").asInt();
        this.rooms = new ArrayList<>();
        for (JsonNode roomNode : floorNode.get("rooms")) {
            this.rooms.add(new Room(roomNode, this));
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
