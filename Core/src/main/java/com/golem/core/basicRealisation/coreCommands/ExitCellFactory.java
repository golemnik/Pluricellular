package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.Cell;

public class ExitCellFactory extends AbstractCellFactory {
    @Override
    public Cell create() {
        return new ExitCommandCell();
    }

    @Override
    public String creationCommand() {
        return "exit";
    }

    @Override
    public String commandDescription() {
        return "shut the core cell down.";
    }
}
