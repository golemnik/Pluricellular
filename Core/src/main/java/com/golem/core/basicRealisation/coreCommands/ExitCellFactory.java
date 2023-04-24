package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.deepSchemas.SystemCommandCellFactory;
import com.golem.core.schemas.Cell;

public class ExitCellFactory extends AbstractSystemCellFactory implements SystemCommandCellFactory {
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
