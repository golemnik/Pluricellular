package com.golem.core.schemas.abstracts;

import com.golem.core.schemas.deepSchemas.SystemCommandCellFactory;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractSystemCellFactory extends AbstractInnerCellFullCore implements SystemCommandCellFactory {
    public static List<AbstractSystemCellFactory> getSystemCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractSystemCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
