package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.Cell;

public class UpdateGenomeCellFactory extends AbstractCellFactory {
    @Override
    public Cell create() {
        UpdateGenomeCommandCell cell = new UpdateGenomeCommandCell();
        cell.setQueen(getBroodQueen());
        return cell;
    }

    @Override
    public String creationCommand() {
        return "update_genes";
    }

    @Override
    public String commandDescription() {
        return "update genome to find new genes can be used.";
    }
}
