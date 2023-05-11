package com.golem.core.schemas.basicInterfaces.connections;

import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;

import java.util.List;

public interface ExtendedQueenConnection {
    void addQueenConnection (List<AbstractQueenCell> queenCell);
    void addSQueenConnection (AbstractQueenCell queenCell);
    List<AbstractQueenCell> getQueenConnections ();

}
