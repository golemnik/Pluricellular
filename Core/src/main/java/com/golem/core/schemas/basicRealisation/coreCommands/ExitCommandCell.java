package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;
import com.golem.core.schemas.basicInterfaces.Cell;

public class ExitCommandCell implements Cell, SystemCommand {
    @Override
    public void activate() {
        System.exit(0);
    }
}
