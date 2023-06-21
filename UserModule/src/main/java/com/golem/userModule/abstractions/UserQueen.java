package com.golem.userModule.abstractions;

import com.golem.clientCell.recipient.user.UserStorage;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;

import java.util.List;

public class UserQueen extends AbstractQueenCell {
    @Override
    public void activate() {
    }

    @Override
    public List<AbstractUCellFactory> extractFactories(ModuleLayer layer) {
        UserStorage storage = null ;
        List<AbstractUCellFactory> list = AbstractUCellFactory.getTicketCellFactories(layer);
        list.forEach(x -> x.setStorage(storage));
        return list;
    }
}
