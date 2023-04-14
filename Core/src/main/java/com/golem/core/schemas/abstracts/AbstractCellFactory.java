package com.golem.core.schemas.abstracts;

import com.golem.core.schemas.CellFactory;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractCellFactory extends InnerCellFullCore implements CellFactory {
        public static List<AbstractCellFactory> getCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
