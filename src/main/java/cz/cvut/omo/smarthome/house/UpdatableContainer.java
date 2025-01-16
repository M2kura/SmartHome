package cz.cvut.omo.smarthome.house;

import java.util.ArrayList;
import java.util.List;

public abstract class UpdatableContainer {
    List<ChangableObj> childObj = new ArrayList<>();

    protected void addChildObj() {}

    protected void removeChildObj() {}
}
