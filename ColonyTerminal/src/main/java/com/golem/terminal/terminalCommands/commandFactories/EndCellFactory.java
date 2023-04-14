package com.golem.terminal.terminalCommands.commandFactories;

import com.golem.core.schemas.Cell;
import com.golem.terminal.terminalCommands.commands.EndCellCommand;
import com.golem.terminal.terminalCommands.schemes.TerminalCommandFactory;

public class EndCellFactory implements TerminalCommandFactory {
    @Override
    public Cell create() {
        return new EndCellCommand();
    }

    @Override
    public String creationCommand() {
        return "exit";
    }

    @Override
    public String commandDescription() {
        return "provides ability to end program.";
    }
}
