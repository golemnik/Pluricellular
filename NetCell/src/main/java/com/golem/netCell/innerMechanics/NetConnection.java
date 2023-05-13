package com.golem.netCell.innerMechanics;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface NetConnection {
    void cycle (AbstractTerminal terminal);

    static List<NetConnection> getConnector(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, NetConnection.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
