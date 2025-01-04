package cz.cvut.omo.smarthome.house;

import cz.cvut.omo.smarthome.house.utils.EventManager;

public interface ChangableObj {
    EventManager eventManager = new EventManager();
    Clock clock = new Clock();
    Report report = new Report();

    public default void getUpdate() {}
    public default void getActor() {}
    public default void getConfig() {}
}
