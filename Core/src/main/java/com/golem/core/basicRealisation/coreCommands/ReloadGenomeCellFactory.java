package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.Cell;

public class ReloadGenomeCellFactory extends AbstractCellFactory {
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
