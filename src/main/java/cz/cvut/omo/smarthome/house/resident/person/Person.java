package cz.cvut.omo.smarthome.house.resident.person;

import cz.cvut.omo.smarthome.house.device.Food;
import cz.cvut.omo.smarthome.house.room.Room;

import java.util.Timer;
import java.util.TimerTask;

public class Person {
    private String name;
    private String surname;
    private boolean isAtHome;
    private double age;
    private Room currentRoom;
    private boolean isSleeping;

    public Person() {
        this.name = name;
        this.currentRoom = currentRoom;
        this.isSleeping = false;
    }

    //------Methods------

    public String getName() {
        return name;
    }


    public void moveToRoom(Room newRoom) {
        if (currentRoom != null) {
            currentRoom.removePersonFromRoom(this);
        }

        newRoom.addPersonToRoom(this);
        currentRoom = newRoom;
    }

    public void sleep(Room room, int sleepDuration) {
        if (currentRoom != null) {
            moveToRoom(room);
            System.out.println(name + "is going to sleep.");
            isSleeping = true;

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isSleeping = false;
                    System.out.println(name + "has woken up!");
                    timer.cancel();
                }
            }, sleepDuration * 1000l);
        }
    }

    public void eat(Food food) {
        //TO DO
    }
}
