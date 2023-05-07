package com.golem.core.schemas.abstracts;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.QueenCell;
import com.golem.core.schemas.InnerCellFullCore;

public abstract class AbstractICellCore implements InnerCellFullCore {
    private AbstractBroodMother broodMother;
    private CoreCell coreCell;
    private QueenCell queen;

    protected AbstractICellCore() {

    }

    @Override
    public void addQueenConnection(QueenCell queenCell) {
        this.queen = queenCell;
    }

    @Override
    public void addBroodMother(AbstractBroodMother broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public void addCore(CoreCell coreCell) {
        this.coreCell = coreCell;
    }

    @Override
    public CoreCell getCoreCell() {
        return coreCell;
    }

    @Override
    public AbstractBroodMother getBroodMother() {
        return broodMother;
    }

    @Override
    public QueenCell getQueenConnection() {
        return queen;
    }

    @Override
    public void setAll(CoreCell coreCell, AbstractBroodMother broodMother, QueenCell queenCell) {
        addCore(coreCell);
        addBroodMother(broodMother);
        addQueenConnection(queenCell);
    }
}
