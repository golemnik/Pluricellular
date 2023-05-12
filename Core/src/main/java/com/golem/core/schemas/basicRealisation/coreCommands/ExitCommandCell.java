package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;
import com.golem.core.schemas.basicInterfaces.Cell;

import java.util.List;

public class ExitCommandCell extends AbstractCommand implements SystemCommand {
    @Override
    public void activate() {
        System.exit(0);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
