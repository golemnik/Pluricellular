package com.golem.terminal.terminalCommands.commands;

import com.golem.terminal.terminalCommands.schemes.TerminalCommand;

public class EndCellCommand implements TerminalCommand {
    @Override
    public String activationResult() {
        return "End the program.";
    }

    @Override
    public void activate() {
        System.exit(0);
    }
}
