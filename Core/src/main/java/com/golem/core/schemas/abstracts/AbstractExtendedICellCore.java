package com.golem.core.schemas.abstracts;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.BroodMotherCell;
import com.golem.core.schemas.ExtendedICellFullCore;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExtendedICellCore implements ExtendedICellFullCore {

    private BroodMotherCell broodMother;
    private CoreCell coreCell;
    private List <AbstractQueenCell> queens = new ArrayList<>();

    protected AbstractExtendedICellCore() {
    }

    @Override
    public void addBroodMother(BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public BroodMotherCell getBroodMother() {
        return broodMother;
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
    public void addQueenConnection(List<AbstractQueenCell> queens) {
        this.queens = queens;
    }

    @Override
    public void addSQueenConnection(AbstractQueenCell abstractQueenCell) {
        this.queens.add(abstractQueenCell);
    }

    @Override
    public List<AbstractQueenCell> getQueenConnections() {
        return queens;
    }

    @Override
    public void setAll(CoreCell coreCell, BroodMotherCell broodMother, List<AbstractQueenCell> queens) {
        addCore(coreCell);
        addBroodMother(broodMother);
        addQueenConnection(queens);
    }
}
