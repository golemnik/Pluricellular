package com.golem.core.schemas.basicInterfaces.connections;

import com.golem.core.schemas.basicInterfaces.QueenCell;

public interface QueenConnection {
    void addQueenConnection (QueenCell queenCell);
    QueenCell getQueenConnection ();
}
