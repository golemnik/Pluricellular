package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;

public class ReloadGenomeCommandCell implements Cell {
    private BroodQueen queen;
    private CellBroodMother broodMother;
    public ReloadGenomeCommandCell() {
    }
    public void setQueen (BroodQueen queen) {
        this.queen = queen;
    }
    public void setBroodMother (CellBroodMother broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
        queen.activate();
        broodMother.reloadFactoryList(BroodQueen.loadAbsFactories(queen.getLayer()));
    }
}
