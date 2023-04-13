package com.golem.terminal.terminalCommands;

import com.golem.core.schemas.Cell;
import com.golem.terminal.terminalCommands.commands.TerminalCommandFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalCommandsCell implements Cell {
    private Map<String, TerminalCommandFactory> terminalCommands = new HashMap<>();

    public TerminalCommandsCell () {

    }

    public static List<TerminalCommandFactory> loadTerminals (ModuleLayer layer) {
        return TerminalCommandFactory.getCommandsFactories(layer);
    }

    public void LoadCommands (List<TerminalCommandFactory> terminalCommandFactories) {
        for (TerminalCommandFactory tfc : terminalCommandFactories) {
            terminalCommands.put(tfc.creationCommand(), tfc);
        }
    }

    public List<String> getCommandList () {
        return new ArrayList<>(terminalCommands.keySet());
    }

    @Override
    public void activate() {

    }
}
