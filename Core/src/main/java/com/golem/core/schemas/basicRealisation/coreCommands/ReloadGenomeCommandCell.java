package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;

import java.util.List;

public class ReloadGenomeCommandCell extends AbstractCommand implements SystemCommand {
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
        queens.forEach(queen -> queen.extractFactories(queen.getLayer()));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
