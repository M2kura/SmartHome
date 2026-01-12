package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.device.Device;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Iterator for Device collections.
 * Implements the Iterator pattern to provide sequential access to Device objects.
 * Supports optional filtering via predicates.
 */
public class DeviceIterator implements Iterator<Device> {
    private final List<Device> devices;
    private int currentPosition;
    private final Predicate<Device> filter;

    /**
     * Creates an iterator for all devices in the collection.
     * @param devices the list of devices to iterate over
     */
    public DeviceIterator(List<Device> devices) {
        this(devices, null);
    }

    /**
     * Creates an iterator with a filter predicate.
     * @param devices the list of devices to iterate over
     * @param filter predicate to filter devices (null for no filtering)
     */
    public DeviceIterator(List<Device> devices, Predicate<Device> filter) {
        this.devices = devices != null ? devices : List.of();
        this.currentPosition = 0;
        this.filter = filter;
        // Move to first matching element if filter is present
        if (this.filter != null) {
            advanceToNextMatch();
        }
    }

    /**
     * Advances the position to the next element that matches the filter.
     */
    private void advanceToNextMatch() {
        while (currentPosition < devices.size() && !filter.test(devices.get(currentPosition))) {
            currentPosition++;
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < devices.size();
    }

    @Override
    public Device next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more devices in the collection");
        }
        Device device = devices.get(currentPosition);
        currentPosition++;
        // If filter is present, advance to next match
        if (filter != null) {
            advanceToNextMatch();
        }
        return device;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported by DeviceIterator");
    }

    /**
     * Creates an iterator that filters devices by type.
     * @param devices the list of devices to iterate over
     * @param deviceType the type of device to filter by
     * @return iterator that only returns devices of the specified type
     */
    public static DeviceIterator filterByType(List<Device> devices, String deviceType) {
        return new DeviceIterator(devices, device -> device.getType().equals(deviceType));
    }

    /**
     * Creates an iterator that filters devices by class type.
     * @param devices the list of devices to iterate over
     * @param deviceClass the class of device to filter by
     * @param <T> the type of device
     * @return iterator that only returns devices of the specified class
     */
    public static <T extends Device> DeviceIterator filterByClass(List<Device> devices, Class<T> deviceClass) {
        return new DeviceIterator(devices, deviceClass::isInstance);
    }
}
