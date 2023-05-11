package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommandCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;

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
