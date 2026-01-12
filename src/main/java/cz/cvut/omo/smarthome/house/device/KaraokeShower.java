package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class KaraokeShower extends Device{
    private String playlist;
    private List<String> songs;
    private int currentSongIndex;
    private double waterTemperature;
    private boolean isPlaying;

    public KaraokeShower(Room room, String type) {
        super(room, type, new Consumption("both", 12.5, 240), 0.1);
        setState(new TurnedOff(this));
        this.playlist = "Default Playlist";
        this.songs = new ArrayList<>();
        this.currentSongIndex = 0;
        this.waterTemperature = 38.0;
        this.isPlaying = false;
        initializeSongs();
    }

    private void initializeSongs() {
        songs.add("Pop Hits");
        songs.add("Rock Classics");
        songs.add("Jazz Standards");
        songs.add("Classical Music");
    }

    public void changePlaylist(String newPlaylist) {
        if (state instanceof TurnedOn) {
            this.playlist = newPlaylist;
            consume(0.05);
        }
    }

    public void nextSong() {
        if (state instanceof TurnedOn && !songs.isEmpty()) {
            currentSongIndex = (currentSongIndex + 1) % songs.size();
            consume(0.05);
        }
    }

    public void previousSong() {
        if (state instanceof TurnedOn && !songs.isEmpty()) {
            currentSongIndex = currentSongIndex > 0 ? currentSongIndex - 1 : songs.size() - 1;
            consume(0.05);
        }
    }

    public void play() {
        if (state instanceof TurnedOn) {
            isPlaying = true;
            consume(0.5);
        }
    }

    public void pause() {
        isPlaying = false;
    }

    public void setWaterTemperature(double temp) {
        if (state instanceof TurnedOn && temp >= 20 && temp <= 45) {
            this.waterTemperature = temp;
        }
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public String getCurrentSong() {
        if (!songs.isEmpty()) {
            return songs.get(currentSongIndex);
        }
        return "No song";
    }

    public String getPlaylist() {
        return playlist;
    }

    public double getWaterTemperature() {
        return waterTemperature;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public List<String> getSongs() {
        return new ArrayList<>(songs);
    }
}
