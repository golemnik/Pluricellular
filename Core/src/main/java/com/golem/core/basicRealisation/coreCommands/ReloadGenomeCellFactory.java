package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.Cell;
import com.golem.core.innerMechanisms.AbstractSystemCellFactory;

public class ReloadGenomeCellFactory extends AbstractSystemCellFactory {
    @Override
    public Cell create() {
        ReloadGenomeCommandCell cell = new ReloadGenomeCommandCell();
        cell.setQueen(getBroodQueen());
        cell.setBroodMother(getBroodMother());
        return cell;
    }

    @Override
    public String creationCommand() {
        return "reload_genome";
    }

    @Override
    public String commandDescription() {
        return "update genome to find new genes can be used.";
    }
}
