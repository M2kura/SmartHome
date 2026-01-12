package cz.cvut.omo.smarthome.house.device.vehicle;

/**
 * Marker interface to identify devices that are sports equipment.
 * Sports equipment can be used by residents for recreational activities.
 */
public interface SportsEquipment {
    /**
     * Get the sport type/name of this equipment
     * @return the sport name (e.g., "cycling", "skiing")
     */
    String getSportType();
}
