package com.golem.terminal.terminalCommands.commands;

import com.golem.core.schemas.Cell;

public interface TerminalCommand extends Cell {
    String activationResult ();
}
