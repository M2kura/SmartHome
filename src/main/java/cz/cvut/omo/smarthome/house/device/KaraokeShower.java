package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;

public class KaraokeShower extends Device{
    private String playlist;

    public KaraokeShower(Room room, String type) {
        super(room, type, new Consumption("Karaoke Shower"), 0.1);
        setState(new TurnedOff(this));
    }

    public void changePlaylist() {}
}
