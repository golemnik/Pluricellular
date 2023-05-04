package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.queens.BroodQueen;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.BroodMotherCell;
import com.golem.core.schemas.abstracts.AbstractQueenCell;
import com.golem.core.schemas.deepSchemas.SystemCommand;

import java.util.List;

public class ReloadGenomeCommandCell implements Cell, SystemCommand {
    private List<AbstractQueenCell> queens;
    private BroodMotherCell broodMother;
    public ReloadGenomeCommandCell() {
    }
    public void setQueen (List<AbstractQueenCell> queens) {
        this.queens = queens;
    }
    public void setBroodMother (BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
        queens.forEach(AbstractQueenCell::updateLayer);
        broodMother.clearAllFactoryList();
        queens.forEach(queen -> queen.fillBroodMother(queen.getLayer()));
    }
}
