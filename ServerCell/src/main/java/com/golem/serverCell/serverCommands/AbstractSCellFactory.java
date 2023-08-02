package com.golem.serverCell.serverCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.serverCell.clients.Clients;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractSCellFactory extends AbstractCellFactory {
    private Clients clients;

    protected AbstractSCellFactory(Signature signature) {
        super(signature);
    }

    public void addClients(Clients clients) {
        this.clients = clients;
    }

    public Clients getClients() {
        return clients;
    }

    public static List<AbstractSCellFactory> getTicketCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractSCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
