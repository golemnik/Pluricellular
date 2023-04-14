package com.golem.core.schemas.connections;

import com.golem.core.schemas.CellBroodMother;

public interface BroodMotherConnection {
    void addBroodMother (CellBroodMother broodMother);
    CellBroodMother getBroodMother ();
}
