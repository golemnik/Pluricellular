package com.golem.ticketCell;

import com.golem.core.schemas.ConnectedCell;
import com.golem.core.schemas.abstracts.AbstractICellCore;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.List;

public class TicketDataConnectorCell extends AbstractICellCore implements ConnectedCell {

    private TicketCollection ticketCollectionCell;
    public TicketDataConnectorCell () {
        ticketCollectionCell = new TicketCollection();
    }

    public TicketCollection getTicketCollectionCell() {
        return ticketCollectionCell;
    }

    @Override
    public void activate() {
        List<AbstractTCellFactory> factories = AbstractTCellFactory.getTicketCellFactories(getBroodQueen().getLayer());
        factories.forEach(x -> x.addCollection(ticketCollectionCell));
        getBroodMother().addFactoryList(factories);
    }
}
