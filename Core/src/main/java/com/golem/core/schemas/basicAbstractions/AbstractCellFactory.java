package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.signature.Signature;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractCellFactory extends AbstractSystemCellFactory {
    protected AbstractCellFactory (Signature signature) {
        super(signature);
    }
    public static List<AbstractCellFactory> getCellFactories(ModuleLayer layer) {
        return ServiceLoader
            .load(layer, AbstractCellFactory.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .collect(Collectors.toList());
    }
}
