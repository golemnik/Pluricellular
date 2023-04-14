package com.golem.core.schemas;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface TerminalCell extends Cell, InnerCellFullCore {
    void terminalInit();
    void terminalCycle ();
    static List<TerminalCell> getCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, TerminalCell.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
