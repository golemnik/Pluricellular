package com.golem.core.schemas.basicInterfaces;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.basicAbstractions.AbstractBroodMother;
import com.golem.core.schemas.basicInterfaces.connections.BroodMotherConnection;
import com.golem.core.schemas.basicInterfaces.connections.CoreCellConnection;
import com.golem.core.schemas.basicInterfaces.connections.QueenConnection;

public interface InnerCellFullCore extends CoreCellConnection, BroodMotherConnection, QueenConnection {
    void setAll (CoreCell coreCell, AbstractBroodMother broodMother, QueenCell queenCell);
}
