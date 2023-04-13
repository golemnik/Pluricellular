package com.golem.shoutingCell;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellFactory;

public class ShoutingCellFactory implements CellFactory {
    @Override
    public Cell create() {
        return new ShoutingCell();
    }

    @Override
    public String creationCommand() {
        return "shout";
    }

    @Override
    public String commandDescription() {
        return " - simple cell for testing. Core outer module.";
    }
}
