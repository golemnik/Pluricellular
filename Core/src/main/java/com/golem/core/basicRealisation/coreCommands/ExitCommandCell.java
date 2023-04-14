package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.schemas.Cell;

public class ExitCommandCell implements Cell {
    @Override
    public void activate() {
        System.exit(0);
    }
}
