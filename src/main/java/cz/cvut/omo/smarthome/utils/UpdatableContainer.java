package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.utils.ChangableObj;
import cz.cvut.omo.smarthome.utils.Report;
import cz.cvut.omo.smarthome.utils.Clock;

import java.util.List;

public abstract class UpdatableContainer implements ChangableObj {
    protected EventManager em;
    protected Clock clock;
    protected Report report;
    protected List<ChangableObj> childObjs;

    public void addChildObj(ChangableObj obj) {
        childObjs.add(obj);
    }

    public void removeChildObj(ChangableObj obj) {
        childObjs.remove(obj);
    }

    @Override
    public void getAction() {
        for (ChangableObj child : childObjs) {
            child.getAction();
        }
    }

    @Override
    public String getConfig(){
        return "";
    }
}
