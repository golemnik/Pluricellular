package com.golem.terminal.terminalCommands.commands;

import com.golem.core.schemas.Cell;

public class AddGenomeFactory implements TerminalCommandFactory{
    @Override
    public Cell create() {
        return new AddCellGenome();
    }

    @Override
    public String creationCommand() {
        return "add_gene";
    }

    @Override
    public String commandDescription() {
        return "provides opportunity to load new cell's genes after global core init.";
    }
}
