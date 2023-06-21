package com.golem.userModule.abstractions;

import com.golem.clientCell.recipient.user.UserStorage;
import com.golem.userModule.Clients;
import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.signature.Signature;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractUCellFactory extends AbstractCellFactory {
    private UserStorage storage;
    protected AbstractUCellFactory(Signature signature) {
        super(signature);
    }

    public void setStorage(UserStorage storage) {
        this.storage = storage;
    }

    public UserStorage getStorage() {
        return storage;
    }

    public static List<AbstractUCellFactory> getTicketCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractUCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
