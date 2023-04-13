package com.golem.terminal.terminalCommands.commands;

import com.golem.core.schemas.Cell;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface TerminalCommandFactory {
    Cell create ();
    String creationCommand ();
    String commandDescription ();

    static List<TerminalCommandFactory> getCommandsFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, TerminalCommandFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
