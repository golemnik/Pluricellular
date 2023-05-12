package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommandCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExitCellFactory extends AbstractSystemCellFactory implements SystemCommandCellFactory {
    public ExitCellFactory() {
        super(new Signature(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    @Override
    public String creationCommand() {
        return "exit";
    }

    @Override
    public String commandDescription() {
        return "shut the core cell down.";
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        return new ExitCommandCell().useSignature(signature);
    }
}
