package com.golem.core.schemas;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.abstracts.AbstractBroodMother;
import com.golem.core.schemas.abstracts.AbstractQueenCell;
import com.golem.core.schemas.connections.BroodMotherConnection;
import com.golem.core.schemas.connections.CoreCellConnection;
import com.golem.core.schemas.connections.ExtendedQueenConnection;

import java.util.List;

public interface ExtendedICellFullCore extends CoreCellConnection, BroodMotherConnection, ExtendedQueenConnection {

    void setAll(CoreCell coreCell, AbstractBroodMother broodMother, List<AbstractQueenCell> queens);
}
