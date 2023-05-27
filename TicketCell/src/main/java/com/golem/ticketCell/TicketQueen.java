package com.golem.ticketCell;

import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.List;

public class TicketQueen extends AbstractQueenCell {
    @Override
    public void activate() {
    }

    @Override
    public List<AbstractTCellFactory> extractFactories(ModuleLayer layer) {
        TicketCollection collection = new TicketCollection();
        List<AbstractTCellFactory> list = AbstractTCellFactory.getTicketCellFactories(layer);
        list.forEach(x -> x.addCollection(collection));
        return list;
    }
}
