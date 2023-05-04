package com.golem.core.schemas.connections;

import com.golem.core.schemas.QueenCell;

public interface QueenConnection {
    void addQueenConnection (QueenCell queenCell);
    QueenCell getQueenConnection ();
}
