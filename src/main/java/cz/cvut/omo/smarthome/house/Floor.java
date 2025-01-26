package cz.cvut.omo.smarthome.house;

import com.fasterxml.jackson.databind.JsonNode;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.UpdatableContainer;

import java.util.ArrayList;
import java.util.List;

public class Floor extends UpdatableContainer {
    private int number;

    public Floor(JsonNode floorNode) {
        this.number = floorNode.get("number").asInt();
        this.childObjs = new ArrayList<>();
        for (JsonNode roomNode : floorNode.get("rooms")) {
            this.childObjs.add(new Room(roomNode, this));
        }
    }
}
