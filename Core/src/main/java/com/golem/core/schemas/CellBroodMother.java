package com.golem.core.schemas;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface CellBroodMother extends Cell {
    List<String> getFactoryCommands ();
    void addFactory (CellFactory factory);
    void addFactoryList (List<CellFactory> factoryList);
    void reloadFactoryList (List<CellFactory> factoryList);
    Cell createCell (String cell);
    static List<CellBroodMother> getCellBroodMothers(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, CellBroodMother.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
