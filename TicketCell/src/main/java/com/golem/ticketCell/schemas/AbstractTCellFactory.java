package com.golem.ticketCell.schemas;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.ticketCell.access.AbstractAccess;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractTCellFactory extends AbstractCellFactory {
    private AbstractAccess collection;

    protected AbstractTCellFactory(Signature signature) {
        super(signature);
    }

    public void addManager(AbstractAccess collection) {
        this.collection = collection;
    }

    public AbstractAccess getManager() {
        return collection;
    }

    public static List<AbstractTCellFactory> getTicketCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractTCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
