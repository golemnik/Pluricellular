package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;

import java.util.List;

public class ExitCommandCell extends AbstractCommand implements SystemCommand {
    @Override
    public void activate() {
        setAnswer(List.of("Exiting..."));
    }

    @Override
    public boolean exitable() {
        return true;
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
