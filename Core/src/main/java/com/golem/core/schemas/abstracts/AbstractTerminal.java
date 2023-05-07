package com.golem.core.schemas.abstracts;

import com.golem.core.schemas.TerminalCell;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractTerminal extends AbstractExtendedICellCore implements TerminalCell {

    public abstract int priority();

    @Override
    public void activate() {
        terminalInit();
        terminalCycle();
    }

    @Override
    public abstract void terminalInit();

    @Override
    public abstract void terminalCycle();

    public static List<AbstractTerminal> getCellTerminals(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractTerminal.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
