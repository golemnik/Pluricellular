package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;

public class ReloadGenomeCellFactory extends AbstractSystemCellFactory {
    @Override
    public Cell create() {
        ReloadGenomeCommandCell cell = new ReloadGenomeCommandCell();
        cell.setQueen(getCoreCell().getQueenConnections());
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
