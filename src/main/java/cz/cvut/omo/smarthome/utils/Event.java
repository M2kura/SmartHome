package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.utils.ChangableObj;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Event {
    private List<ChangableObj> asignedTo;
    private Optional<ChangableObj> involves;
    private Optional<Room> room;
    private String task;
    private String type;
    private String status;
    private int time;
    private int created;
    private int solved;
    private String solvedBy = "";

    public Event(int created) {
        this.status = "Pending";
        this.asignedTo = new ArrayList<>();
        this.involves = Optional.empty();
        this.created = created;
    }

    public void setRoom(Room room) {
        this.room = Optional.of(room);
    }

    public void setFor(ChangableObj obj) {
        this.asignedTo.add(obj);
    }

    public void setInvolved(ChangableObj obj) {
        this.involves = Optional.of(obj);
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Optional<Room> getRoom() {
        return room;
    }

    public Optional<ChangableObj> getInvolved() {
        return involves;
    }

    public String getTask() {
        return task;
    }

    public String getType() {
        return type;
    }

    public int getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public int getCreated() {
        return created;
    }

    public int getSolved() {
        return solved;
    }

    public String getBy() {
        return solvedBy;
    }

    public void updateStatus(String name) {
        if (status.equals("Pending")) {
            status = "Processing";
            solvedBy = name;
        } else if (status.equals("Processing")) {
            status = "Completed";
            Clock clock = Clock.getClock();
            solved = clock.getTicks() - created;
        }
    }

    public boolean isFor(ChangableObj obj) {
        return asignedTo.contains(obj);
    }
}
