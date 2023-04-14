package com.golem.core.schemas;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface CellFactory {
    Cell create ();
    String creationCommand ();
    String commandDescription ();
    static List<CellFactory> getCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, CellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
