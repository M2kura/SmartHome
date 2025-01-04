package cz.cvut.omo.smarthome.house.utils;

import cz.cvut.omo.smarthome.house.creature.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.creature.Person;
import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.room.Room;

import java.util.List;
import java.util.Optional;

public class Event {
    private List<Resident> residents;
    private List<Device> devices;
    private Optional<Room> room;
    private Optional<List<Food>> food;
    private String category;
    private String description;
    private String status;


    public List<Resident> getResidents() {
        return residents;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Optional<Room> getRoom() {
        return room;
    }

    public Optional<List<Food>> getFood() {
        return food;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus() {}
}
