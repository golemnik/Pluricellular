package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.deepSchemas.SystemCommand;
import com.golem.core.schemas.Cell;

public class ExitCommandCell implements Cell, SystemCommand {
    @Override
    public void activate() {
        System.exit(0);
    }
}
