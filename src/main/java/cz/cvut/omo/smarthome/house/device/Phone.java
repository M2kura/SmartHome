package cz.cvut.omo.smarthome.house.device;

import cz.cvut.omo.smarthome.house.Room;
import cz.cvut.omo.smarthome.house.device.Consumption;
import cz.cvut.omo.smarthome.house.device.Device;
import cz.cvut.omo.smarthome.house.device.state.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class Phone extends Device{
    private List<String> contacts;
    private List<String> messages;
    private boolean inCall;

    public Phone(Room room, String type) {
        super(room, type, new Consumption("electro", 2), 0.1);
        setState(new TurnedOff(this));
        this.contacts = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.inCall = false;
    }

    public boolean makeCall(String contact) {
        if (state instanceof TurnedOn && !inCall) {
            inCall = true;
            consume(0.5);
            return true;
        }
        return false;
    }

    public void endCall() {
        inCall = false;
    }

    public boolean sendMessage(String recipient, String message) {
        if (state instanceof TurnedOn) {
            messages.add("To: " + recipient + " - " + message);
            consume(0.1);
            return true;
        }
        return false;
    }

    public void addContact(String contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }
    }

    public List<String> getContacts() {
        return new ArrayList<>(contacts);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public boolean isInCall() {
        return inCall;
    }

    public void emergencyCall(String number) {
        makeCall(number);
    }
}
