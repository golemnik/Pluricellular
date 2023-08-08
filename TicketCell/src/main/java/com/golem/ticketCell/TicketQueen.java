package com.golem.ticketCell;

import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.List;

public class TicketQueen extends AbstractQueenCell {
    @Override
    public void activate() {

    }

    @Override
    public List<AbstractTCellFactory> extractFactories(ModuleLayer layer) {
        AbstractAccess collectionManager = AbstractAccess.getCollectionAccess(getLayer()).get(0);
        List<AbstractTCellFactory> list = AbstractTCellFactory.getTicketCellFactories(layer);
        list.forEach(x -> x.addManager(collectionManager));
        return list;
    }
}
