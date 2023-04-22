package com.golem.ticketCell;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;

public class TicketCollectionCellFactory extends AbstractCellFactory {
    @Override
    public Cell create() {
        return new TicketCollectionCell();
    }

    @Override
    public String creationCommand() {
        return "init_ticket_collection";
    }

    @Override
    public String commandDescription() {
        return "initiate ticket collection";
    }
}
