package com.golem.shoutingTwice;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellFactory;
import com.golem.shoutingCell.ShoutingCellFactory;

public class ShoutingTwiceCellFactory extends ShoutingCellFactory implements CellFactory {
    @Override
    public Cell create() {
        return new ShoutingTwiceCell();
    }

    @Override
    public String creationCommand() {
        return "shout_twice";
    }

    @Override
    public String commandDescription() {
        return " - simple cell for testing. Core outer module.";
    }
}
