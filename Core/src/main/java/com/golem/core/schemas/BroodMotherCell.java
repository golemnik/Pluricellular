package com.golem.core.schemas;

import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.abstracts.AbstractQueenCell;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface BroodMotherCell extends Cell {
    Map<String, AbstractSystemCellFactory> getFactoryCommands ();

    <T extends AbstractSystemCellFactory> void addMainCellDepends(T factory, AbstractQueenCell queen);

    <T extends AbstractSystemCellFactory> void addFactory(T factory, AbstractQueenCell queen);

    <T extends AbstractSystemCellFactory> void addFactoryList(List<T> factoryList, AbstractQueenCell queen);

    <T extends AbstractSystemCellFactory> void reloadFactoryList(List<T> factoryList, List<AbstractQueenCell> queens);

    void clearAllFactoryList();
    Cell createCell (String cell);
    static List<BroodMotherCell> getCellBroodMothers(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, BroodMotherCell.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
