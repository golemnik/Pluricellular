package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.deepSchemas.SystemCommand;

public class ReloadGenomeCommandCell implements Cell, SystemCommand {
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
        broodMother.addFactoryList(BroodQueen.loadSystemFactories(queen.getLayer()));
        broodMother.addFactoryList(BroodQueen.loadOuterFactories(queen.getLayer()));
    }
}
