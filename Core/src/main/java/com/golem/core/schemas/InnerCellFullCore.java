package com.golem.core.schemas;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.abstracts.AbstractBroodMother;
import com.golem.core.schemas.connections.BroodMotherConnection;
import com.golem.core.schemas.connections.CoreCellConnection;
import com.golem.core.schemas.connections.QueenConnection;

public interface InnerCellFullCore extends CoreCellConnection, BroodMotherConnection, QueenConnection {
    void setAll (CoreCell coreCell, AbstractBroodMother broodMother, QueenCell queenCell);
}
