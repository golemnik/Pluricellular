package com.golem.core.schemas.connections;

import com.golem.core.schemas.BroodMotherCell;

public interface BroodMotherConnection {
    void addBroodMother (BroodMotherCell broodMother);
    BroodMotherCell getBroodMother ();
}
