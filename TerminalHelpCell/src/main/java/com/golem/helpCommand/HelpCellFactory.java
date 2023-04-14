package com.golem.helpCommand;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;

public class HelpCellFactory extends AbstractCellFactory {
    @Override
    public Cell create() {
        HelpCommandCell cell = new HelpCommandCell();
        cell.addBroodMother(getBroodMother());
        return cell;
    }

    @Override
    public String creationCommand() {
        return "help";
    }

    @Override
    public String commandDescription() {
        return "show available commands and their descriptions.";
    }
}
