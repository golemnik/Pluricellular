package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.schemas.Cell;

public class UpdateGenomeCommandCell implements Cell {
    private BroodQueen queen;
    public UpdateGenomeCommandCell() {
    }
    public void setQueen (BroodQueen queen) {
        this.queen = queen;
    }
    @Override
    public void activate() {
        queen.activate();
    }
}
