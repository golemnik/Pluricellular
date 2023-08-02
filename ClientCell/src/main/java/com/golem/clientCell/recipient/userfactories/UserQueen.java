package com.golem.clientCell.recipient.userfactories;

import com.golem.clientCell.recipient.user.User;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;

import java.util.List;

public class UserQueen extends AbstractQueenCell {
    private static final User user = new User();
    @Override
    public void activate() {

    }

    public static User getUser() {
        return user;
    }

    @Override
    public List<AbstractUCellFactory> extractFactories(ModuleLayer layer) {
        List<AbstractUCellFactory> list = AbstractUCellFactory.getUserCellFactories(layer);
        list.forEach(x -> x.addUser(user));
        return list;
    }
}
