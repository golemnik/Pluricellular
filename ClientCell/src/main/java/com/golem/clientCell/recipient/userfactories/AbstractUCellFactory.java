package com.golem.clientCell.recipient.userfactories;

import com.golem.clientCell.recipient.user.User;
import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.signature.Signature;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractUCellFactory extends AbstractCellFactory {
    private User user;

    protected AbstractUCellFactory(Signature signature) {
        super(signature);
    }

    public void addUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean runAtStart() {
        return true;
    }

    public static List<AbstractUCellFactory> getUserCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractUCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
