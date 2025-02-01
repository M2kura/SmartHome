package cz.cvut.omo.smarthome.house.resident.state;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.utils.EventManager;

public abstract class ResidentState {
    protected EventManager em;
    protected Resident resident;

    public ResidentState(Resident resident) {
        this.resident = resident;
        this.em = EventManager.getEM();
    }

    public abstract void getAction();
}
