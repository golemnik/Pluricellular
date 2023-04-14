package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;

public class UnloadGenomeCellFactory extends AbstractCellFactory {
    @Override
    public Cell create() {
        UnloadGenomeCellCommand cell = new UnloadGenomeCellCommand();

        return new UnloadGenomeCellCommand();
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
