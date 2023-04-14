package com.golem.core.schemas;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface CellBroodMother extends Cell {
    Map<String, AbstractCellFactory> getFactoryCommands ();
    void addMainCellDepends ();
    void addFactory (AbstractCellFactory factory);
    void addFactoryList (List<AbstractCellFactory> factoryList);
    void reloadFactoryList (List<AbstractCellFactory> factoryList);
    Cell createCell (String cell);
    static List<CellBroodMother> getCellBroodMothers(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, CellBroodMother.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
