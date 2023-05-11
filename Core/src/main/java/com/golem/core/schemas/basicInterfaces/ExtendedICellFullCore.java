package com.golem.core.schemas.basicInterfaces;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.basicAbstractions.AbstractBroodMother;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.core.schemas.basicInterfaces.connections.BroodMotherConnection;
import com.golem.core.schemas.basicInterfaces.connections.CoreCellConnection;
import com.golem.core.schemas.basicInterfaces.connections.ExtendedQueenConnection;

import java.util.List;

public interface ExtendedICellFullCore extends CoreCellConnection, BroodMotherConnection, ExtendedQueenConnection {

    void setAll(CoreCell coreCell, AbstractBroodMother broodMother, List<AbstractQueenCell> queens);
}
