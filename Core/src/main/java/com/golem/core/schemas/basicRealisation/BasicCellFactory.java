package com.golem.core.schemas.basicRealisation;

import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;

public class BasicCellFactory extends AbstractCellFactory {
    @Override
    public Cell create() {
        return new BasicCell();
    }

    @Override
    public String creationCommand() {
        return "basic";
    }

    @Override
    public String commandDescription() {
        return "do nothing. Simple testing cell, basic realisation.";
    }

}
