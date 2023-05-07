package com.golem.core.schemas.connections;

import com.golem.core.schemas.abstracts.AbstractBroodMother;

public interface BroodMotherConnection {
    void addBroodMother (AbstractBroodMother broodMother);
    AbstractBroodMother getBroodMother ();
}
