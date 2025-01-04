package cz.cvut.omo.smarthome.house.utils;

public interface EventIterator {
    public default void getNext() {}

    public default void hasNext() {}
}
