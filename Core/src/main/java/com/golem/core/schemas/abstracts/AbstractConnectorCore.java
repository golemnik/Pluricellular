package com.golem.core.schemas.abstracts;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.connections.BroodQueenConnection;
import com.golem.core.schemas.connections.CoreCellsConnection;

public abstract class AbstractConnectorCore implements BroodQueenConnection, CoreCellsConnection {
    private CoreCell coreCell;
    private BroodQueen queen;
    @Override
    public void addBroodQueen(BroodQueen broodQueen) {
        this.queen = broodQueen;
    }

    @Override
    public BroodQueen getBroodQueen() {
        return queen;
    }

    @Override
    public void addCore(CoreCell coreCell) {
        this.coreCell = coreCell;
    }

    @Override
    public CoreCell getCoreCell() {
        return coreCell;
    }

    public void setAll (CoreCell coreCell, BroodQueen broodQueen) {
        addCore(coreCell);
        addBroodQueen(broodQueen);
    }

}
