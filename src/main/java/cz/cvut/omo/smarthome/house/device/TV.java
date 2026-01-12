package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class TV extends Device {
    private int currentChannel;
    private int volume;
    private List<String> channels;
    private boolean isStreaming;
    private String currentContent;

    public TV(Room room, String type) {
        super(room, type, new Consumption("electro", 25), 0.5);
        setState(new TurnedOff(this));
        this.currentChannel = 1;
        this.volume = 50;
        this.channels = new ArrayList<>();
        this.isStreaming = false;
        this.currentContent = "";
        initializeChannels();
    }

    private void initializeChannels() {
        channels.add("News Channel");
        channels.add("Sports Channel");
        channels.add("Movie Channel");
        channels.add("Kids Channel");
        channels.add("Documentary Channel");
    }

    public void changeChannel(int channel) {
        if (state instanceof TurnedOn && channel > 0 && channel <= channels.size()) {
            this.currentChannel = channel;
            this.currentContent = channels.get(channel - 1);
            consume(0.1);
        }
    }

    public void nextChannel() {
        if (state instanceof TurnedOn) {
            currentChannel = (currentChannel % channels.size()) + 1;
            this.currentContent = channels.get(currentChannel - 1);
            consume(0.1);
        }
    }

    public void previousChannel() {
        if (state instanceof TurnedOn) {
            currentChannel = currentChannel > 1 ? currentChannel - 1 : channels.size();
            this.currentContent = channels.get(currentChannel - 1);
            consume(0.1);
        }
    }

    public void setVolume(int volume) {
        if (state instanceof TurnedOn && volume >= 0 && volume <= 100) {
            this.volume = volume;
        }
    }

    public void volumeUp() {
        if (state instanceof TurnedOn && volume < 100) {
            volume += 5;
        }
    }

    public void volumeDown() {
        if (state instanceof TurnedOn && volume > 0) {
            volume -= 5;
        }
    }

    public boolean startStreaming(String content) {
        if (state instanceof TurnedOn) {
            isStreaming = true;
            currentContent = content;
            consume(0.3);
            return true;
        }
        return false;
    }

    public void stopStreaming() {
        isStreaming = false;
        currentContent = channels.get(currentChannel - 1);
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public int getVolume() {
        return volume;
    }

    public String getCurrentContent() {
        return currentContent;
    }

    public boolean isStreaming() {
        return isStreaming;
    }

    public List<String> getChannels() {
        return new ArrayList<>(channels);
    }
}
