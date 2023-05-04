package com.golem.core.schemas.connections;

import com.golem.core.schemas.abstracts.AbstractQueenCell;

import java.util.List;

public interface ExtendedQueenConnection {
    void addQueenConnection (List<AbstractQueenCell> queenCell);
    void addSQueenConnection (AbstractQueenCell queenCell);
    List<AbstractQueenCell> getQueenConnections ();

}
