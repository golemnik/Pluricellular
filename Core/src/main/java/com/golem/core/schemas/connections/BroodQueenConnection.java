package com.golem.core.schemas.connections;

import com.golem.core.broodQueen.BroodQueen;

public interface BroodQueenConnection {
    void addBroodQueen (BroodQueen broodQueen);
    BroodQueen getBroodQueen ();
}
