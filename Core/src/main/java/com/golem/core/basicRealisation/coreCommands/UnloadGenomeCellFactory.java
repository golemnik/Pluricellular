package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.Cell;
import com.golem.core.innerMechanisms.AbstractSystemCellFactory;

public class UnloadGenomeCellFactory extends AbstractSystemCellFactory {
    @Override
    public Cell create() {
        UnloadGenomeCellCommand cell = new UnloadGenomeCellCommand();
        cell.setQueen(getBroodQueen());
        cell.setBroodMother(getBroodMother());
        return cell;
    }

    @Override
    public String creationCommand() {
        return "unload_genome";
    }

    @Override
    public String commandDescription() {
        return "clears all modules from the current layer.";
    }
}
