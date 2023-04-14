package com.golem.core.schemas;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.connections.BroodMotherConnection;
import com.golem.core.schemas.connections.BroodQueenConnection;
import com.golem.core.schemas.connections.CoreCellsConnection;

public interface InnerCellFullCore extends CoreCellsConnection, BroodMotherConnection, BroodQueenConnection {
    void setAll (CoreCell coreCell, CellBroodMother broodMother, BroodQueen broodQueen);
}
