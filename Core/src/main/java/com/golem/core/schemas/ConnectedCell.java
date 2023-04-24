package com.golem.core.schemas;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface ConnectedCell extends Cell, InnerCellFullCore {
    @Override
    void activate();
    static List<ConnectedCell> getBranchConnectionCell(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, ConnectedCell.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
