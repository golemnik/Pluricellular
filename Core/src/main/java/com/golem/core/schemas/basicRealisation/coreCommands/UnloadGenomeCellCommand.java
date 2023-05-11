package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.queens.BroodQueen;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;

public class UnloadGenomeCellCommand implements Cell, SystemCommand {

    private BroodQueen queen;
    private BroodMotherCell broodMother;
    public UnloadGenomeCellCommand() {
    }
    public void setQueen (BroodQueen queen) {
        this.queen = queen;
    }
    public void setBroodMother (BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
//        CellLayer.UnloadGenomeLayer();
//        queen.updateQueenGenomeLayer();
//        broodMother.clearAllFactoryList();
//        broodMother.reloadFactoryList(BroodQueen.loadSystemFactories(queen.getCoreLayer()));
        System.out.println("this command is not working. For updating cell jar's - re-run project.");
    }
}
