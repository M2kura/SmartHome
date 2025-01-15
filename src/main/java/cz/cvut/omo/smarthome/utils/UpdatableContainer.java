package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.utils.Clock;

import java.util.ArrayList;

public abstract class UpdatableContainer implements ChangableObj {
    protected Clock clock;
    protected Report report;
    private ArrayList<ChangableObj> childObjs = new ArrayList<>();

    public void addChildObj(ChangableObj obj) {
        childObjs.add(obj);
    }

    public void removeChildObj(ChangableObj obj) {
        childObjs.remove(obj);
    }
}
