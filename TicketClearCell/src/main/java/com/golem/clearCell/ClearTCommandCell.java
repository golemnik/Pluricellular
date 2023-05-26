package com.golem.clearCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.List;

public class ClearTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    public ClearTCommandCell () {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        collection.getCollection().clear();
        setAnswer(List.of("Collection is empty now."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
