package com.golem.core.schemas.basicInterfaces.connections;

import com.golem.core.schemas.basicAbstractions.AbstractBroodMother;

public interface BroodMotherConnection {
    void addBroodMother (AbstractBroodMother broodMother);
    AbstractBroodMother getBroodMother ();
}
