package com.golem.core.basicRealisation;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellFactory;

public class BasicCellFactory implements CellFactory {
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
        return " - do nothing. Simple testing cell, basic realisation.";
    }
}
