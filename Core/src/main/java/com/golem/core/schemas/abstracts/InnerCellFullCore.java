package com.golem.core.schemas.abstracts;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.connections.BroodMotherConnection;
import com.golem.core.schemas.connections.BroodQueenConnection;
import com.golem.core.schemas.connections.CoreCellsConnection;

public abstract class InnerCellFullCore implements CoreCellsConnection, BroodMotherConnection, BroodQueenConnection {
    private CellBroodMother broodMother;
    private CoreCell coreCell;
    private BroodQueen queen;
    @Override
    public void addBroodMother(CellBroodMother broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public CellBroodMother getBroodMother() {
        return broodMother;
    }

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

    public void setAll (CoreCell coreCell, CellBroodMother broodMother, BroodQueen broodQueen) {
        addCore(coreCell);
        addBroodMother(broodMother);
        addBroodQueen(broodQueen);
    }
}
