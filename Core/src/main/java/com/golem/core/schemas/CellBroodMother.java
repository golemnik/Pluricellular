package com.golem.core.schemas;

import com.golem.core.innerMechanisms.AbstractSystemCellFactory;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface CellBroodMother extends Cell, InnerCellFullCore {
    Map<String, AbstractSystemCellFactory> getFactoryCommands ();
    <T extends AbstractSystemCellFactory> void addMainCellDepends(T factory);
    <T extends AbstractSystemCellFactory> void addFactory (T factory);
    void addFactoryList (List<? extends AbstractSystemCellFactory> factoryList);
    void reloadFactoryList (List<? extends AbstractSystemCellFactory> factoryList);
    void clearAllFactoryList();
    Cell createCell (String cell);
    static List<CellBroodMother> getCellBroodMothers(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, CellBroodMother.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
