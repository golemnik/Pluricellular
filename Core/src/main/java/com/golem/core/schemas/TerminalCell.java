package com.golem.core.schemas;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface TerminalCell extends Cell {
    void terminalInit();
    void terminalCycle ();
    void addBroodMother (CellBroodMother broodMother);
    void addCore (CoreCell coreCell);
    void addQueen (BroodQueen broodQueen);
    static List<TerminalCell> getCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, TerminalCell.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
